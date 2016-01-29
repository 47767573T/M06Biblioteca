package DBControl;


import DBObjects.Libros;
import DBObjects.Prestamo;
import DBObjects.Socios;
import org.hibernate.Session;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Moises on 17/01/2016.
 */
public class Insertar{

    public static String ano_mes_dia = "yyyy/MM/dd";
    static SimpleDateFormat formatoBasicoFecha = new SimpleDateFormat(ano_mes_dia);

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

    public static void crearPrestamo (int idLibro, int idSocio){

        int plazoDevolucion = 10;

        Calendar calendar = Calendar.getInstance();
        Date diaPrestamo = calendar.getTime();
        String fechaPrestamo = formatoBasicoFecha.format(diaPrestamo);
        calendar.add(GregorianCalendar.DAY_OF_YEAR,plazoDevolucion);
        Date diaDevolucion = calendar.getTime();
        String fechaDevolucion = formatoBasicoFecha.format(diaDevolucion);

        Prestamo prestamo = new Prestamo(
                DBControl.Consultar.LibroPorId(idLibro)
                , DBControl.Consultar.SocioPorId(idSocio)
                , fechaPrestamo
                , fechaDevolucion);

        nuevoPrestamo(prestamo);
    }
}
