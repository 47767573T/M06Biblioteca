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
        Query query = session.createQuery("from Libros where titulo= :x");
        query.setParameter("x", titulo);

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }

    public static List<Object> libroPorEditorial(String editorial){
        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        Session session = Acceso.getSession("Consultar_libroPorEditorial");
        Query query = session.createQuery("from Libros where editorial= :x");
        query.setParameter("x", editorial);

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }

    public static List<Object> libroPorAnoEdicion(int ano){
        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        Session session = Acceso.getSession("Consultar_libroPorAnoEdicion");
        Query query = session.createQuery("from Libros where anoEdicion= :x");
        query.setParameter("x", ano);

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }

    public static List<Object> socioPorNombre(String nombre){
        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        Session session = Acceso.getSession("Consultar_socioPorNombre");
        Query query = session.createQuery("from Socios where nombre= :x");
        query.setParameter("x", nombre);

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }

    public static List<Object> socioPorApellido(String apellido){
        if (!UltimaConsulta.isEmpty())UltimaConsulta.clear();

        Session session = Acceso.getSession("Consultar_socioPorApellido");
        Query query = session.createQuery("from Socios where apellido= :x");
        query.setParameter("x", apellido);

        UltimaConsulta = query.list();

        return UltimaConsulta;
    }

}
