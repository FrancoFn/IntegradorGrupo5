package main.Entidades;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter  
@Getter
public class MesaDeAyuda extends Persona {
	
	public void mostrarServicioCliente (Cliente cliente) {
	}
	
	public void cargarIncidente(Incidente incidente) {
	}
	
}
