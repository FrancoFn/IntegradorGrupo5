package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import main.Entidades.Especialidad;
import main.Entidades.Tecnico;

public class RRHH {
static Scanner teclado = new Scanner (System.in);	
	
	public static void altaTecnico () {
		EntityManager em = ManagerPersistence.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        List<Especialidad> especialidades1 = new ArrayList<>();
        especialidades1.add(Especialidad.GOOGLE);
        especialidades1.add(Especialidad.WINDOWS);
		Tecnico tecnico1 = new Tecnico("T1","Dom1",111,"t1@gmail.com",1,especialidades1);
		Tecnico tecnico2 = new Tecnico("T2","Dom2",222,"t2@gmail.com",1,especialidades1);
		tecnico1.altaTecnico();
		tecnico2.altaTecnico();
		em.persist(tecnico1);
		em.persist(tecnico2);
		tx.commit();
	}
	
	public static void modificarTecnico() {
		System.out.println ("Por favor ingrese el Id del tecnico a modificar.");
		int id = teclado.nextInt();
		List<Especialidad> especialidades2 = new ArrayList<>();
        especialidades2.add(Especialidad.TANGO);
        especialidades2.add(Especialidad.LINUX);
		EntityManager em = ManagerPersistence.getEntityManager();
		Tecnico tecnico = em.find(Tecnico.class, id);
		if (tecnico != null) {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			tecnico.modificarTecnico ("Calle Prueba1"+new Date(), 11111, "prueba1@example.com"+new Date(),especialidades2);		
			tx.commit();
		} else {
			System.out.println ("El tecnico no existe.");
			RRHH.modificarTecnico();
		}
	}
	
	public static void bajaTecnico() {
		System.out.println ("Por favor ingrese el Id del tecnico a dar de baja.");
		int id = teclado.nextInt();
		EntityManager em = ManagerPersistence.getEntityManager();
		Tecnico tecnico = em.find(Tecnico.class, id);
		if (tecnico != null) {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			tecnico.bajaTecnico();
			tx.commit();
		} else {
			System.out.println ("El tecnico no existe.");
			RRHH.bajaTecnico();
		}
	}
}
