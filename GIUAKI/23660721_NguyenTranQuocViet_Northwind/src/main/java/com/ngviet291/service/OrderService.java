package com.ngviet291.service;

import com.ngviet291.dao.OrderDAO;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    public double calculateTotalOrder (String orderID){
        return orderDAO.calculateTotalOrder(orderID);
    }

}
