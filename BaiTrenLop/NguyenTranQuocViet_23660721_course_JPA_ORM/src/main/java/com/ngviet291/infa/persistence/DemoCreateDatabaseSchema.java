package com.ngviet291.infa.persistence;

import com.ngviet291.entity.Instructor;
import com.ngviet291.entity.Student;
import com.ngviet291.infa.db.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DemoCreateDatabaseSchema {
    public static void main(String[] args) {

        EntityManager entityManager = null;
        EntityTransaction tr = null;

        try {

            entityManager = JPAUtil.getEntityManager();
            tr = entityManager.getTransaction();

            tr.begin();

            Student student = Student.builder()
                    .id("ST1")
                    .firstName("Nguyen")
                    .lastName("Viet")
                    .enrollmentDate(LocalDateTime.now())
                    .build();

            Instructor instructor = Instructor.builder()
                    .id("IS1")
                    .firstName("Nguyen")
                    .lastName("Van A")
                    .hireDate(LocalDateTime.now())
                    .build();

            entityManager.persist(student);
            entityManager.persist(instructor);

            tr.commit();

        } catch (Exception e) {

            if (tr != null && tr.isActive()) {
                tr.rollback();
            }

            e.printStackTrace();

        } finally {

            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
