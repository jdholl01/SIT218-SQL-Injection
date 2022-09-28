package edu.deakin.sit218.coachwebapp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.deakin.sit218.coachwebapp.entity.Client;

public class TestJDBC {
	
	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost/coachappschema?" +
                "user=coachdbadmin&password=coachdbadmin";
		try {
			System.out.println("Connecting to Database");
			Connection conn = DriverManager.getConnection(jdbcURL);
			System.out.println("Connection successful!");
		} catch (SQLException e) {
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
		}
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		Client client = new Client("Test", "yes", "mys");
		
		try {
			
			session.beginTransaction();
			
			session.save(client);
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}

}	