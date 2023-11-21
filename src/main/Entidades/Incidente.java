package main.Entidades;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Incidente {
	private String id;
	private String descripcion;
	private String tiempoResolucion;
	private String consideraciones;
	private Cliente cliente;
	private Tecnico encargado;
	private Estado estado;
		
	public Incidente(String id, String descripcion, String tiempoResolucion, String consideraciones, Cliente cliente,
			Tecnico encargado, Estado estado) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.tiempoResolucion = tiempoResolucion;
		this.consideraciones = consideraciones;
		this.cliente = cliente;
		this.encargado = encargado;
		this.estado = estado;
	}
			
}
