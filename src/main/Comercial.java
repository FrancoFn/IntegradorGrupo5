package main;

import java.util.Scanner;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import main.Entidades.Cliente;
import main.Entidades.ServicioContratado;

public class Comercial {
	static Scanner teclado = new Scanner (System.in);	
	
	public static void altaCliente () {
		EntityManager em = ManagerPersistence.getEntityManager();
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
    }/*
	public static void altaCliente () {
		System.out.println ("Por favor ingrese la razón social del cliente:");
		String nombre = teclado.nextLine();
		System.out.println ("Por favor ingrese el cuit del cliente:");
		Long cuit = Long.parseLong(teclado.nextLine());
		System.out.println ("Por favor ingrese el domicilio del cliente:");
	    String domicilio = teclado.nextLine();
	    System.out.println ("Por favor ingrese el telefono del cliente:");
	    Long telefono = Long.parseLong(teclado.nextLine());
	    System.out.println ("Por favor ingrese el mail del cliente:");
	    String mail = teclado.next();
	    
	    int opcionServicio=0;
	    ServicioContratado contratacion = ServicioContratado.PACK_BASICO;
	    do {
	    	System.out.println ("Por favor ingrese el servicio contratado por el cliente:");
	    	System.out.println ("1 - Pack básico");
	    	System.out.println ("2 - Pack intermedio");
	    	System.out.println ("3 - Pack full");
	    	opcionServicio = teclado.nextInt();
	    		switch (opcionServicio) {
					case 1:
						contratacion = ServicioContratado.PACK_BASICO;
						break;
					case 2:
						contratacion = ServicioContratado.PACK_INTERMEDIO;	 
						break;
					case 3:
						contratacion = ServicioContratado.PACK_FULL;
						break;
					default:
						System.out.println ("La opción ingresada no es valida por favor ingrese una opción valida.");
				} 
	  	}while (opcionServicio < 1 || opcionServicio > 3);
	  
	    Cliente cliente = new Cliente(nombre,domicilio,telefono,mail,cuit,1,contratacion);
	    cliente.altaCliente();
	    EntityManager em = ManagerPersistence.getEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(cliente);
	    tx.commit();
	} 
	*/ 
	
	public static void modificarCliente() {
		System.out.println ("Por favor ingrese el Id del cliente a modificar:");
		int id = teclado.nextInt();
		ServicioContratado servicio2= ServicioContratado.PACK_INTERMEDIO;
		EntityManager em = ManagerPersistence.getEntityManager();
		Cliente cliente = em.find(Cliente.class, id);
		if (cliente != null) {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			cliente.modificarCliente ("Calle Prueba1", 11111, "prueba1@example.com"+new Date(),servicio2);
			System.out.println("El cliente: "+cliente.getNombre()+" ha sido modificado.");
			tx.commit();
        } else {
            System.out.println("El cliente no existe");
            Comercial.modificarCliente();
        }
	}
	
	public static void bajaCliente() {
		System.out.println ("Por favor ingrese el Id del cliente a dar de baja:");
		int id = teclado.nextInt();
		EntityManager em = ManagerPersistence.getEntityManager();
		Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
        	EntityTransaction tx = em.getTransaction();
        	tx.begin();
			cliente.bajaCliente ();
			tx.commit();
        } else {
        	System.out.println("El cliente no existe");
            Comercial.bajaCliente();
        }
	}
	
	
}
