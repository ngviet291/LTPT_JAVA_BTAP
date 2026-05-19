package dao;

import db.JPAUtil;
import entity.DoseStatus;
import entity.Person;
import entity.Vaccine;
import jakarta.persistence.EntityManager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VaccineDAO {
    public Vaccine findById(String id){
        EntityManager em =null;
        try {
            em = JPAUtil.getEntityManager();
            return em.find(Vaccine.class,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null){
                em.close();
            }
        }
    }
    public Map<Vaccine, Integer> countRecordsByVaccines(){
        EntityManager  em =null;
        try {
            em = JPAUtil.getEntityManager();
            String query = """
                        select  v, count(r) from Vaccine  v
                        join v.records r
                        where r.status=:status
                        group by v
                        order by v.name asc
                    """;
            List<Object[]> result = em.createQuery(query, Object[].class).setParameter("status", DoseStatus.COMPLETED).getResultList();
            Map<Vaccine, Integer> map =new LinkedHashMap<>();
            for(Object[] obj:result){
                map.put((Vaccine) obj[0], ((Long) obj[1]).intValue());
            }
            return  map;
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
