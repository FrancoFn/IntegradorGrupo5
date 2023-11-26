package main.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import main.ServicioContratado;

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
    public Cliente(String nombre, String domicilio, long telefono, String email, long cuit, int estado, ServicioContratado contratacion) {
        super(nombre, domicilio, telefono, email, estado);
        this.cuit = cuit;
        this.contratacion = contratacion;
    }

    // Método para ingresar un nuevo cliente
    public void altaCliente() {
        System.out.println("Cliente dado de alta: " + this.getNombre());
    }
    

    // Método para modificar datos del cliente
    public void modificarCliente(String nuevoDomicilio, long nuevoTelefono, String nuevoEmail,ServicioContratado contratacion) {
        this.setDomicilio(nuevoDomicilio);
        this.setTelefono(nuevoTelefono);
        this.setEmail(nuevoEmail);
        this.contratacion = contratacion;
        System.out.println("Datos modificados para el cliente: " + this.getNombre());
    }

    // Método para cambiar el estado del cliente de 1 a 0
    public void bajaCliente() {
        this.setEstado(0);
        System.out.println("Cliente dado de baja: " + this.getNombre());
    }
    
    
}
