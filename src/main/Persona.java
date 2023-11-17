import lombok.Getter;
import lombok.Setter;

//anotaciones lombok para setter and getter de todos los atributos
@Setter  
@Getter
public abstract class Persona {
	private String id;
	private String nombre;
	private String domicilio;
	private String telefono;
	private String email;
	
	//Constructor
	public Persona(String id, String nombre, String domicilio, String telefono, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.email = email;
	}
}
