package edu.deakin.sit218.coachwebapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.deakin.sit218.coachwebapp.entity.Client;

public class ClientDAOImpl implements ClientDAO {
	
	private SessionFactory factory;
	
	public ClientDAOImpl() {
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
	}
	
	@Override
	public void insertClient(Client client) {
		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
			session.save(client);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
	}
	
	@Override
	public boolean existsClient(Client client) {
		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
			String hql = "from Client where question = '"+ client.getQuestion()+"' and answer = '"+client.getAnswer()+"' and area = '"+client.getArea()+"'";
			List<Client> clients = session.createQuery(hql).getResultList();
			
			return (!clients.isEmpty());
			
		} finally {
			session.close();
		}
	}
	
	@Override
	public Client retrieveClient(Client client) {
		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
			String hql = "from Client where question = '"+ client.getQuestion()+"' and answer = '"+client.getAnswer()+"' and area = '"+client.getArea()+"'";
			List<Client> clients = session.createQuery(hql).getResultList();
			
			if (clients.isEmpty()) {
				throw new RuntimeException("There is no client: " + client.toString());
			}
			else if (clients.size() > 1) {
				throw new RuntimeException("More than one client exists: " + client.toString());
			}
			else {
				return clients.get(0);
			}
			
		} finally {
			session.close();
		}
	}
	
	@Override
	public void updateClient(Client client) {
		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
			session.update(client);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		factory.close();
	}
}
