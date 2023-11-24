package main;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import main.Entidades.Cliente;


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
			System.out.println ("El sercicio contratado por el cliente es: "+ ((Cliente)cliente.get(0)).getContratacion());			
		} else {
			System.out.println ("El cliente no existe comuniquese con el sector comercial para su ingreso.");
		}            	
	}
	
	public static void cargarIncidente() {
	}
}
