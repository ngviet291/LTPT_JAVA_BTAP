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
    public List<Product> getSuppliers(){
        String cypher="" +

                "";
    }
}
