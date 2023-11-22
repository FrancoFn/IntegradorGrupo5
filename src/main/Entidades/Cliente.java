package main.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Cliente extends Persona {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidente> incidentes = new ArrayList<>();
    @Column(name = "cuit")
    private long cuit;
    @Column
    private ServicioContratado contratacion;

    // Constructor por defecto (necesario para Hibernate)
    public Cliente() {
    }

    // Constructor
    public Cliente(int id, String nombre, String domicilio, long telefono, String email, long cuit, int estado, ServicioContratado contratacion) {
        super(id, nombre, domicilio, telefono, email, estado);
        this.cuit = cuit;
        this.contratacion = contratacion;
    }

    // Método para ingresar un nuevo cliente
    public void altaCliente() {
        // Lógica para ingresar un nuevo cliente
        // Por ejemplo:
        // Simplemente imprimir un mensaje para demostración
        System.out.println("Cliente dado de alta: " + this.getNombre());
    }
    

    // Método para modificar datos del cliente
    public void modificacionCliente(String nuevoNombre, String nuevoDomicilio, long nuevoTelefono, String nuevoEmail) {
        // Lógica para modificar datos del cliente
        // Por ejemplo:
        // Actualizar los datos del cliente con los nuevos valores proporcionados
        this.setNombre(nuevoNombre);
        this.setDomicilio(nuevoDomicilio);
        this.setTelefono(nuevoTelefono);
        this.setEmail(nuevoEmail);
        System.out.println("Datos modificados para el cliente: " + this.getNombre());
    }

    // Método para cambiar el estado del cliente de 1 a 0
    public void bajaCliente() {
        // Lógica para cambiar el estado del cliente de 1 a 0
        // Por ejemplo:
        // Cambiar el estado del cliente a inactivo
        this.setEstado(0);
        System.out.println("Cliente dado de baja: " + this.getNombre());
    }
}
