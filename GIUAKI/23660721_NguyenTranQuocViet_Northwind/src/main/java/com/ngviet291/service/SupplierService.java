package com.ngviet291.service;

import com.ngviet291.dao.SupplierDAO;
import com.ngviet291.entity.Supplier;

public class SupplierService {
    private final SupplierDAO supplierDAO;

    public SupplierService(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }
    public  boolean updateSupplier(Supplier supplier){
        return supplierDAO.updateSupplier(supplier);
    }
}
