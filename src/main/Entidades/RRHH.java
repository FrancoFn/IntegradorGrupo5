package main.Entidades;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter  
@Getter
public class RRHH extends Persona {
	
	public void altaTecnico (Tecnico tecnico) {}
	
	public void modificacionTecnico (Tecnico tecnico) {}
	
	public void bajaTecnico (Tecnico tecnico) {}
	
}
