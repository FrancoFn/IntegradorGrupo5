package main.Entidades;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	//@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	//@JoinColumn(name = "tecnico_id") //Esto indica la columna en la tabla de Incidente que representa la relación
	//private Tecnico encargado;
	@Column
	private Estado estado;
		
	// Constructor por defecto (necesario para Hibernate)
    public Incidente() {
    }
	
	public Incidente(String id, String descripcion, String tiempoResolucion, String consideraciones, Cliente cliente,
		 Estado estado) {//Tecnico encargado,
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.tiempoResolucion = tiempoResolucion;
		this.consideraciones = consideraciones;
		this.cliente = cliente;
		//this.encargado = encargado;
		this.estado = estado;
	}
			
}
