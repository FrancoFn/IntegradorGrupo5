package main.Entidades;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Transient;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import lombok.Getter;
import lombok.Setter;
import main.Especialidad;
import main.IEstado;
import main.EstadoIncidente;
import main.TiempoResolucion;

@Setter
@Getter
@Entity

public class Incidente {
	@Id
	private String id;
	@Column
	private String descripcion;
	@Column
	private Date fechaInicio;
	@Column
	private Date fechaFinalizacion;
	@Column
	private TiempoResolucion tiempoResolucion;
	//private Date tiempoResolucion;
	@Column
	private String consideraciones;
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "cliente_id") //Esto indica la columna en la tabla de Incidente que representa la relación
	private Cliente cliente;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Tecnico> tecnico = new ArrayList<>();
	@Column
	private Especialidad categoria;
	@Column
	private EstadoIncidente estadoInc;
    @Transient
    private static EntityManagerFactory entityManagerFactory;
    @Transient
    private static EntityManager entityManager;
    private static IEstado estado; // Instancia de Estado

    // Constructor por defecto (necesario para Hibernate)
    public Incidente() {
    }
	
		public Incidente(String id, String descripcion,Date fechaInicio, TiempoResolucion tiempoResolucion, String consideraciones, Cliente cliente,
			List<Tecnico> tecnico,EstadoIncidente estado, Especialidad categoria) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.tiempoResolucion = tiempoResolucion;
		this.consideraciones = consideraciones;
		this.cliente = cliente;
		this.tecnico = tecnico;
		this.estadoInc = estado;
		this.categoria = categoria;
	}
	
	public void creacionIncidente (Cliente cliente, Especialidad categoria, String descripcion, TiempoResolucion tiempo, List<Tecnico> tecnico) {
		this.cliente = cliente;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.tiempoResolucion = tiempo;
		this.tecnico = tecnico;
		this.fechaInicio = new Date();
	}
	
	/*public static void proceso() {
        entityManagerFactory = Persistence.createEntityManagerFactory("JPA_PU");
        entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Obtener todos los incidentes de la base de datos
            List<Incidente> incidentes = entityManager.createQuery("SELECT i FROM Incidente i", Incidente.class)
                    .getResultList();

            // Verificar si la lista de incidentes está vacía
            if (incidentes.isEmpty()) {
                System.out.println("Sin incidentes registrados");
            } else {
                // Enviar cada incidente a la interfaz Estado
                for (Incidente incidente : incidentes) {
                    estado.proceso(incidente);
                }
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }*/
}				
