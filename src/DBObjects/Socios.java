package DBObjects;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Moises on 16/01/2016.
 */

@Entity
//@Table(name = "socios")
public class Socios implements Serializable{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    //@Column (name = "nombre")
    String nombre;
    //@Column (name = "apellido")
    String apellido;
    //@Column (name = "edad")
    int edad;
    //@Column (name = "direccion")
    String direccion;
    //@Column (name = "telefono")
    int telefono;


    //CONSTRUCTORES
    public Socios(String nombre, String apellido, int edad, String direccion, int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Socios(){}

    public Socios(int id){
        this.id = id;
    }


    //GETTERS & SETTERS
    public int getId() { return id; }

    public void setId(int id) { this.id = id;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
