package main.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.Entidades.Cliente;
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
        Cliente cliente = new Cliente(3, "F", "Calle Secundaria", 987654321, "maria@example.com", 1234567890, 1, servicio);
        em.persist(cliente);
        tx.commit();
	}

}
