package com.ngviet291.dao;

import com.ngviet291.db.Neo4jUltil;
import com.ngviet291.entity.Product;
import com.ngviet291.entity.Supplier;
import com.ngviet291.mapper.DataMapper;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final DataMapper dataMapper;

    public ProductDAO(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public List<Product> listProductsBySupplier (String companyName, int page, int size){
        try (Session session= Neo4jUltil.getSession()){
            int skip=(page-1)*size;
            String cypher="MATCH(s:Supplier{company_name:$companyName})-[:SUPPLIES] -> (p:Product) return p SKIP $skip LIMIT $limit";
            return session.executeRead(tx->{
                Result result= tx.run(cypher, Values.parameters("companyName",companyName,"skip",skip,"limit",size));
                return result.list(record->{
                    Product product= dataMapper.toObject(record.get("p").asMap(), Product.class);
                    return  product;
                });
            });
        }
    }
}
