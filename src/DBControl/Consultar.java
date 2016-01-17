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

    public static List<Object> libroPorTitulo(String titulo){
        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        Session session = Acceso.getSession("Consultar_libroPorTitulo");
        Query query = session.createQuery("from Libros where titulo= :t");
        query.setParameter("t", titulo);

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }

    public static List<Object> libroPorEditorial(String editorial){
        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        Session session = Acceso.getSession("Consultar_libroPorEditorial");
        Query query = session.createQuery("from Libros where editorial= :e");
        query.setParameter("e", editorial);

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }

    public static List<Object> libroPorAnoEdicion(int ano){
        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        Session session = Acceso.getSession("Consultar_libroPorAnoEdicion");
        Query query = session.createQuery("from Libros where anoEdicion= :e");
        query.setParameter("e", ano);

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }

}
