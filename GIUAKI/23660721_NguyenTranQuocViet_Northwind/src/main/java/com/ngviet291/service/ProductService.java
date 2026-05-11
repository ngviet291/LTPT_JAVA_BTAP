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
    public List<Product> findProductsByPriceRange(double min,  double max){
        return productDAO.findProductsByPriceRange(min,max);
    }
    public boolean updateUnitsInStock( String productID, int quantity){
        return productDAO.updateUnitsInStock(productID,quantity);
    }
    public int calculateTotalSold(String productID){
        return productDAO.calculateTotalSold(productID);
    }
}
