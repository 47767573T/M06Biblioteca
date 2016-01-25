package DBInterfaz;

import DBControl.Acceso;
import DBObjects.Libros;
import DBObjects.Prestamo;
import DBObjects.Socios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by Moises on 23/01/2016.
 */
public class InterfazTest {

    public static Acceso acceso;
    public static Random rnd = new Random();

    public static int numSocios = 10;
    public static int numLibros = 50;
    public static int numPrestamos = 5;

    public static String ano_mes_dia = "yyyy/MM/dd";
    static SimpleDateFormat formatoBasicoFecha = new SimpleDateFormat(ano_mes_dia);


    public static void autoInsertSocios(){
        int contador = numSocios;

        for (int i = 1; i < contador+1; i++) {
            Socios socio = new Socios(
                    "nom"+i
                    , "cognom"+i
                    , Integer.toString(rnd.nextInt(100))
                    , "direccion"+i
                    , "91111111"+i);
            DBControl.Insertar.nuevoSocio(socio);
        }
    }

    public static void autoInsertLibros(){
        int contador = numLibros;

        for (int i = 1; i < contador+1; i++) {
            Libros libro = new Libros(
                    "titulo"+i
                    , Integer.toString(rnd.nextInt(1000))
                    , Integer.toString(rnd.nextInt(10))
                    , Integer.toString(rnd.nextInt(800))
                    , "199"+Integer.toString(rnd.nextInt(10)));
            DBControl.Insertar.nuevoLibro(libro);

        }
    }

    public static void autoInsertPrestamos(){
        int contador = numPrestamos;

        Calendar calendar = Calendar.getInstance();
        Date diaPrestamo = calendar.getTime();
        String fechaPrestamo = formatoBasicoFecha.format(diaPrestamo);
        calendar.add(GregorianCalendar.DAY_OF_YEAR,10);

        for (int i = 1; i < contador+1; i++) {

            calendar.add(GregorianCalendar.DAY_OF_YEAR,rnd.nextInt(100)+1);
            Date diaDevolucion = calendar.getTime();
            String fechaDevolucion = formatoBasicoFecha.format(diaDevolucion);

            Prestamo prestamo = new Prestamo(
                    DBControl.Consultar.LibroPorId(rnd.nextInt(numLibros)+1)
                    , DBControl.Consultar.SocioPorId(rnd.nextInt(numSocios)+1)
                    , fechaPrestamo
                    , fechaDevolucion);

            DBControl.Insertar.nuevoPrestamo(prestamo);
        }
    }

}
