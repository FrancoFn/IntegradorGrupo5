package main;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import main.Entidades.Incidente;

public class EnCurso implements IEstado {

	@Override
	public void Asignado(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println ("El incidente se encuentra en curso no puede volver a asignarse.");
	}

	@Override
	public void EnCurso(Incidente incidente) {
		// TODO Auto-generated method stub
		System.out.println ("El incidente ya se encuentra en curso.");
	}

	@Override
	public void Resuelto(Incidente incidente) {
		// TODO Auto-generated method stub
		incidente.setEstadoInc(EstadoIncidente.RESUELTO);
		incidente.setFechaFinalizacion(new Date());
		
		EntityManager em = ManagerPersistence.getEntityManager();
        EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(incidente);
        tx.commit();
		//Mandar mensaje por mail al cliente;
	}
}
