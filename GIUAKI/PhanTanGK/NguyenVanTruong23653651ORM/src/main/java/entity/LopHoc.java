/*
 * @ (#) LopHoc.java     1.0    3/10/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
*/
package entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
 * @description
 * @author:NguyenTruong
 * @date:  3/10/2026
 * @version:    1.0
 */
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "lop_hoc")
public class LopHoc {
    @Id
    @Column(name="ms_lop")
    private String maLop;
    @Column(name="ten_lop",nullable = false,columnDefinition = "varchar(100)",unique = true)
    private String tenLop;
    @Column(name="si_so_du_kien",nullable = false)
    private int siSoDuKien;
    @OneToMany(mappedBy = "lopHoc")
    private List<Sinhvien> dsSinhVien;



}
