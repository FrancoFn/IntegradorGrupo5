package main.Entidades;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Empresa {

	private String nombre;
	private String cuit;
	private String domicilio;
	private List<Tecnico> areaTecnica = new ArrayList<>();
	private List<Cliente> clientes = new ArrayList<>();

}
