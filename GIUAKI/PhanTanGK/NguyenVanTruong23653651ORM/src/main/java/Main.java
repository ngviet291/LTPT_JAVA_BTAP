/*
 * @ (#) Main.java     1.0    3/10/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/10/2026
 * @version:    1.0
 */

import entity.Sinhvien;
import infrastructure.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        Sinhvien sv = Sinhvien.builder()
                .mssv("SV001")
                .ten("Nguyen Van A")
                .ho("Nguyen")
                .gioitinh("Nam")
                .ngaysinh(java.time.LocalDate.of(2000, 1, 1))
                .dsDienThoai(new HashSet<>(List.of("0123456789", "0987654321")))
                .build();

        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
           // em.persist(sv);
            em.merge(sv);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }}
