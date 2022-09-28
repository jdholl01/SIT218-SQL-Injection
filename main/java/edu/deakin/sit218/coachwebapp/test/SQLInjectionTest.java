package edu.deakin.sit218.coachwebapp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.deakin.sit218.coachwebapp.entity.Client;

public class SQLInjectionTest {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your question area: ");
		String area = scanner.nextLine();
		scanner.close();
	
		String jdbcURL = "jdbc:mysql://localhost/questionandanswer?" +
                "user=qaadmin&password=qaadmin";
		try {
			System.out.println("Connecting to Database");
			Connection conn = DriverManager.getConnection(jdbcURL);
			System.out.println("Connection successful!");
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM client WHERE area = '"+ area +"'";
			System.out.println(query);
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				int clientID = result.getInt("idclient");
				String answer = result.getString("answer");
				String question = result.getString("question");
				area = result.getString("area");
				System.out.println(clientID + ", " + question + ", " + answer + ", " + area);
			}
			
			
		} catch (SQLException e) {
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
		    
		}
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		
		try {
			
			session.beginTransaction();
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}

}	