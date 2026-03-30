package com.ngviet291.dao;

import com.ngviet291.db.Neo4jUtil;
import com.ngviet291.entity.Employee;
import com.ngviet291.entity.Role;
import com.ngviet291.mapper.DataMapper;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDao {
    private final DataMapper dataMapper;

    public EmployeeDao(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public boolean addEmployee(Employee emp){
        try(Session session= Neo4jUtil.getSession()) {
            String cypher ="CREATE(:Employee{id:$id,lastName:$lastName,firstName:$firstName,gender:$gender,phone:$phone,salaryCoefficient:$salaryCoefficient})";
            return session.executeWrite(tx->{
                Result result= tx.run(cypher, Values.parameters("id",emp.getId(),"lastName",emp.getLastName(),"firstName",emp.getFirstName(),"gender",emp.getGender(),"phone",emp.getPhone(),"salaryCoefficient",emp.getSalaryCoefficient()));
                return result.consume().counters().nodesCreated()>0;
            });
        }
    }
    public List<Employee> listManagers(){

        try (Session session= Neo4jUtil.getSession()){
            String cypher="MATCH(d:Department)-[:MANAGED_BY]->(e:Employee) RETURN e";
            return session.executeRead(tx->{
                Result result= tx.run(cypher);
                return result.list(record -> {
                    Employee employee = dataMapper.toObject(record.get("e").asMap(),Employee.class);
                    return employee;
                });
            });
        }
    }
    public Map<Employee,Double> getTotalIncomeOfEmployees(){
        try (Session session= Neo4jUtil.getSession()){
            String cypher = "MATCH(e:Employee)-[r:INVOLVE_IN]->(p:Project) RETURN e,sum(r.hours*200000*e.salaryCoefficient) AS salary";
            return session.executeRead(tx->{
                Map<Employee,Double> map= new HashMap<>();
                Result result = tx.run(cypher);
                result.list(record -> {
                    Employee employee = dataMapper.toObject(record.get("e").asMap(),Employee.class);
                    double salary= record.get("salary").asDouble();
                    map.put(employee,salary);
                    return  null;
                });
                return map;
            });
        }
    }
    public boolean updateRoleOfInvolvement(String empID, String prjID, Role newRole){
        try(Session session = Neo4jUtil.getSession()) {
            String cypher = "MATCH (e:Employee{id:$eid})-[r:INVOLVE_IN] ->(p:Project{id:$pid}) WHERE p.endDate>Date() SET r.role=$role";
            return session.executeWrite(tx->{
                Result result = tx.run(cypher,Values.parameters("eid",empID,"pid",prjID,"role",newRole.toString()));
                return  result.consume().counters().propertiesSet()>0;
            });
        }
    }
}
