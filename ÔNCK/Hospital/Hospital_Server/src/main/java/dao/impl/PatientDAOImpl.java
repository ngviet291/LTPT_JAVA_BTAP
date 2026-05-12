package dao.impl;

import db.JPAUtil;
import entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PatientDAOImpl implements dao.PatientDAO {
    @Override
    public boolean addPatient(Patient patient){
        EntityManager entityManager= null;
        EntityTransaction entityTransaction= null;
        try{
                entityManager=JPAUtil.getEntityManager();
                entityTransaction= entityManager.getTransaction();
                entityTransaction.begin();
                entityManager.persist(patient);
                entityTransaction.commit();
                return true;
        } catch (Exception e) {
            if (entityTransaction!=null && entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            return false;
        }
        finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }
}
