import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

//anotaciones lombok para setter and getter de todos los atributos
@Setter  
@Getter
public class Cliente extends Persona {

	private List <Incidente> incidentes = new ArrayList<>();
	private String cuit;
	private ServicioContratado contratacion;
	
	public Cliente(String id, String nombre, String domicilio, String telefono, String email, String cuit, ServicioContratado contratacion) {
		super(id, nombre, domicilio, telefono, email);
		this.cuit = cuit;
		this.contratacion = contratacion;
	}
	
	public void modificacionCliente() {}
	
	public void bajaCliente() {}

}
