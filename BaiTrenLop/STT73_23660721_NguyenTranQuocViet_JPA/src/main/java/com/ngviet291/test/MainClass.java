/**
 * @ (#) MainClass.java   1.0     10/3/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package com.ngviet291.test;


import com.ngviet291.entity.Clazz;
import com.ngviet291.entity.Student;
import com.ngviet291.infracstructor.db.JPAUtils;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet
 * @version: 1.0
 * @created: 10/3/2026
 */

public class MainClass {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtils.getEntityManager();

        Student student = Student.builder()
                .id("SV001")
                .firstName("Nguyen")
                .lastName("Viet")
                .gender("Nam")
                .phoneList(new HashSet<>(List.of("0123456789", "0987654321")))
                .dateOfBirth(LocalDate.now())
                .build();

        Clazz clazz = Clazz.builder()
                .classId("B001")
                .className("Lap trinh Java")
                .numbers(30)
                .build();

        student.setClazz(clazz);
        clazz.setStudents(List.of(student));

        var transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(clazz);
        entityManager.persist(student);

        transaction.commit();
    }

}
