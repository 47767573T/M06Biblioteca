import DBControl.Acceso;
import DBObjects.Libros;
import DBObjects.Prestamo;
import DBObjects.Socios;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Moises on 14/01/2016.
 */
public class MainTest {

    public static Acceso acceso;
    public static Random rnd = new Random();

    public static String ano_mes_dia = "yyyy/MM/dd";
    static SimpleDateFormat formatoBasicoFecha = new SimpleDateFormat(ano_mes_dia);

    public static int numSocios = 10;
    public static int numLibros = 50;
    public static int numPrestamos = 5;

    public static void main(final String[] args) throws Exception {

        //Configuracion y acceso inicial a la base de datos
        Configuration conf = new Configuration().configure();
        acceso = new Acceso(conf);
        Session session = acceso.getSession("MainTest");

        ComprobarTablas(session);       //De esta forma comprobamos la conexion y la creacion de tablas mediante consola

        //INSERTAR:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        autoInsertLibros();
        autoInsertSocios();
        autoInsertPrestamos();

        //ELIMINAR:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //DBControl.Eliminar.libroPorId(1);
        //DBControl.Eliminar.socioPorId(1);
        //DBControl.Eliminar.PrestamosPorId(1);

        //CONSULTAS SIMPLES::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //Libros.................................................................................
        System.out.println("flag1");
        List<Libros> consulta1 = DBControl.Consultar.queryLibros("0", "7", "0", "0", "0");
        System.out.println(consulta1.size()+" Resultados");
        for (int i = 0; i < consulta1.size(); i++) {
            Libros libro = consulta1.get(i);

            System.out.println("\t"+libro.getTitulo()+" ("+libro.getEditorial()+") - edicion del "+libro.getAnoEdicion());
        }
/*
        List<Object> consulta2 = DBControl.Consultar.querySimple("Socios", "edad", " ");
        for (int i = 0; i < consulta2.size(); i++) {
            Socios socio = (Socios)consulta2.get(i);
            System.out.println("\t"+socio.getNombre()+" "+socio.getApellido()+" ("+socio.getEdad()+") con tlfn: "+socio.getTelefono());
        }

        List<Object> consulta3 = DBControl.Consultar.querySimple("Prestamo", "idlibro_id", "1");
        for (int i = 0; i < consulta3.size(); i++) {
            Prestamo prestamo = (Prestamo)consulta3.get(i);
            System.out.println("\t("+prestamo.getFechaFin()+" - "+prestamo.getFechaIni()+") de ID="+prestamo.getId());
        }

        //MODIFICAR
        DBControl.Modificar.querySimple("Libros", "anoedicion", "1999", "anoedicion", "2001");
        DBControl.Modificar.querySimple("Socios", "direccion", "direccion4", "apellido", "apellido4");
        DBControl.Modificar.querySimple("Prestamo", "idsocio_id", "1", "idlibro_id", "2");

        acceso.Apagar(); //Cerramos el sessionFactory al cerrar la aplicacion

        //CONSULTA MULTIPLES:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
       /* List<Object> consultaMulti1 = DBControl.Consultar.queryCompleja("Libros", "titulo", "tit2");
        for (int i = 0; i < consultaMulti1.size(); i++) {
            Libros libro = (Libros)consultaMulti1.get(i);
            System.out.println("\t"+libro.getTitulo()+" ("+libro.getEditorial()+") - edicion del "+libro.getAnoEdicion());
        }
*/
    }

    public static void ComprobarTablas (Session session){
        try {
            System.out.println("buscando las entidades...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("\tencontrada" +
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