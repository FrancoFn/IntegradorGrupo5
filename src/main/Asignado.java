package main;

import java.util.Scanner;

import main.Entidades.Incidente;

public class Asignado implements IEstado {
	
	Scanner teclado = new Scanner (System.in);
	
	@Override
	public void Asignado(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println ("El incidente ya se encuentra asignado.");
		//incidente.setEstadoInc(EstadoIncidente.ASIGNADO);
		//Enviar mensaje por mail al Tecnico.
	}

	@Override
	public void EnCurso(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println("Ingrese los comentarios que crean necesarios:");
		String consideraciones = teclado.nextLine();
		
		incidente.setEstadoInc(EstadoIncidente.EN_CURSO);
	}

	@Override
	public void Resuelto(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println("El indidente se encuentra asignado. Primero debe darle curso para poder finalizarlo.");
	};
}
