package DBObjects;

import com.sun.istack.internal.NotNull;
import org.hibernate.mapping.Constraint;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by Moises on 16/01/2016.
 */

@Entity
public class Libros implements Serializable{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    //@Column (name = "titulo")
    private String titulo;
    //@Column (name = "unidades")
    private String unidades;
    //@Column (name = "editorial")
    private String editorial;
    //@Column (name = "paginas")
    private String paginas;
    //@Column (name = "anoEdicion")
    private String anoEdicion;

    //CONSTRUCTORES
    public Libros(String titulo, String unidades, String editorial, String paginas, String anoEdicion) {
        this.titulo = titulo;
        this.unidades = unidades;
        this.editorial = editorial;
        this.paginas = paginas;
        this.anoEdicion = anoEdicion;
    }


    public Libros(){}

    public Libros(int id){
        this.id = id;
    }

    // GETTERS & SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getAnoEdicion() {
        return anoEdicion;
    }

    public void setAnoEdicion(String anoEdicion) {
        this.anoEdicion = anoEdicion;
    }

    // METODOS GENERALES


    @Override
    public String toString() {
        return id+" - "+titulo+ " de "+paginas+"pags. "
                +"\n Ed. "+editorial+" fueron editadas "+unidades+" en "+anoEdicion ;
    }
}
