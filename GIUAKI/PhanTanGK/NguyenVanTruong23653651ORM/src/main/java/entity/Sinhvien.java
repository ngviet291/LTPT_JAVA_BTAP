/*
 * @ (#) SinhVien.java     1.0    3/10/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package entity;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/10/2026
 * @version:    1.0
 */

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "sinhvien")// Customumize table name
public class Sinhvien {

    @Id
    @Column(columnDefinition="varchar(20)")

    private String mssv;
    private String ho;
    @Column(nullable = false)
    private String ten;
    @Column(name="gioi_tinh")
    private String gioitinh;
    private LocalDate ngaysinh;
    // han che dung EAGER
    // EAGER: Luôn tải dữ liệu liên quan ngay khi truy vấn đối tượng chính.
    //LAZY: Chỉ tải dữ liệu liên quan khi thực sự cần thiết (khi truy cập thuộc tính đó).
    @ElementCollection
    @CollectionTable(name="dien_thoai",joinColumns = @JoinColumn(name="mssv"))
    @Column(name="so_dien_thoai",nullable = false)
    private Set<String> dsDienThoai;
    @ManyToOne
    @JoinColumn(name="ms_lop",nullable = false)
    private LopHoc lopHoc;//defaut foreign key column name is lop_hoc_ms_lop

}