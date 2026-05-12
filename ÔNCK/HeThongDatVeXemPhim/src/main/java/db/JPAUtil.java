package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT = "mariadb-pu";

    private static EntityManagerFactory emf = null;

    public static EntityManagerFactory getEntityManagerFactory() {

        try {

            synchronized (JPAUtil.class) {

                if (emf == null) {
                    emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
                }
            }

            return emf;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static EntityManager getEntityManager(){
        return getEntityManagerFactory().createEntityManager();
    }
}
