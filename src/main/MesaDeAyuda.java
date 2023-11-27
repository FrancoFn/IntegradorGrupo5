package main;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


import main.Entidades.Cliente;
import main.Entidades.Incidente;
import main.Entidades.Tecnico;

public class MesaDeAyuda {
	static Scanner teclado = new Scanner (System.in);	
	
	public static void cargarIncidente () {
		//Valido y obtengo el cliente si existe
		System.out.println ("Por favor indique el cuit del cliente a buscar:");
		long cuit = teclado.nextLong();
		System.out.println ("Por favor indique la razon social:");
		String razonsocial = teclado.next();
		
		Query q = (Query) ManagerPersistence.getEntityManager().createQuery("from Cliente where cuit = :doc and nombre = :doc1");
		q.setParameter("doc", cuit);
		q.setParameter("doc1", razonsocial);
		List<?> cliente = q.getResultList();
		if (!cliente.isEmpty()){
<<<<<<< HEAD
<<<<<<< HEAD
			System.out.println ("El sercicio contratado por el cliente es: "+ ((Cliente)cliente.get(0)).getContratacion());			
		} else {
			System.out.println ("El cliente no existe comuniquese con el sector comercial para su ingreso.");
		}            	
	}
	
	public static void cargarIncidente() {
		
=======
=======
>>>>>>> Pablo
			if ((((Cliente)cliente.get(0)).getEstado() == 0)){
				System.out.println("El cliente ha sido dado de baja. Comuniquese con el sector comercial.");
				return;
			} else {
				System.out.println();
				System.out.println("El sercicio contratado por el cliente es: "+ ((Cliente)cliente.get(0)).getContratacion());
			}						
		} else  {
			System.out.println("El cliente no existe. Comuniquese con el sector comercial para su ingreso.");
			return;
		}
		
		//Solicito la categoria del incidente
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
		
		//Solicito la descripcion del incidente
		System.out.println ("Por favor ingrese la descripción del problema:");
		String descripcion = teclado.next();
		
		//Tiempo de resolucion estimado
		TiempoResolucion[] tiempo = TiempoResolucion.values();
		TiempoResolucion tiempoResolucion = TiempoResolucion.values()[categoria.ordinal()]; 
		System.out.println ("El tiempo de resolucion aproximado para la categoria "+categoria+" es de "+tiempo[categoria.ordinal()]);
		
		//Seleccion del Tecnico
		Query q2 = (Query) ManagerPersistence.getEntityManager().createQuery ("SELECT t FROM Tecnico t JOIN t.especialidades e WHERE t.disponibilidad = :doc AND e = :doc1");
		q2.setParameter("doc", true);
		q2.setParameter("doc1", categoria);
		List<Tecnico> tecnico = q2.getResultList();
		
		System.out.println ("Los tecnicos disponible son:");
		for (int j = 0; j < tecnico.size(); j++) {
			System.out.println (j+1+" - "+((Tecnico)tecnico.get(j)).getNombre());
		}
		
		//Creacion del incidente y persintecia en base de datos
		Incidente incidente = new Incidente();
		incidente.creacionIncidente((Cliente)cliente.get(0), categoria, descripcion, tiempoResolucion, (Tecnico)tecnico.get(0));
		
		EntityManager em = ManagerPersistence.getEntityManager();
        EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(incidente);
        tx.commit();
		
		Asignado asignado = new Asignado();
		asignado.asignar(incidente);
<<<<<<< HEAD
>>>>>>> Pablo
=======
>>>>>>> Pablo
	}
}
