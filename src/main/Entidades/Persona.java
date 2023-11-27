package main.Entidades;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
//@Entity
@Table(name = "persona") // Nombre de la tabla en la base de datos
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //JOINED) Estrategia de herencia
@MappedSuperclass
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "telefono")
    private long telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "estado")
    private int estado; //0 dado de baja - 1 activo

    public Persona() {
    }

    //Constructor
    public Persona(String nombre, String domicilio, long telefono, String email, int estado) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }
}
