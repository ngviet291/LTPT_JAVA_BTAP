package dao;

import db.JPAUtil;
import entity.DoseStatus;
import entity.Person;
import entity.Record;
import entity.Vaccine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class RecordDAO {
    public boolean createNewRecord(Record record){
        EntityManager em = null;
        EntityTransaction en = null;
        try {
            em= JPAUtil.getEntityManager();
            en= em.getTransaction();
            en.begin();

            Person person= em.find(Person.class,record.getPerson().getId());
            if(person==null){
                en.rollback();
                return false;
            }

            Vaccine vaccine = em.find(Vaccine.class,record.getVaccine().getId());
            if(vaccine==null){
                en.rollback();
                return false;
            }

            record.setPerson(person);
            record.setVaccine(vaccine);
            em.persist(record);

            en.commit();
            return  true;
        } catch (Exception e) {
            if(en!=null&& en.isActive()){
                en.rollback();
                return false;
            }
            throw new RuntimeException(e);
        }finally {
            if(em!=null)
                em.close();
        }
    }
    public boolean updateScheduledRecord(Long recordId, DoseStatus doseStatus ){
        EntityManager em = null;
        EntityTransaction en = null;
        try {
            em= JPAUtil.getEntityManager();
            en= em.getTransaction();
            en.begin();

            Record record = em.find(Record.class, recordId);
            if(record == null){
                en.rollback();
                return false;
            }
            record.setStatus(doseStatus);
            en.commit();
            return  true;
        } catch (Exception e) {
            if(en!=null&& en.isActive()){
                en.rollback();
                return false;
            }
            throw new RuntimeException(e);
        }finally {
            if(em!=null)
                em.close();
        }
    }
    public Record findById(Long id){
        EntityManager em =null;
        try {
            em = JPAUtil.getEntityManager();
            return em.find(Record.class,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null){
                em.close();
            }
        }
    }
}
