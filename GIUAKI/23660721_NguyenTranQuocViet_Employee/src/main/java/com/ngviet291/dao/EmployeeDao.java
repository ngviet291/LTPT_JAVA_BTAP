package com.ngviet291.dao;

import com.ngviet291.db.Neo4jUtil;
import com.ngviet291.entity.Employee;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

public class EmployeeDao {
    public boolean addEmployee(Employee emp){
        try(Session session= Neo4jUtil.getSession()) {
            String cypher ="CREATE(:Employee{id:$id,lastName:$lastName,firstName:$firstName,gender:$gender,phone:$phone,salaryCoefficient:$salaryCoefficient})";
            return session.executeWrite(tx->{
                Result result= tx.run(cypher, Values.parameters("id",emp.getId(),"lastName",emp.getLastName(),"firstName",emp.getFirstName(),"gender",emp.getGender(),"phone",emp.getPhone(),"salaryCoefficient",emp.getSalaryCoefficient()));
                return result.consume().counters().nodesCreated()>0;
            });
        }
    }
}
