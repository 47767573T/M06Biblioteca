import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by Moises on 16/01/2016.
 */

@Entity
//@Table(name = "libros")
public class Libros implements Serializable{

    @Id
    @GeneratedValue
    private int id;
    //@Column (name = "titulo")
    private String titulo;
    //@Column (name = "unidades")
    private int unidades;
    //@Column (name = "editorial")
    private String editorial;
    //@Column (name = "paginas")
    private int paginas;
    //@Column (name = "anoEdicion")
    private int anoEdicion;

    //CONSTRUCTORES
    public Libros(String titulo, int unidades, String editorial, int paginas, int anoEdicion) {
        this.titulo = titulo;
        this.unidades = unidades;
        this.editorial = editorial;
        this.paginas = paginas;
        this.anoEdicion = anoEdicion;
    }

    public Libros(){}

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

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getAnoEdicion() {
        return anoEdicion;
    }

    public void setAnoEdicion(int anoEdicion) {
        this.anoEdicion = anoEdicion;
    }
}
