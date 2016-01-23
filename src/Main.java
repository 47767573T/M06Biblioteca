import DBControl.Acceso;
import DBObjects.Libros;
import DBObjects.Prestamo;
import DBObjects.Socios;
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
        ComprobarTablas(session);       //De estaa forma comprobamos la conexxion y las tablas mediante consola

        //INSERTAR:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        DBControl.Insertar.nuevoLibro();
        DBControl.Insertar.nuevoSocio();
        DBControl.Insertar.nuevoPrestamo(5);

        //ELIMINAR:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        DBControl.Eliminar.libroPorId(2);
        DBControl.Eliminar.socioPorId(3);
        DBControl.Eliminar.PrestamosPorId(3);

        //CONSULTAS::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //Libros.................................................................................
        List<Object> consulta1 = DBControl.Consultar.querySimple("Libros", "titulo", "tit2",0);
        for (int i = 0; i < consulta1.size(); i++) {
            Libros libro = (Libros)consulta1.get(i);
            System.out.println("\t"+libro.getTitulo()+" ("+libro.getEditorial()+") - edicion del "+libro.getAnoEdicion());
        }

        List<Object> consulta2 = DBControl.Consultar.querySimple("Libros", "editorial", "ed2",0);
        for (int i = 0; i < consulta2.size(); i++) {
            Libros libro = (Libros)consulta2.get(i);
            System.out.println("\t"+libro.getTitulo()+" ("+libro.getEditorial()+") - edicion del "+libro.getAnoEdicion());
        }

        List<Object> consulta3 = DBControl.Consultar.querySimple("Libros", "anoedicion", "1999",1);
        for (int i = 0; i < consulta3.size(); i++) {
            Libros libro = (Libros)consulta3 .get(i);
            System.out.println("\t"+libro.getTitulo()+" ("+libro.getEditorial()+") - edicion del "+libro.getAnoEdicion());
        }

        List<Object> consulta4 = DBControl.Consultar.querySimple("Prestamo", "idlibro_id", "1",1);
        for (int i = 0; i < consulta4.size(); i++) {
            Prestamo prestamo = (Prestamo)consulta4.get(i);
            System.out.println("\t("+prestamo.getFechaFin()+" - "+prestamo.getFechaFin()+") de ID="+prestamo.getId());
        }

        //MODIFICAR
        DBControl.Modificar.querySimple("Libros", "titulo", "tit2", "anoedicion", "2001", 1);

/*
        //Socios.................................................................................
        List<Object> sociosPorNombre= DBControl.Consultar.socioPorNombre("nom2");
        for (int i = 0; i < librosPorAnoEdicion.size(); i++) {
            Socios socio = (Socios)sociosPorNombre.get(i);
            System.out.println("\t"+socio.getNombre()+" "+socio.getApellido()+" ("+socio.getEdad()+") con tlf "
                    +socio.getTelefono()+" y domicilio en "+socio.getDireccion());
        }

        List<Object> sociosPorApellido= DBControl.Consultar.socioPorApellido("cognom2");
        for (int i = 0; i < sociosPorApellido.size(); i++) {
            Socios socio = (Socios)sociosPorApellido.get(i);
            System.out.println("\t"+socio.getNombre()+" "+socio.getApellido()+" ("+socio.getEdad()+") con tlf "
                    +socio.getTelefono()+" y domicilio en "+socio.getDireccion());
        }
*/
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