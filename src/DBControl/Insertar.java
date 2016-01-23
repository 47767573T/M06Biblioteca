package DBControl;


import DBObjects.Libros;
import DBObjects.Prestamo;
import DBObjects.Socios;
import org.hibernate.Query;
import org.hibernate.Session;
import sun.tools.jar.Main;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by Moises on 17/01/2016.
 */
public class Insertar{

    public static void nuevoLibro(Libros libro){

        Session session = Acceso.getSession("Insertar_nuevoLibro");
        session.beginTransaction();

        session.save(libro);
        session.getTransaction().commit();
    }

    public static void nuevoSocio(Socios socio) {

        Session session = Acceso.getSession("Insertar_nuevoSocio");
        session.beginTransaction();

        session.save(socio);
        session.getTransaction().commit();
    }

    public static void nuevoPrestamo(Prestamo prestamo) {

        Session session = Acceso.getSession("Insertar_nuevoPrestamo");
        session.beginTransaction();

        session.save(prestamo);
        session.getTransaction().commit();
    }
}
