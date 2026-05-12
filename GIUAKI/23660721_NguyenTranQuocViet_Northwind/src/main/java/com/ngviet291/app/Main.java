package com.ngviet291.app;

import com.ngviet291.dao.OrderDAO;
import com.ngviet291.dao.ProductDAO;
import com.ngviet291.dao.SupplierDAO;
import com.ngviet291.entity.Status;
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
        System.out.println("get supplier\n");
        supplierService.getSuppliers().forEach(System.out::println);


        System.out.println("get by id \n"+supplierService.getSupplierById("S006"));

        productService.findProductsByPriceRange(1,10).forEach(System.out::println);
        System.out.println("\nTìm top N nhà cung cấp có nhiều sản phẩm nhất:");
        supplierService.getTopSuppliers(5).forEach(System.out::println);

        if(supplierService.deleteSupplier("S005")){
            System.out.println("Xoa thanh cong");
        }else {
            System.out.println("Xoa nhu cc");
        }
        if(productService.updateUnitsInStock("P013",36)){
            System.out.println("Updatr thanh cong");
        }
        else System.out.println("Update nhu cc");
        System.out.println("tổng số sản phẩm đã bán:" + productService.calculateTotalSold("P013"));

        System.out.println("Sản phẩm có Status:" +Status.CANCELLED);
        orderService.getOrdersByStatus(Status.CANCELLED.toString()).forEach(System.out::println);
        orderService.countOrdersByStatus().forEach((k,v)-> System.out.println(k+":"+v));

        supplierService.searchSuppliersByName("Exotic").forEach(System.out::println);
    }
}
