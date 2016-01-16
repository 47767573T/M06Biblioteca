
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Moises on 16/01/2016.
 */

@Entity
public class Prestamo implements Serializable{

    @Id
    @GeneratedValue

    @OneToOne(targetEntity = Libros.class, fetch = FetchType.LAZY)
    private Libros idLibro;

    @OneToOne(targetEntity = Socios.class, fetch = FetchType.LAZY)
    private Socios idSocio;


    private Date fechaIni;


    private Date fechaFin;



    //CONSTRUCTORES
    public Prestamo(Socios idSocio, Date fechaIni, Date fechaFin) {
        this.idSocio = idSocio;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public Prestamo(){}

    // GETTERS & SETTERS
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

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
