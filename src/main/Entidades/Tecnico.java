package main.Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Query;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import lombok.Getter;
import lombok.Setter;

import main.EstadoIncidente;
import main.Especialidad;
import main.ManagerPersistence;
import main.Asignado;
import main.EnCurso;


@Entity
@Setter  
@Getter
public class Tecnico extends Persona {
	
	@ElementCollection
    @CollectionTable(name = "tecnico_especialidades", joinColumns = @JoinColumn(name = "tecnico_id"))
    @Column
	private List<Especialidad> especialidades = new ArrayList<>();
	@ManyToMany(mappedBy = "tecnico", cascade = CascadeType.ALL)
	private List<Incidente> incidentes = new ArrayList<>();
	@Column
	private boolean disponibilidad; 
	
	public Tecnico() {
	}
	
	public Tecnico(String nombre, String domicilio, long telefono, String email, int estado, List<Especialidad> especialidades) {
		super(nombre, domicilio, telefono, email,estado);
		this.especialidades = especialidades;
		this.disponibilidad = true;
	}
	
	public void altaTecnico() {
        System.out.println("Tecnico dado de alta: " + this.getNombre());
    }
	
	public void modificarTecnico (String nuevodomicilio, long telefono, String email,List<Especialidad> especialidades) {
		this.setDomicilio(nuevodomicilio);
		this.setTelefono(telefono);
		this.setEmail(email);
		this.setEspecialidades(especialidades);
	}
	
	public void bajaTecnico() {
		this.setEstado(0);
        System.out.println("Tecnico dado de baja: " + this.getNombre());
	}
	
	static Scanner teclado = new Scanner (System.in);
	
	public static void revisarIncidente () {
		System.out.println("Por favor ingrese su numero de id:");
		int id = teclado.nextInt();
		//Falta validacion de id correcto
		EstadoIncidente estado = EstadoIncidente.ASIGNADO;
		
		Query q = (Query) ManagerPersistence.getEntityManager().createQuery ("SELECT i FROM Incidente i JOIN i.tecnico t WHERE i.estadoInc = :doc and t.id = :doc1");
		q.setParameter("doc", estado);
		q.setParameter("doc1", id);
		List<Incidente> incidentes = q.getResultList();
				
		int[] contador = {1};
		if (!incidentes.isEmpty()) {
	        System.out.println ("Id - Descripcion - Categoria - Cliente");			
			incidentes.stream().forEach(incidente -> 
	        	System.out.println((contador[0]++)+" "+incidente.getDescripcion()+" "+incidente.getCategoria()+" "+incidente.getCliente().getNombre()));
	    } else {
	        System.out.println("No tiene incidentes en estado asignado");
	        return;
	    }
		
		System.out.println ("Ingrese el numero del incidente a resolver:");
		int idResolver = teclado.nextInt();
		//Falta validar que el id sea correcto
		
		Incidente incidente = new Incidente();
		incidente = (Incidente)incidentes.get(idResolver-1);
		
		Asignado asignado = new Asignado();
		asignado.EnCurso(incidente);
		
				
		//Cambio la disponibilidad del Tecnico a FALSE
		Query q2 = (Query) ManagerPersistence.getEntityManager().createQuery("SELECT t FROM Tecnico t WHERE t.id = :doc");
		q2.setParameter("doc", id);
		List<Tecnico> tecnico = q2.getResultList();
		
		((Tecnico)tecnico.get(0)).setDisponibilidad(false);
		
		EntityManager em = ManagerPersistence.getEntityManager();
        EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist((Tecnico)tecnico.get(0));
        tx.commit();
	}
	
	public static void resolverIncidente () {
		System.out.println("Por favor ingrese su numero de id:");
		int id = teclado.nextInt();
		//Falta validacion de id correcto
		EstadoIncidente estado = EstadoIncidente.EN_CURSO;
		
		Query q = (Query) ManagerPersistence.getEntityManager().createQuery ("SELECT i FROM Incidente i JOIN i.tecnico t WHERE i.estadoInc = :doc and t.id = :doc1");
		q.setParameter("doc", estado);
		q.setParameter("doc1", id);
		List<Incidente> incidentes = q.getResultList();
		
		int[] contador = {1};
		if (!incidentes.isEmpty()) {
	        System.out.println ("Id - Descripcion - Categoria - Cliente");			
			incidentes.stream().forEach(incidente -> 
	        	System.out.println((contador[0]++)+" "+incidente.getDescripcion()+" "+incidente.getCategoria()+" "+incidente.getCliente().getNombre()));
	    } else {
	        System.out.println("No tiene incidentes en estado asignado");
	        return;
	    }
		
		System.out.println ("Ingrese el numero del incidente a resolver:");
		int idResolver = teclado.nextInt();
		//Falta validar que el id sea correcto
		
		Incidente incidente = new Incidente();
		incidente = (Incidente)incidentes.get(idResolver-1);
		
		EnCurso encurso = new EnCurso();
		encurso.Resuelto(incidente);
		
		//Cambio la disponibilidad del Tecnico a TRUE
		Query q2 = (Query) ManagerPersistence.getEntityManager().createQuery("SELECT t FROM Tecnico t WHERE t.id = :doc");
		q2.setParameter("doc", id);
		List<Tecnico> tecnico = q2.getResultList();
				
		((Tecnico)tecnico.get(0)).setDisponibilidad(true);
		
		EntityManager em = ManagerPersistence.getEntityManager();
        EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist((Tecnico)tecnico.get(0));
        tx.commit();
	}
}
