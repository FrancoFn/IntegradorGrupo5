package main;

import main.Entidades.Incidente;

public interface IEstado {
	public void Asignar(Incidente incidente);
	
	public void EnCurso(Incidente incidente);
	
	public void Resuelto(Incidente incidente);
	
	//void proceso(Incidente incidente);
}
