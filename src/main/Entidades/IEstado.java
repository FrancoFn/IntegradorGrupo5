package main.Entidades;
public interface IEstado {
	public abstract void Asignado(Incidente incidente);
	
	public abstract void EnCurso(Incidente incidente);
	
	public abstract void Resuelto(Incidente incidente);
}
