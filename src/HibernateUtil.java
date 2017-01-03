
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
/*import org.hibernate.boot.registry.StandardServiceRegistryBuilder;*/
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.stat.Statistics;

public class HibernateUtil {

	private static final SessionFactory sessionFactory=buildSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
      
		
        serviceRegistry =new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
         
    }

   
    public static SessionFactory getSessionFactory() {
    
		
        return sessionFactory;
    
    }

    public static void shutdown() {
    	// Close caches and connection pools
    	getSessionFactory().close();
    }

}

