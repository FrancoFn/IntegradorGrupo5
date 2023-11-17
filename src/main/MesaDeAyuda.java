import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

//anotaciones lombok para setter and getter de todos los atributos
@Setter  
@Getter
public class MesaDeAyuda extends Persona {
	

	private List<Incidente> incidentes = new ArrayList<>();
	private List<Cliente> clientes = new ArrayList<>();
	
	public MesaDeAyuda(String id, String nombre, String domicilio, String telefono, String email) {
		super(id, nombre, domicilio, telefono, email);
		// TODO Auto-generated constructor stub
	}
	
	public void mostrarServicioCliente (Cliente cliente) {
	}
	
	public void cargarIncidente(Incidente incidente) {
	}
	
}
