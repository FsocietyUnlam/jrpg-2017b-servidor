package comandos;

//import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class ConexionHibernate {

	/*
	 * private static SessionFactory sessionFactory;
	 * 
	 * static {
	 * 
	 * Configuration configuration = new Configuration(); configuration.configure();
	 * StandardServiceRegistry serviceRegistry = new
	 * StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
	 * .build(); sessionFactory=configuration.buildSessionFactory(serviceRegistry);
	 * }
	 * 
	 * public static SessionFactory getSessionFactory() { return sessionFactory; }
	 */

	private static StandardServiceRegistry serviceRegistry;
	private volatile static SessionFactory INSTANCE = null;
	//private static Session unSession;

	//u obtener la sesion?
	//public static SessionFactory getSessionFactory() {
	public  SessionFactory getSessionFactory() {
		if (INSTANCE == null) {
			createSessionFactory();
		}
		return INSTANCE;// SessionFactory;
	}

	private synchronized static void createSessionFactory() {
		if (INSTANCE != null) {
			return;
		}

		Configuration configuration = new Configuration();
		configuration.configure();

		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		INSTANCE = configuration.buildSessionFactory(serviceRegistry);
		//unSession = INSTANCE.openSession();
	}

	/*public static Session getUnSession() {
		return unSession;
	}*/

	/*
	 * public static void setUnSession(Session unSession) {
	 * ConexionHibernate.unSession = unSession; }
	 */

}
