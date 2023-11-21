package main.Entidades;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

//anotaciones lombok para setter and getter de todos los atributos
@Setter  
@Getter
public class Tecnico extends Persona {
	
	private List<Especialidad> especialidades = new ArrayList<>();
//	private List<Incidente> incidentes = new ArrayList<>();
	private boolean disponibilidad; 
	
	public Tecnico(int id, String nombre, String domicilio, long telefono, String email, int estado, List<Especialidad> especialidades) {
		super(id, nombre, domicilio, telefono, email,estado);
		this.especialidades = especialidades;
		this.disponibilidad = true;
	}
	
	public void revisarIncidente () {}
	
	public void resolverIncidente () {}
}
