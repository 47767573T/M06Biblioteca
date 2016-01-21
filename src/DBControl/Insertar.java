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

    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    static Random rnd = new Random();

    public static void nuevoLibro(/*Libros Libro*/){

        Session session = Acceso.getSession("Insertar_nuevoLibro");
        session.beginTransaction();

        //Libro de prueba
        Libros libro1 = new Libros("tit1", 20, "ed1", 23, 1902);
        Libros libro2 = new Libros("tit2", 25, "ed2", 45, 2005);
        Libros libro3 = new Libros("tit2", 10, "ed2", 45, 1999);
        Libros libro4 = new Libros("tit4", 560, "edX", 54, 1999);


        session.save(libro1);
        session.save(libro2);
        session.save(libro3);
        session.save(libro4);
        session.getTransaction().commit();

    }

    public static void nuevoSocio(/*Socios socio*/) {

        Session session = Acceso.getSession("Insertar_nuevoSocio");
        session.beginTransaction();

        //Socios de prueba
        Socios socio1 = new Socios("nom1", "cognom1", 58, "direccion1", 91111111);
        Socios socio2 = new Socios("nom2", "cognom2", 25, "direccion2", 92222222);
        Socios socio3 = new Socios("nom3", "cognom3", 987, "direccion3", 933333333);
        Socios socio4 = new Socios("nom4", "cognom4", 12, "direccion4", 944444444);

        session.save(socio1);
        session.save(socio2);
        session.save(socio3);
        session.save(socio4);
        session.getTransaction().commit();
    }

    public static void nuevoPrestamo(/*Prestamo prestamo,*/ int diasDePrestamo) {

        Session session = Acceso.getSession("Insertar_nuevoPrestamo");
        session.beginTransaction();

        Calendar calendar = Calendar.getInstance();
        Date diaPrestamo = calendar.getTime();
        System.out.println(diaPrestamo.toString());
        calendar.add(GregorianCalendar.DAY_OF_YEAR,diasDePrestamo);
        Date diaDevolucion = calendar.getTime();
        System.out.println(diaPrestamo.toString());

        Libros libro1 = new Libros(1);
        Socios socio1 = new Socios(2);

        Libros libro2 = new Libros(3);
        Socios socio2 = new Socios(1);

        Libros libro3 = new Libros(4);
        Socios socio3 = new Socios(4);


        //Prestamos de prueba
        Prestamo prestamo1 = new Prestamo(libro1, socio1, diaPrestamo, diaDevolucion);
        Prestamo prestamo2 = new Prestamo(libro2, socio2, diaPrestamo, diaDevolucion);
        Prestamo prestamo3 = new Prestamo(libro3, socio3, diaPrestamo, diaDevolucion);

        session.save(prestamo1);
        System.out.println("\t Ingresado prestamo1 (libro1-Socio2)");
        session.save(prestamo2);
        System.out.println("\t Ingresado prestamo2 (libro3-Socio1)");
        session.save(prestamo3);
        System.out.println("\t Ingresado prestamo3 (libro4-Socio4)");
        session.getTransaction().commit();
    }

}
