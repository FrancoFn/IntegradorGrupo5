package main.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Incidente {
	@Id
	private String id;
	@Column
	private String descripcion;
	@Column
	private String tiempoResolucion;
	@Column
	private String consideraciones;
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "cliente_id") //Esto indica la columna en la tabla de Incidente que representa la relación
	private Cliente cliente;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Tecnico> tecnico = new ArrayList<>();
	@Column
	private Estado estado;

	// Constructor por defecto (necesario para Hibernate)
	public Incidente() {
	}

	public Incidente(String id, String descripcion, String tiempoResolucion, String consideraciones, Cliente cliente,
					 List<Tecnico> tecnico,Estado estado) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.tiempoResolucion = tiempoResolucion;
		this.consideraciones = consideraciones;
		this.cliente = cliente;
		this.tecnico = tecnico;
		this.estado = estado;
	}

}
