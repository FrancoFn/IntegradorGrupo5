package main;
import lombok.Getter;
import lombok.Setter;
import main.Entidades.Cliente;
import main.Entidades.Tecnico;

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
