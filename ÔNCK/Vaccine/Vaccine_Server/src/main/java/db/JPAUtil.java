package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    public static final String PERSISTENCE_UNIT = "mariadb-pu";
    private static final EntityManagerFactory emf;
    static {
        emf= Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
