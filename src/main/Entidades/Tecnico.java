package main.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;


import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Tecnico extends Persona {

	@ElementCollection
	@CollectionTable(name = "tecnico_especialidades", joinColumns = @JoinColumn(name = "tecnico_id"))

	@Column
	private List<Especialidad> especialidades = new ArrayList<>();
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Incidente> incidentes = new ArrayList<>();
	@Column
	private boolean disponibilidad;

	public Tecnico() {
	}

	public Tecnico(String nombre, String domicilio, long telefono, String email, int estado, List<Especialidad> especialidades) {
		super(nombre, domicilio, telefono, email,estado);
		this.especialidades = especialidades;
		this.disponibilidad = true;
	}

	public void altaTecnico() {
		System.out.println("Tecnico dado de alta: " + this.getNombre());
	}

	public void modificarTecnico (String nuevodomicilio, long telefono, String email,List<Especialidad> especialidades) {
		this.setDomicilio(nuevodomicilio);
		this.setTelefono(telefono);
		this.setEmail(email);
		this.setEspecialidades(especialidades);
	}

	public void bajaTecnico() {
		this.setEstado(0);
		System.out.println("Tecnico dado de baja: " + this.getNombre());
	}

	public void revisarIncidente () {}

	public void resolverIncidente () {}
}