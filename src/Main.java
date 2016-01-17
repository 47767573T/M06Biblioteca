import DBControl.Acceso;
import DBObjects.Libros;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

import java.util.List;
import java.util.Map;

/**
 * Created by Moises on 14/01/2016.
 */
public class Main {



    public static Acceso acceso;




    public static void main(final String[] args) throws Exception {

        //Configuracion y acceso inicial a la base de datos
        Configuration conf = new Configuration().configure();
        acceso = new Acceso(conf);
        Session session = acceso.getSession("Main");
        ComprobarTablas(session);

        //INSERTAR:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        DBControl.Insertar.nuevoLibro();
        DBControl.Insertar.nuevoSocio();

        //ELIMINAR:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        DBControl.Eliminar.libroPorId(1);
        DBControl.Eliminar.socioPorId(4);

        //CONSULTAS::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //Libros.................................................................................
        List<Object> librosPorTitulo = DBControl.Consultar.libroPorTitulo("tit2");
        for (int i = 0; i < librosPorTitulo.size(); i++) {
            Libros libro = (Libros)librosPorTitulo.get(i);
            System.out.println(libro.getTitulo()+" ("+libro.getEditorial()+") - edicion del "+libro.getAnoEdicion());
        }

        List<Object> librosPorEditorial = DBControl.Consultar.libroPorEditorial("ed2");
        for (int i = 0; i < librosPorEditorial.size(); i++) {
            Libros libro = (Libros)librosPorEditorial.get(i);
            System.out.println(libro.getTitulo()+" ("+libro.getEditorial()+") - edicion del "+libro.getAnoEdicion());
        }

        List<Object> librosPorAnoEdicion = DBControl.Consultar.libroPorAnoEdicion(2005);
        for (int i = 0; i < librosPorAnoEdicion.size(); i++) {
            Libros libro = (Libros)librosPorAnoEdicion.get(i);
            System.out.println(libro.getTitulo()+" ("+libro.getEditorial()+") - edicion del "+libro.getAnoEdicion());
        }

        //Socios.................................................................................

        acceso.Apagar(); //Cerramos el sessionFactory al cerrar la aplicacion

    }

    public static void ComprobarTablas (Session session){
        try {
            System.out.println("buscando las entidades...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("\tejecutando" +
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





/*private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration().configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }*/