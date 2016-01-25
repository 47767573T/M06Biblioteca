package DBControl;

import DBObjects.Libros;
import DBObjects.Prestamo;
import DBObjects.Socios;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Moises on 17/01/2016.
 */
public class Consultar {

    static List<Object> UltimaConsulta = new ArrayList<>();

    public static Libros LibroPorId (int id){

        Session session = Acceso.getSession("Consultar_LibroPorId_" + id);
        session.beginTransaction();
        Query query = session.createQuery("from Libros where id = "+id);

        if (query.list().isEmpty()) {
            Libros libro = new Libros(0);
            return libro;
        }
        else { Libros libro = (Libros) query.list().get(0);
            return libro;
        }
    }

    public static Socios SocioPorId (int id){

        Session session = Acceso.getSession("Consultar_SocioPorId_" + id);
        session.beginTransaction();
        Query query = session.createQuery("from Socios where id = "+id);

        if (query.list().isEmpty()) {
            Socios socio = new Socios(0);
            return socio;
        }
        else { Socios socio = (Socios) query.list().get(0);
            return socio;
        }
    }

    public static Prestamo PrestamoPorId (int id){

        Session session = Acceso.getSession("Consultar_PrestamoPorId_" + id);
        session.beginTransaction();
        Query query = session.createQuery("from Prestamo where id = "+id);

        if (query.list().isEmpty()) {
            Prestamo prestamo = new Prestamo(0);
            return prestamo;
        }
        else { Prestamo prestamo = (Prestamo) query.list().get(0);
            return prestamo;
        }
    }

    public static List<Libros> queryLibros (String titulo, String editorial, String anoedicion, String pag
            , String unidades) {

        boolean inicioDeQuery = true;

        if (!UltimaConsulta.isEmpty()) UltimaConsulta.clear();
        Session session = Acceso.getSession("Consultar_queryLibros");

        String queryMontada = "from Libros where ";
        if (!titulo.equals("0")){
            queryMontada += "(titulo = :tit)";
            inicioDeQuery = false;
        }

        if (!editorial.equals("0") && !inicioDeQuery)queryMontada += " AND (editorial = :ed)";
        else if (!editorial.equals("0") && inicioDeQuery){
            queryMontada +="(editorial = :ed)";
            inicioDeQuery = false;
        }

        if (!anoedicion.equals("0") && !inicioDeQuery)queryMontada += " AND (anoedicion = :ano)";
        else if (!anoedicion.equals("0") && inicioDeQuery){
            queryMontada += " (anoedicion = :ano)";
            inicioDeQuery = false;
        }

        if (!pag.equals("0") && !inicioDeQuery)queryMontada += " AND (paginas = :pags)";
        else if (!pag.equals("0") && inicioDeQuery){
            queryMontada += " (paginas = :pags)";
            inicioDeQuery = false;
        }

        if (!unidades.equals("0") && !inicioDeQuery)queryMontada += " AND (unidades= :uni)";
        else if (!unidades.equals("0") && inicioDeQuery){
            queryMontada += " (unidades= :uni)";
            inicioDeQuery = false;
        }

        if (titulo.equals("0") && editorial.equals("0") && anoedicion.equals("0") && pag.equals("0")
                && unidades.equals("0")){
            queryMontada = "from Libros";
        }

        System.out.println(queryMontada);
        Query query = session.createQuery(queryMontada);

        if (!titulo.equals("0")) query.setParameter("tit", titulo);
        if (!editorial.equals("0")) query.setParameter("ed",editorial);
        if (!anoedicion.equals("0"))query.setParameter("ano",anoedicion);
        if (!pag.equals("0"))query.setParameter("pags",pag);
        if (!unidades.equals("0"))query.setParameter("uni",unidades);

        System.out.println(queryMontada);
        query.list();

        return query.list();
    }

    public static List<Socios> querySocios (String nombre, String apellido, String direccion, String telefono){

        boolean inicioDeQuery = true;

        Session session = Acceso.getSession("Consultar_querySocios");

        String queryMontada = "from Socios where ";
        if (!nombre.equals("0")){
            queryMontada += "(nombre = :nom)";
            inicioDeQuery = false;
        }

        if (!apellido.equals("0") && !inicioDeQuery)queryMontada += " AND (apellido = :cognom)";
        else if (!apellido.equals("0") && inicioDeQuery){
            queryMontada +="(apellido = :cognom)";
            inicioDeQuery = false;
        }

        if (!direccion.equals("0") && !inicioDeQuery)queryMontada += " AND (direccion = :dir)";
        else if (!direccion.equals("0") && inicioDeQuery){
            queryMontada += " (direcion = :dir)";
            inicioDeQuery = false;
        }

        if (!telefono.equals("0") && !inicioDeQuery)queryMontada += " AND (telefono = :tlfn)";
        else if (!telefono.equals("0") && inicioDeQuery){
            queryMontada += " (telefono = :tlfn)";
            inicioDeQuery = false;
        }

        if (nombre.equals("0") && apellido.equals("0") && direccion.equals("0") && telefono.equals("0")){
            queryMontada = "from Socios";
        }

        System.out.println(queryMontada);
        Query query = session.createQuery(queryMontada);

        if (!nombre.equals("0")) query.setParameter("tit", nombre);
        if (!apellido.equals("0")) query.setParameter("cognom",apellido);
        if (!direccion.equals("0"))query.setParameter("dir",apellido);
        if (!telefono.equals("0"))query.setParameter("tlfn",telefono);

        System.out.println(queryMontada);
        query.list();

        return query.list();
    }

    public static List<Prestamo> queryPrestamos (int idLibro, int idSocio, String fechaIni, String fechaFin){

        boolean inicioDeQuery = true;

        Session session = Acceso.getSession("Consultar_queryPrestamos");

        String queryMontada = "from Prestamo where ";
        if (idLibro != 0){
            queryMontada += "(idLibro.id = :idl)";
            inicioDeQuery = false;
        }

        if (idSocio != 0 && !inicioDeQuery)queryMontada += " AND (idSocio.id = :ids)";
        else if (idSocio != 0 && inicioDeQuery){
            queryMontada += "(idSocio.id = :ids)";
            inicioDeQuery = false;
        }

        if (!fechaIni.equals("0") && !inicioDeQuery)queryMontada += " AND (fechaIni = :ini)";
        else if (!fechaIni.equals("0") && inicioDeQuery) {
            queryMontada += " (fechaIni = :ini)";
            inicioDeQuery = false;
        }

        if (!fechaFin.equals("0") && !inicioDeQuery)queryMontada += " AND (fechaFin = :fin)";
        else if (!fechaFin.equals("0") && inicioDeQuery){
            queryMontada += " (fechaFin = :fin)";
            inicioDeQuery = false;
        }

        if (idSocio != 0 && idLibro == 0 && fechaFin.equals("0") && fechaIni.equals("0")){
            queryMontada = "from Prestamo where idSocio.id = :ids";
        }

        Query query = session.createQuery(queryMontada);

        if (idLibro != 0) query.setParameter("idl", idLibro);
        if (idSocio != 0) query.setParameter("ids", idSocio);
        if (!fechaIni.equals("0"))query.setParameter("ini", fechaIni);
        if (!fechaFin.equals("0"))query.setParameter("fin", fechaFin);

        System.out.println(queryMontada);
        query.list();

        return query.list();
    }

}
