package com.ngviet291.service;

import com.ngviet291.dao.SupplierDAO;
import com.ngviet291.entity.Product;
import com.ngviet291.entity.Supplier;

import java.util.List;

public class SupplierService {
    private final SupplierDAO supplierDAO;

    public SupplierService(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }
    public  boolean updateSupplier(Supplier supplier){
        return supplierDAO.updateSupplier(supplier);
    }
    public List<Supplier> getSuppliers(){
        return supplierDAO.getSuppliers();
    }
    public Supplier getSupplierById(String id){
        return  supplierDAO.getSupplierById(id);
    }
    public List<Supplier> getTopSuppliers (int limit){
        return supplierDAO.getTopSuppliers(limit);
    }
    public  boolean deleteSupplier(String id){
        return supplierDAO.deleteSupplier(id);
    }
    public List<Supplier> searchSuppliersByName(String keyword){
        return supplierDAO.searchSuppliersByName(keyword);
    }
}
