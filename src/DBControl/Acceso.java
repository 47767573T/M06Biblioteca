package DBControl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * Created by Moises on 17/01/2016.
 */
public class Acceso {

    public static Configuration conf;
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;


    //CONSTRUCTOR
    public Acceso(Configuration configuration) {

        try{
            conf = configuration;
            System.out.print("inicio de configuracion de serviceRegistry y sessionFactory...");
            serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .buildServiceRegistry();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            System.out.println("...Configuracion correcta");
            System.out.println("peticion de acceso a "+conf.getProperty("connection.url"));
        }catch (ExceptionInInitializerError ex){
            System.out.println("Error en DB.Acceso --> "+ex);
        }
    }

    public static Session getSession(String callClass) throws HibernateException {
        System.out.println("peticion de acceso desde "+callClass);
        return sessionFactory.openSession();
    }

    public static void ComprobarTablas (Session session){
        try {
            System.out.println("buscando las entidades...");
            final java.util.Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("\tencontrada" +
                        ": " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
            System.out.println("...entidades encontradas en Base de datos");
        } catch (ExceptionInInitializerError ex) {
            System.out.println("Error en Main_ComprobarTablas"+ ex);
            session.close();
        }
    }
}
