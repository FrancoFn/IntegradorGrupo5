package main;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import main.Entidades.Cliente;
import main.Entidades.Especialidad;
import main.Entidades.Estado;
import main.Entidades.Incidente;
import main.Entidades.Tecnico;
import main.Entidades.ServicioContratado;
import javax.persistence.EntityTransaction;


public class IntegradorGrupo5Main {

	public static EntityManager getEntityManager() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_PU");
        EntityManager manager = factory.createEntityManager();

        return manager;
    }
	public static void main(String[] args) {
		EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        ServicioContratado servicio= ServicioContratado.PACK_BASICO;
        Cliente cliente = new Cliente(1, "F", "Calle Secundaria", 987654321, "maria@example.com", 1234567890, 1, servicio);
        Cliente cliente2 = new Cliente(3, "P", "Calle Prueba", 1234456, "prueba@example.com", 1234, 2, servicio);
        cliente2.altaCliente();        
        em.persist(cliente);
        em.persist(cliente2);
        List<Especialidad> especialidad = new ArrayList<>();
        especialidad.add(Especialidad.GOOGLE);
        Tecnico tecnico = new Tecnico (1,"Tecnico1","Prueba",1234,"tecnico1@gmail.com",1,especialidad);
        List<Tecnico> tecnicos = new ArrayList<>();
        tecnicos.add(tecnico);
        em.persist(tecnico);
        Estado estado = Estado.EN_CURSO;
        Incidente incidente = new Incidente ("1","Prueba1","1 hora","Prueba",cliente, tecnicos,estado);
        em.persist(incidente);
        tx.commit();
	}

}
