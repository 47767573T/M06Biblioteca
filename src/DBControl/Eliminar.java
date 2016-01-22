package DBControl;

import DBObjects.Libros;
import DBObjects.Prestamo;
import DBObjects.Socios;
import org.hibernate.Session;

/**
 * Created by Moises on 17/01/2016.
 */
public class Eliminar {

    public static void libroPorId(int Id){

        Session session = Acceso.getSession("Eliminar_libroPorId");

        session.beginTransaction();
        Libros libro = new Libros(Id);
        session.delete(libro);
        session.getTransaction().commit();
    }

    public static void socioPorId(int Id){

        Session session = Acceso.getSession("Eliminar_SocioPorId");

        session.beginTransaction();
        Socios socio = new Socios(Id);
        session.delete(socio);
        session.getTransaction().commit();
    }

    public static void PrestamosPorId(int Id){

        Session session = Acceso.getSession("Eliminar_PrestamosPorId");

        session.beginTransaction();
        Prestamo prestamo = new Prestamo(Id);
        session.delete(prestamo);
        session.getTransaction().commit();
    }


}
