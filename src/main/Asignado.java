package main;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import main.Entidades.Incidente;

public class Asignado implements IEstado {
	
	Scanner teclado = new Scanner (System.in);
	
	@Override
	public void asignar(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println ("El incidente ya se encuentra asignado.");
		incidente.setEstadoInc(EstadoIncidente.ASIGNADO);
		
		EntityManager em = ManagerPersistence.getEntityManager();
        EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(incidente);
        tx.commit();
		
		//Enviar mensaje por mail al Tecnico.
	}

	@Override
	public void enDesarrollo(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println("Ingrese los comentarios que crean necesarios:");
		String consideraciones = teclado.nextLine();
		
		incidente.setEstadoInc(EstadoIncidente.EN_CURSO);
		incidente.setConsideraciones(consideraciones);
		
		EntityManager em = ManagerPersistence.getEntityManager();
        EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(incidente);
        tx.commit();
	}

	@Override
	public void finalizado(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println("El indidente se encuentra asignado. Primero debe darle curso para poder finalizarlo.");
	};
}
