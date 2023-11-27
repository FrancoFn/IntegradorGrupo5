package main;

import main.Entidades.Incidente;

public interface IEstado {
	public void asignar(Incidente incidente);
	
	public void enDesarrollo(Incidente incidente);
	
	public void finalizado(Incidente incidente);
	
	//void proceso(Incidente incidente);
}
