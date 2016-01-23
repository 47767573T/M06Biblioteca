package DBControl;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moises on 17/01/2016.
 */
public class Modificar {

    static List<Object> UltimaConsulta = new ArrayList<>();

    //public static void insertarSocio(){ }

    public static void querySimple (String tabla, String colConsultar, String itemDeConsulta
            , String colModificar, String itemNuevo) {

        if (!UltimaConsulta.isEmpty()) UltimaConsulta.clear();


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

        int result = query.executeUpdate();
        System.out.println(result+" campos afectados");
        session.getTransaction().commit();
    }
}
