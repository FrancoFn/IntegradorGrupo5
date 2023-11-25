package main.Entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
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
	@Column
	private Date fechaOrigen;
	@Column
	private Especialidad categoria;
		
	// Constructor por defecto (necesario para Hibernate)
    public Incidente() {
    }
	
	public Incidente(String descripcion, String tiempoResolucion, String consideraciones, Cliente cliente,
			List<Tecnico> tecnico,Estado estado, Date fechaOrigen, Especialidad categoria) {
		this.descripcion = descripcion;
		this.tiempoResolucion = tiempoResolucion;
		this.consideraciones = consideraciones;
		this.cliente = cliente;
		this.tecnico = tecnico;
		this.estado = estado;
		this.fechaOrigen = fechaOrigen;
		this.categoria = categoria;
	}
	
	public void creacionIncidente (Cliente cliente, Especialidad categoria, String descripcion) {
		this.cliente = cliente;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fechaOrigen = new Date();
		//this.estado = Asignado;
	}
			
}
