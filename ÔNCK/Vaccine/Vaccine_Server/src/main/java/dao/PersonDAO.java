package dao;

import db.JPAUtil;
import entity.Person;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PersonDAO {
    public Person findById(String id){
        EntityManager  em =null;
        try {
            em = JPAUtil.getEntityManager();
            return em.find(Person.class,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null){
                em.close();
            }
        }
    }
    public List<Person> listObesePeople(){
        EntityManager  em =null;
        try {
            em = JPAUtil.getEntityManager();
            String query = """
                    Select p from Person p
                    where weight/(height*height)>25
                    """;
            return em.createQuery(query, Person.class).getResultList();
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
