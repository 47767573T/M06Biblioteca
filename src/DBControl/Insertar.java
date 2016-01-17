package DBControl;


import DBObjects.Libros;
import DBObjects.Socios;
import org.hibernate.Query;
import org.hibernate.Session;
import sun.tools.jar.Main;

import java.io.PrintStream;

/**
 * Created by Moises on 17/01/2016.
 */
public class Insertar{

    public static void nuevoLibro(/*Libros Libro*/){

        Session session = Acceso.getSession("Insertar_nuevoLibro");
        session.beginTransaction();

        //Libro de prueba
        Libros libro1 = new Libros("tit1", 20, "ed1", 23, 1902);
        Libros libro2 = new Libros("tit2", 25, "ed2", 45, 2005);
        Libros libro3 = new Libros("tit2", 10, "ed2", 45, 1999);

        session.save(libro1);
        session.save(libro2);
        session.save(libro3);
        session.getTransaction().commit();
    }

    public static void nuevoSocio(/*Socios socio*/) {

        Session session = Acceso.getSession("Insertar_nuevoSocio");
        session.beginTransaction();

        //Socios de prueba
        Socios socio1 = new Socios("nom1", "cognom1", 58, "direccion1", 91111111);
        Socios socio2 = new Socios("nom2", "cognom2", 25, "direccion2", 92222222);

        session.save(socio1);
        session.save(socio2);
        session.getTransaction().commit();
    }

}
