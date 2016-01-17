package DBControl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Map;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.awt.*;


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
        }catch (ExceptionInInitializerError ex){
            System.out.println("Error en DB.Acceso --> "+ex);
        }
    }

    public static Session getSession(String callClass) throws HibernateException {
        System.out.println("peticion de acceso a "+conf.getProperty("connection.url")+" desde "+callClass);
        return sessionFactory.openSession();

    }

    //Apagar la sesion
    public static void apagar() {sessionFactory.close();}

    /*
        public static ServiceRegistry nuevaServiceRegistry() {
            System.out.println("llega a registro");
            return new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .buildServiceRegistry();
        }

        private static SessionFactory nuevaSessionFactory() {
            try{
                System.out.println("Entra en nueva session factory");
                return configuration.buildSessionFactory(serviceRegistry);
            }catch(Throwable ex){
                throw new ExceptionInInitializerError(ex);
            }
        }
    */


}
