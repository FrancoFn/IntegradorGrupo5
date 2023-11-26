package main;

import main.Entidades.Incidente;

public class Resuelto implements IEstado {

	@Override
	public void Asignado(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println ("El incidente ya se encuentra resulto.");
	}

	@Override
	public void EnCurso(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println ("El incidente ya se encuentra resulto.");
	}

	@Override
	public void Resuelto(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println ("El incidente ya se encuentra resulto.");
	}
}
