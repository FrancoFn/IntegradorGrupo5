package main.Entidades;

import java.sql.Date;
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

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

public class Incidente {
	@Id
	private String id;
	@Column
	private String descripcion;
	@Column
	private Date fechaIncidente;
	@Column
	private Date tiempoResolucion;
	@Column
	private String consideraciones;
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "cliente_id") //Esto indica la columna en la tabla de Incidente que representa la relación
	private Cliente cliente;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Tecnico> tecnico = new ArrayList<>();
	// @ManyToOne
	//    @JoinColumn(name = "estado_id") // Puedes ajustar el nombre de la columna según tu esquema de base de datos
	
	@Transient
    private static EntityManagerFactory entityManagerFactory;
    @Transient
    private static EntityManager entityManager;
    private static Estado estado; // Instancia de Estado
    
    public Incidente() {
    }
	public static void setEstado(Estado estado) {
        Incidente.estado = estado;
	}
	public Incidente(String id, String descripcion,Date fechaIncidente, Date tiempoResolucion, String consideraciones, Cliente cliente,
			List<Tecnico> tecnico) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fechaIncidente=fechaIncidente;
		this.tiempoResolucion = tiempoResolucion;
		this.consideraciones = consideraciones;
		this.cliente = cliente;
		this.tecnico = tecnico;
		//this.estado = estado;
	}
	public static void proceso() {
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
                    Estado.estado.proceso(incidente);
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
    }		
}
