package com.ngviet291.service;

import com.ngviet291.dao.OrderDAO;
import com.ngviet291.entity.Order;

import java.util.List;
import java.util.Map;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    public double calculateTotalOrder (String orderID){
        return orderDAO.calculateTotalOrder(orderID);
    }
    public List<Order> getOrdersByStatus(String status){
        return orderDAO.getOrdersByStatus(status);
    }
    public Map<String, Long> countOrdersByStatus(){
        return orderDAO.countOrdersByStatus();
    }
}
