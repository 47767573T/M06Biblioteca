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

    public static void querySimple (String tabla, String colConsultar, String itemDeConsulta
            , String colModificar, String itemNuevo, int type) {

        if (!UltimaConsulta.isEmpty()) UltimaConsulta.clear();

        Session session = Acceso.getSession("Modificar_" + tabla);
        Query query = session.createQuery("update " + tabla + " set " + colModificar + " = :valorModificar"
                + " where " + colConsultar + " =:valorConsultar");

        switch (type) {
            case 0:
                query.setParameter("valorModificar", itemNuevo);
                query.setParameter("valorConsultar", itemDeConsulta);
                System.out.println("Modificado de "+tabla+"_"+colModificar+" a valor="+itemNuevo+" cuando "
                        +colConsultar+" sea valor="+itemDeConsulta);

                session.getTransaction().commit();
                break;

            case 1:
                query.setParameter("valorModificar", Integer.parseInt(itemNuevo));
                query.setParameter("valorConsultar", itemDeConsulta);
                System.out.println("Modificado de "+tabla+"_"+colModificar+" a valor="+itemNuevo+" cuando "
                        +colConsultar+" sea valor="+itemDeConsulta);
                session.update()
                session.getTransaction().commit();
                break;

            default:
                System.out.println("Modificado en " + tabla + " de " + colModificar + " tipo de dato erróneo");
                break;
        }
    }
}
