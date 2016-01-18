package DBControl;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moises on 17/01/2016.
 */
public class Consultar {

    static List<Object> UltimaConsulta = new ArrayList<>();
/*
    public static List<Object> libroPorTitulo(String titulo){
        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        Session session = Acceso.getSession("Consultar_libroPorTitulo");
        Query query = session.createQuery("from Libros where titulo= :x");
        query.setParameter("x", titulo);

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }
*/
    public static List<Object> querySimple (String tabla, String columna, String itemDeBusqueda, int type){

        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        Session session = Acceso.getSession("Consultar_"+tabla+"_"+columna+"_"+itemDeBusqueda);
        Query query = session.createQuery("from "+tabla+" where "+columna+" = :x");

        if (type == 0) query.setParameter("x", itemDeBusqueda);
        if (type == 1) query.setParameter("x", Integer.parseInt(itemDeBusqueda));
        if (type == 2) query.

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }


}
