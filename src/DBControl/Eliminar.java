package DBControl;

import DBObjects.Libros;
import DBObjects.Prestamo;
import DBObjects.Socios;
import org.hibernate.Session;

/**
 * Created by Moises on 17/01/2016.
 */
public class Eliminar {

    public static boolean libroPorId(int id){

        Session session = Acceso.getSession("Eliminar_libroPorId");

        session.beginTransaction();
        Libros libro = DBControl.Consultar.LibroPorId(id);

        if (libro.getId() == 0) return false;
        else{
            session.delete(libro);
            session.getTransaction().commit();
            return true;
        }
    }

    public static boolean socioPorId(int id){

        Session session = Acceso.getSession("Eliminar_SocioPorId");

        session.beginTransaction();
        Socios socio = DBControl.Consultar.SocioPorId(id);

        if (socio.getId() == 0) return false;
        else {
            session.delete(socio);
            session.getTransaction().commit();
            return true;
        }
    }

    public static boolean prestamoPorId(int id){

        Session session = Acceso.getSession("Eliminar_PrestamosPorId");

        session.beginTransaction();
        Prestamo prestamo = DBControl.Consultar.PrestamoPorId(id);

        if (prestamo.getId() == 0) return false;
        else {
            session.delete(prestamo);
            session.getTransaction().commit();
            return true;
        }
    }

    public static void Prestamos(Prestamo prestamo){
        Session session = Acceso.getSession("Eliminar_Prestamo");

        session.beginTransaction();
        session.delete(prestamo);
        session.getTransaction().commit();

    }

}
