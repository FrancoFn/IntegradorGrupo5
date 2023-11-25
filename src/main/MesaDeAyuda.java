package main;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import main.Entidades.Cliente;
import main.Entidades.Especialidad;


public class MesaDeAyuda {
	static Scanner teclado = new Scanner (System.in);	
	
	public static void mostrarServicioCliente () {
		System.out.println ("Por favor indique el cuit del cliente a buscar:");
		long cuit = teclado.nextLong();
		System.out.println ("Por favor indique la razon social:");
		String razonsocial = teclado.next();
		
		Query q = (Query) ManagerPersistence.getEntityManager().createQuery("from Cliente where cuit = :doc and nombre = :doc1");
		q.setParameter("doc", cuit);
		q.setParameter("doc1", razonsocial);
		List<?> cliente = q.getResultList();
		if (!cliente.isEmpty()){
			if ((((Cliente)cliente.get(0)).getEstado() == 0)){
				System.out.println("El cliente ha sido dado de baja. Comuniquese con el sector comercial.");
			} else {
				System.out.println ("El sercicio contratado por el cliente es: "+ ((Cliente)cliente.get(0)).getContratacion());
			}						
		} else {
			System.out.println ("El cliente no existe. Comuniquese con el sector comercial para su ingreso.");
		}            	
	}
	
	public static void cargarIncidente() {
		Especialidad[] especialidades = Especialidad.values();
		Especialidad categoria = null;
		int opcionCategoria = 0;
		do {
			System.out.println ("Por favor seleccione la categoria del incidente:");
			for(int i = 0; i <= 5; i++) {
				System.out.println (i+1 +"- "+especialidades[i]);			
			}	
			opcionCategoria = teclado.nextInt();
			if (opcionCategoria < 1 || opcionCategoria > especialidades.length) {
				System.out.println ("La opción ingresada no es correcta.");
			} else {
				categoria = especialidades[opcionCategoria-1];
			}
		} while (opcionCategoria < 1 || opcionCategoria > especialidades.length);
		
	}
}
