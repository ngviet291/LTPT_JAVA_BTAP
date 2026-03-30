/*
 * @ (#) DemoLopHoc.java     1.0    3/10/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/10/2026
 * @version:    1.0
 */
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class DemoLopHoc {
    public static void main(String[] args) {

        EntityManager em = infrastructure.JPAUtil.getEntityManager();
        Sinhvien sinhVien =em.find(Sinhvien.class,"SV001");

    LopHoc lopHoc = LopHoc.builder()
            .maLop("L001")
            .tenLop("Lop 1")
            .siSoDuKien(30)
            .build();
    lopHoc.setDsSinhVien(new ArrayList<>(List.of(sinhVien)));
    sinhVien.setLopHoc(lopHoc);
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(sinhVien);
            em.merge(lopHoc);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
    }
}}
