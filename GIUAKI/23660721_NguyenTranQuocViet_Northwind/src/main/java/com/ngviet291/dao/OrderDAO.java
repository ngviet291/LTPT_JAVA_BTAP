package com.ngviet291.dao;

import com.ngviet291.db.Neo4jUltil;
import com.ngviet291.entity.Order;
import com.ngviet291.mapper.DataMapper;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAO {
    private final DataMapper dataMapper;

    public OrderDAO(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }
    public double calculateTotalOrder (String orderID){
        try (Session session= Neo4jUltil.getSession()){
            String cypher = "MATCH(o:Order{order_id:$orderID})-[od:ORDERS] ->(p:Product) RETURN o, sum(od.quantity*od.unit_price*(1-od.discount)) as total";
            return session.executeRead(tx->{
                Result result= tx.run(cypher, Values.parameters("orderID",orderID));
                return result.next().get("total").asDouble();
            });
        }
    }
//    Lấy danh sách đơn hàng theo trạng thái
//    getOrdersByStatus(status: String): List<Order>
    public List<Order> getOrdersByStatus(String status){
        try (Session session = Neo4jUltil.getSession()){
            String cypher = "MATCH(o:Order{status:$status}) RETURN o";
            return session.executeRead(tx->{
                Result result = tx.run(cypher, Values.parameters("status",status));
                return result.list(record -> {
                    Order order= dataMapper.toObject(record.get("o").asMap(), Order.class);
                    return order;
                });
            });
        }
    }
//    Thống kê số đơn hàng theo từng trạng thái
//    countOrdersByStatus(): Map<String, Long>
    public Map<String, Long> countOrdersByStatus(){
        try (Session session= Neo4jUltil.getSession()){
            String cypher ="MATCH (o:Order) RETURN o.status AS status, COUNT(o) AS total";
            return session.executeRead(tx->{
                Result result = tx.run(cypher);
                Map<String,Long> map= new HashMap<>();
                result.list(record -> {
                    String status= record.get("status").asString();
                    Long total= record.get("total").asLong();
                    map.put(status,total);
                    return null;
                });
                return map;
            });
        }
    }
}
