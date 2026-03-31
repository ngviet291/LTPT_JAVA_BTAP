CREATE CONSTRAINT employee
FOR (e:Employee) REQUIRE e.id IS UNIQUE;

CREATE CONSTRAINT project
FOR (p:Project) REQUIRE p.id IS UNIQUE;

CREATE CONSTRAINT department
FOR (d:Department) REQUIRE d.id IS UNIQUE;

// Create person nodes
LOAD CSV WITH HEADERS FROM 'file:///hiha/employee.csv' AS row
MERGE (e:Employee {id:row.id,lastName: row.lastName, firstName: row.firstName,gender:row.gender,phone:row.phone,salaryCoefficient: toFloat(row.salaryCoefficient)});

// Create movie nodes
LOAD CSV WITH HEADERS FROM 'file:///hiha/project.csv' AS row
MERGE (p:Project {id: row.id, name: row.name, startDate:Date(row.startDate), endDate:Date(row.endDate),budget:toFloat(row.budget)});


LOAD CSV WITH HEADERS FROM 'file:///hiha/department.csv' AS row
MERGE (d:Department {id: row.id, name: row.name, location:row.location});

// Create relationships
LOAD CSV WITH HEADERS FROM 'file:///hiha/employee.csv' AS row
MATCH (e:Employee {id: row.id})
MATCH (d:Department {id: row.department_id})
MERGE (e)-[:BELONG_TO]->(d);

LOAD CSV WITH HEADERS FROM 'file:///hiha/department.csv' AS row
MATCH (e:Employee {id: row.manager_id})
MATCH (d:Department {id: row.id})
MERGE (d)-[:MANAGED_BY]->(e);

LOAD CSV WITH HEADERS FROM 'file:///hiha/involvement.csv' AS row
MATCH (e:Employee {id: row.employee_id})
MATCH (p:Project {id: row.project_id})
MERGE (e)-[:INVOLVE_IN {hours:toInteger(row.hours),role:row.role}]->(p);

CREATE FULLTEXT INDEX companyname IF NOT EXISTS
FOR (s:Supplier)
ON EACH [s.company_name]

CREATE FULLTEXT INDEX [index_name] [IF NOT EXISTS]
FOR (n:LabelName["|" ...])
ON EACH "[" n.propertyName[, ...] "]"
[OPTIONS "{" option: value[, ...] "}"]

