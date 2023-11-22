package main.Entidades;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

//anotaciones lombok para setter and getter de todos los atributos
@Setter  
@Getter
public class RRHH extends Persona {
	
	private List<Tecnico> tecnicos = new ArrayList<>();
	
	public void altaTecnico (Tecnico tecnico) {}
	
	public void modificacionTecnico (Tecnico tecnico) {}
	
	public void bajaTecnico (Tecnico tecnico) {}
	
	public List<Incidente> verIncidentes () {
	return null;
	}
}
