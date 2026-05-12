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
    //Tìm danh sách sản phẩm theo khoảng giá
    public List<Product> findProductsByPriceRange(double min,  double max){
        try (Session session= Neo4jUltil.getSession()){
            String cypher="MATCH(p:Product) WHERE p.unit_price>$min AND p.unit_price<$max RETURN p ";
            return session.executeRead(tx->{
                Result result= tx.run(cypher,Values.parameters("min",min,"max",max));
                return result.list(record -> {
                    Product product= dataMapper.toObject(record.get("p").asMap(), Product.class);
                    return product;
                });
            });
        }
    }
//    Cập nhật tồn kho sản phẩm
//    updateUnitsInStock(productID: String, quantity: int): boolean
    public boolean updateUnitsInStock( String productID, int quantity){
        try (Session session= Neo4jUltil.getSession()){
            String cypher= "MATCH(p:Product{product_id:$productId}) SET p.units_in_stock=$quantity";
            return session.executeWrite(tx->{
                Result result = tx.run(cypher,Values.parameters("productId",productID,"quantity",quantity));
                return result.consume().counters().propertiesSet()>0;
            });

        }
    }
//    h) Tính tổng số sản phẩm đã bán theo ProductID
//    calculateTotalSold(productID: String): int
    public int calculateTotalSold(String productID){
         try (Session session = Neo4jUltil.getSession()){
             String cypher="MATCH(o:Order) -[od:ORDERS] -> (p:Product{product_id:$productId}) RETURN p, sum(od.quantity) as sumquan";
            return session.executeRead(tx->{
                Result result = tx.run(cypher,Values.parameters("productId",productID));
                return result.next().get("sumquan").asInt();
            });
         }
    }
}
