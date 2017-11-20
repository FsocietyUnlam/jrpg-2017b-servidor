package servidor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConectorSingleton {

	private static ConectorSingleton instance = new ConectorSingleton();
	private Session session;

	private ConectorSingleton() {
		// TODO Auto-generated constructor stub
	}
	
	public static ConectorSingleton getInstance() {
		return instance;
	}
	
	public Session getSession() {
	
		
		
		if (session == null) {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			session = factory.openSession();
		}
		
		return session;
		
	}
	
}
