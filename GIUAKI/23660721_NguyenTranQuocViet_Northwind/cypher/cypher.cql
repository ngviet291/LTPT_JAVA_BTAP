CREATE CONSTRAINT supplier
FOR (s:Supplier) REQUIRE s.supplier_id IS UNIQUE;

CREATE CONSTRAINT product
FOR (p:Product) REQUIRE p.product_id IS UNIQUE;

CREATE CONSTRAINT order
FOR (o:Order) REQUIRE o.order_id IS UNIQUE;


// Create `Book` nodes
LOAD CSV WITH HEADERS FROM 'file:///northwind/suppliers.csv' AS row
MERGE (s:Supplier {supplier_id: row.supplier_id, company_name: row.company_name,contact_name:row.contact_name,country:row.country});

LOAD CSV WITH HEADERS FROM 'file:///northwind/orders.csv' AS row
MERGE (o:Order {order_id: row.order_id,customer_name: row.customer_name,employee_name:row.employee_name,order_date:Date(row.order_date),status:row.status});

LOAD CSV WITH HEADERS FROM 'file:///northwind/products.csv' AS row
MERGE (p:Product {product_id: row.product_id,product_name: row.product_name,unit:row.unit,unit_price:toFloat(row.unit_price),units_in_stock:toInteger(row.units_in_stock)});

// Create `WROTE` relationships
LOAD CSV WITH HEADERS FROM 'file:///northwind/products.csv' AS row
MATCH (p:Product{product_id: row.product_id})
MATCH (s:Supplier{supplier_id: row.supplier_id})
MERGE (s)-[:SUPPLIES]->(p);

LOAD CSV WITH HEADERS FROM 'file:///northwind/order_details.csv' AS row
MATCH (p:Product{product_id: row.product_id})
MATCH (o:Order{order_id: row.order_id})
MERGE (o)-[:ORDERS{quantity:toInteger(row.quantity),unit_price:toFloat(row.unit_price),discount:toFLoat(row.discount)}]->(p);


CREATE RANGE INDEX companyName IF NOT EXISTS
FOR (s:Supplier)
ON (s.company_name)

CREATE FULLTEXT INDEX companyName FOR (s:Supplier) ON EACH [s.company_name]