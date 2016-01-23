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
            System.out.println("peticion de acceso a "+conf.getProperty("connection.url"));
        }catch (ExceptionInInitializerError ex){
            System.out.println("Error en DB.Acceso --> "+ex);
        }
    }

    public static Session getSession(String callClass) throws HibernateException {
        System.out.println("peticion de acceso desde "+callClass);
        return sessionFactory.openSession();
    }

    public static void Apagar() {sessionFactory.close();}
}
