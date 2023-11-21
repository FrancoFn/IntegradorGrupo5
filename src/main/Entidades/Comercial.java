package main.Entidades;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

//anotaciones lombok para setter and getter de todos los atributos
@Setter  
@Getter
public class Comercial extends Persona {
    
	private List<Cliente> clientes = new ArrayList<>();
	
	public Comercial(String id, String nombre, String domicilio, String telefono, String email) {
		super(id, nombre, domicilio, telefono, email);
		// TODO Auto-generated constructor stub
	}
	
	public void altaCliente (Cliente cliente) {
	}
	
	public void modificacionCliente (Cliente cliente) {
	}
	
	public void bajaCliente (Cliente cliente) {
	}
}
