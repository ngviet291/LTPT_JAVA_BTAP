/**
 * @ (#) Main.java   1.0     10/3/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package com.ngviet291.test;


import com.ngviet291.entity.Student;
import com.ngviet291.infracstructor.db.JPAUtils;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Set;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 10/3/2026
 */

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getEntityManager();
        Student student= Student.builder()
                .id("S001")
                .firstName("John")
                .lastName("Doe")
                .gender("Male")
                .dateOfBirth(LocalDate.of(2000, 1, 1)).phoneList(Set.of("1234567890", "0987654321"))
                .build();
        var transaction = em.getTransaction();
        try {
                transaction.begin();
                em.persist(student);
                transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }
}
