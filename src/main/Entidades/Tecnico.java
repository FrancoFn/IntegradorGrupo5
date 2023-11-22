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
	
	public Tecnico(int id, String nombre, String domicilio, long telefono, String email, int estado, List<Especialidad> especialidades) {
		super(id, nombre, domicilio, telefono, email,estado);
		this.especialidades = especialidades;
		this.disponibilidad = true;
	}
	
	public void revisarIncidente () {}
	
	public void resolverIncidente () {}
}
