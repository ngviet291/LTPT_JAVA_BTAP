package com.ngviet291.dao;

import com.ngviet291.db.Neo4jUltil;
import com.ngviet291.entity.Product;
import com.ngviet291.entity.Supplier;
import com.ngviet291.mapper.DataMapper;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.util.List;

public class SupplierDAO {
    private final DataMapper dataMapper;

    public SupplierDAO(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }
    public  boolean updateSupplier(Supplier supplier){
        try (Session session = Neo4jUltil.getSession()){
            String cypher= "MATCH(s:Supplier{supplier_id:$id}) SET s.company_name= $companyName,s.contact_name=$contactName,s.country=$country";
            return session.executeWrite(tx->{
                Result result= tx.run(cypher, Values.parameters("id",supplier.getSupplierId(),"companyName",supplier.getCompanyName(),"contactName",supplier.getContactName(),"country",supplier.getCountry()));
                return result.consume().counters().propertiesSet()>0;
            });
        }
    }
    //Tim nha cc có nhiều sp nhất nếu như có nhiều nhà cc = mãx thì in ds
    public List<Supplier> getSuppliers(){
        String cypher="MATCH(s:Supplier) -[:SUPPLIES] -> (p:Product) WITH s,count(p) as countProduct WITH collect({supplier:s,countProduct:countProduct}) as list,max(countProduct) as maxPro unwind list as item with item,maxPro WHERE maxPro=item.countProduct return item.supplier as sup,maxPro";
        try(Session session = Neo4jUltil.getSession()) {
            return session.executeRead(tx->{
                Result result = tx.run(cypher);
                return result.list(record -> {
                    Supplier supplier= dataMapper.toObject(record.get("sup").asMap(),Supplier.class);
                    return supplier;
                });
            });
        }
    }
    public Supplier getSupplierById(String id){
        String cypher="MATCH(s:Supplier{supplier_id:$id}) return s";
        try (Session session= Neo4jUltil.getSession()){
            return session.executeRead(tx->{
                Result result = tx.run(cypher,Values.parameters("id",id));
                if(result.hasNext()){
                    Supplier supplier= dataMapper.toObject(result.single().get("s").asMap(),Supplier.class);
                    return supplier;
                }
                return null;
            });
        }
    }
    //Tìm top N nhà cung cấp có nhiều sản phẩm nhất
    public List<Supplier> getTopSuppliers (int limit){
        try(Session session=Neo4jUltil.getSession()) {
            String cypher= "MATCH(s:Supplier) -[:SUPPLIES] -> (p:Product) WITH s,count(p) as countPro ORDER BY(countPro) DESC RETURN s,countPro LIMIT $limit";
            return session.executeRead(tx->{
                Result result= tx.run(cypher,Values.parameters("limit",limit));
                return result.list(record -> {
                    Supplier supplier= dataMapper.toObject(record.get("s").asMap(),Supplier.class);
                    return supplier;
                });
            });
        }
    }
    //f) Xóa nhà cung cấp theo SupplierID
    //deleteSupplier(id: String): boolean
    public  boolean deleteSupplier(String id){
        try(Session session = Neo4jUltil.getSession()) {
            String cypher="MATCH(s:Supplier{supplier_id:$id}) -[sp:SUPPLIES] -> (p:Product) DETACH DELETE s,sp";
            return session.executeWrite(tx->{
                Result result= tx.run(cypher,Values.parameters("id",id));
                return  result.consume().counters().nodesDeleted()>0;
            });
        }


    }

//    searchSuppliersByName(keyword: String): List<Supplier>
    public List<Supplier> searchSuppliersByName(String keyword){
        try (Session session = Neo4jUltil.getSession()){
            String cypher = """
                    CALL db.index.fulltext.queryNodes("companyName", $keyword) YIELD node, score
                    RETURN node, score
                    """;
            return session.executeRead(tx->{
                Result result = tx.run(cypher,Values.parameters("keyword",keyword));
                return result.list(record -> {
                    Supplier supplier= dataMapper.toObject(record.get("node").asMap(),Supplier.class);
                    return supplier;
                });
            });
        }
    }
}
