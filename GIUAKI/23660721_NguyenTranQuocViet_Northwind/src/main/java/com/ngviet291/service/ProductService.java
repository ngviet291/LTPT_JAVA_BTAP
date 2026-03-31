package com.ngviet291.service;

import com.ngviet291.dao.ProductDAO;
import com.ngviet291.entity.Product;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> listProductsBySupplier (String companyName, int page, int size){
        return productDAO.listProductsBySupplier(companyName,page,size);
    }
}
