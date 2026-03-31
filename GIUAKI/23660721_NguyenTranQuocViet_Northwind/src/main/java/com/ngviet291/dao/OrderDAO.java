package com.ngviet291.dao;

import com.ngviet291.db.Neo4jUltil;
import com.ngviet291.mapper.DataMapper;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

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
}
