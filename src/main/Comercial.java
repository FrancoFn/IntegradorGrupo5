package main;

import java.util.Scanner;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import main.Entidades.Cliente;
import main.Entidades.ServicioContratado;

public class Comercial {
	static Scanner teclado = new Scanner (System.in);	
	
	public static EntityManager getEntityManager() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_PU");
        EntityManager manager = factory.createEntityManager();

        return manager;
    }
	
	public static void altaCliente () {
		EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
		tx.begin();
        ServicioContratado servicio1= ServicioContratado.PACK_BASICO;
        ServicioContratado servicio2= ServicioContratado.PACK_INTERMEDIO;
        ServicioContratado servicio3= ServicioContratado.PACK_FULL;
        Cliente cliente1 = new Cliente("P1", "Calle Prueba1", 11111, "prueba1@example.com", 111, 1, servicio1);
        Cliente cliente2 = new Cliente("P2", "Calle Prueba2", 22222, "prueba2@example.com", 222, 1, servicio1);
        Cliente cliente3 = new Cliente("P3", "Calle Prueba3", 33333, "prueba3@example.com", 333, 1, servicio2);
        Cliente cliente4 = new Cliente("P4", "Calle Prueba4", 44444, "prueba4@example.com", 444, 1, servicio2);
        Cliente cliente5 = new Cliente("P5", "Calle Prueba5", 55555, "prueba5@example.com", 555, 1, servicio3);
        Cliente cliente6 = new Cliente("P6", "Calle Prueba6", 66666, "prueba6@example.com", 666, 1, servicio3);
        cliente1.altaCliente();
        cliente2.altaCliente();
        cliente3.altaCliente();
        cliente4.altaCliente();
        cliente5.altaCliente();
        cliente6.altaCliente();
        em.persist(cliente1);
        em.persist(cliente2);
        em.persist(cliente3);
        em.persist(cliente4);
        em.persist(cliente5);
        em.persist(cliente6);
        tx.commit();
        teclado.close();
	}
	
	public static void modificarCliente() {
		System.out.println ("Por favor ingrese el Id del cliente a modificar.");
		int id = teclado.nextInt();
		ServicioContratado servicio2= ServicioContratado.PACK_INTERMEDIO;
		EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
		tx.begin();
		Cliente cliente = em.find(Cliente.class, id);
        cliente.modificarCliente ("Calle Prueba1", 11111, "prueba1@example.com"+new Date(),servicio2);
        tx.commit();
        teclado.close();
	}
	
	public static void bajaCliente() {
		System.out.println ("Por favor ingrese el Id del cliente a dar de baja.");
		int id = teclado.nextInt();
		EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
		tx.begin();
		Cliente cliente = em.find(Cliente.class, id);
        cliente.bajaCliente ();
        tx.commit();
        teclado.close();
	}
	
	
}
