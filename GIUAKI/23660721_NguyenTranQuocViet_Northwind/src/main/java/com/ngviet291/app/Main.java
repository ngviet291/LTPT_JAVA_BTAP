package com.ngviet291.app;

import com.ngviet291.dao.OrderDAO;
import com.ngviet291.dao.ProductDAO;
import com.ngviet291.dao.SupplierDAO;
import com.ngviet291.entity.Supplier;
import com.ngviet291.mapper.DataMapper;
import com.ngviet291.service.OrderService;
import com.ngviet291.service.ProductService;
import com.ngviet291.service.SupplierService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        DataMapper dataMapper= new DataMapper();
        ProductDAO productDAO= new ProductDAO(dataMapper);
        ProductService productService= new ProductService(productDAO);
        productService.listProductsBySupplier("Cooperativa de Quesos 'Las Cabras'",1,5).forEach(System.out::println);

        Supplier supplier= Supplier.builder()
                .supplierId("S006")
                .companyName("SASASA")
                .contactName("wqwqeqe")
                .country("uqwhehqwe")
                .build();
        SupplierDAO supplierDAO= new SupplierDAO(dataMapper);
        SupplierService supplierService= new SupplierService(supplierDAO);
        if(supplierService.updateSupplier(supplier)){
            System.out.println("Cap nhat o so ke");
        }
        else System.out.println("Cap nhat nhu cc");

        OrderDAO orderDAO= new OrderDAO(dataMapper);
        OrderService orderService= new OrderService(orderDAO);
        System.out.println(orderService.calculateTotalOrder("O005"));
    }
}
