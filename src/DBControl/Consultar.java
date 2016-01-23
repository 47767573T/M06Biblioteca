package DBControl;

import DBObjects.Libros;
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

        List<Libros> libros = query.list();
        System.out.println(libros.get(0).getTitulo());

        Libros libro = libros.get(0);

        return libro;
    }

    public static Socios SocioPorId (int id){

        Session session = Acceso.getSession("Consultar_SocioPorId_" + id);
        session.beginTransaction();
        Query query = session.createQuery("from Socios where id = "+id);

        Socios socio = (Socios) query.list().get(0);

        return socio;
    }

    public static List<Object> querySimple (String tabla, String columna, String itemDeBusqueda){
        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        //Realizamos la conexion y montamos la query
        Session session = Acceso.getSession("Consultar_" + tabla + "_" + columna + "_" + itemDeBusqueda);
        Query query = session.createQuery("from "+tabla+" where "+columna+" = :x");

        //Si los parametros de consulta son para el campo ID cambiamos el tipo de variable a Integer
        if (columna.startsWith("id")) {query.setParameter("x",(Integer.parseInt(itemDeBusqueda)));
        }else{query.setParameter("x",itemDeBusqueda);}

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }

    public static List<Object> queryCompleja (String tabla, String colConsultar, String itemDeConsulta
            , String colModificar, String itemNuevo) {
        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        Session session = Acceso.getSession("Modificar_" + tabla);
        session.beginTransaction();
        Query query = session.createQuery("update " + tabla + " set " + colModificar + " = :valorModificar"
                + " where " + colConsultar + " =:valorConsultar");


        //Si los parametros de consulta son para el campo ID cambiamos el tipo de variable a Integer
        //Validamos el tipo de variable pide en el campo a consultar
        if (colConsultar.startsWith("id")){
            query.setParameter("valorConsultar", Integer.parseInt(itemDeConsulta));
        }else{
            query.setParameter("valorConsultar", itemDeConsulta);
        }

        //Validamos el tipo de variable pide en el campo a modificar
        if (colModificar.startsWith("id")){
            query.setParameter("valorModificar", Integer.parseInt(itemNuevo));
        }else{
            query.setParameter("valorModificar", itemNuevo);
        }

        System.out.println("Modificado de "+tabla+"_"+colModificar+" a valor="+itemNuevo+" cuando "
                +colConsultar+" sea valor="+itemDeConsulta);

        return UltimaConsulta;
    }


    }
