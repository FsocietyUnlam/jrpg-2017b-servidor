package comandos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
/**
 * Clase ConexionHibernate.
 * @author Hector
 *
 */
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
    private static volatile SessionFactory INSTANCE = null;
    private static Session unSession;

    // public static SessionFactory getSessionFactory() {
    /**
     * Getter de SessionFactory.
     * @return INSTANCE
     */
    public SessionFactory getSessionFactory() {
        if (INSTANCE == null) {
            createSessionFactory();
        }
        return INSTANCE; // SessionFactory;
    }
/**
 * Metodo createSessionFactory.
 */
    private static synchronized void createSessionFactory() {
        if (INSTANCE != null) {
            unSession.clear();
            return;
        }

        Configuration configuration = new Configuration();
        configuration.configure();

        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        INSTANCE = configuration.buildSessionFactory(serviceRegistry);
        unSession = INSTANCE.openSession();
    }

    /*
     * public static Session getUnSession() { if(unSession.) return unSession; }
     */

    /*
     * public static void setUnSession(Session unSession) {
     * ConexionHibernate.unSession = unSession; }
     */

}
