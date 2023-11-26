package main;

import main.Entidades.Incidente;

public interface IEstado {
	public abstract void Asignado(Incidente incidente);
	
	public abstract void EnCurso(Incidente incidente);
	
	public abstract void Resuelto(Incidente incidente);
	
	//void proceso(Incidente incidente);
}