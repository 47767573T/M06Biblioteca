package DBObjects;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Moises on 16/01/2016.
 */

@Entity
public class Prestamo implements Serializable{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @ManyToOne(targetEntity = Libros.class, fetch = FetchType.EAGER)
    private Libros idLibro;

    @ManyToOne(targetEntity = Socios.class, fetch = FetchType.EAGER)
    private Socios idSocio;

    private String fechaIni;

    private String fechaFin;

    //CONSTRUCTORES
    public Prestamo(Libros idLibro, Socios idSocio, String fechaIni, String fechaFin) {
        this.idLibro = idLibro;
        this.idSocio = idSocio;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public Prestamo(){}

    public Prestamo(int id){
        this.id = id;
    }

    // GETTERS & SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdLibro(Libros idLibro) {
        this.idLibro = idLibro;
    }

    public Libros getIdLibro() {
        return idLibro;
    }

    public void setLibro(Libros idLibro) {
        this.idLibro = idLibro;
    }

    public Socios getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Socios idSocio) {
        this.idSocio = idSocio;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    // METODOS GENERALES

    @Override
    public String toString() {
        return id+" - libro:"+ idLibro.getId()+" prestado a socio "+idSocio.getId()
                +"\n Prestamos realizado el "+fechaIni+" a devolver el "+fechaFin;
    }
}
