package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerPersistence {
	
	public static EntityManager getEntityManager() {
		if(entity == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_PU");
			entity = factory.createEntityManager();
        }
        return entity;
    }
	
	private static EntityManager entity;
	
}
