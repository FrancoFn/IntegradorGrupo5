package main;

import main.Entidades.Incidente;

public class Resuelto implements IEstado {

	@Override
	public void asignar (Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println ("El incidente ya se encuentra resulto.");
	}

	@Override
	public void enDesarrollo(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println ("El incidente ya se encuentra resulto.");
	}

	@Override
	public void finalizado(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println ("El incidente ya se encuentra resulto.");
	}
}
