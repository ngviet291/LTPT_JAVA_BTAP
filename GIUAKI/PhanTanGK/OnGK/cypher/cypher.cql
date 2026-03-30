

// tạo các unique
create constraint customer_id if not exists for(c:Customer) require c.id is unique;

CREATE CONSTRAINT room_id IF NOT EXISTS
FOR (r:Room)
REQUIRE r.id IS UNIQUE;

CREATE CONSTRAINT booking_id IF NOT EXISTS
FOR (b:Booking)
REQUIRE b.id IS UNIQUE;

CREATE CONSTRAINT bill_id IF NOT EXISTS
FOR (b:Bill)
REQUIRE b.id IS UNIQUE;

SHOW CONSTRAINTS

// LOAD DƯ LIEU LENid,description,type,bedOptions,sleepsCount,smokingAllowed,price
LOAD CSV WITH HEADERS
FROM 'file:///room.csv' AS row
CREATE (r:Room {
id: row.id,
description: row.description,
type: row.type,
bedOption: row.bedOption,
sleepsCount: toInteger(row.sleepsCount),
smokingAllowed: row.smokingAllowed,
price: toFloat(row.price)
});
LOAD CSV WITH HEADERS
FROM 'file:///customers.csv' AS row
CREATE (c:Customer {
id: row.id,
customerName: row.customerName,
phoneNumber: row.phoneNumber,
email: row.email
});

LOAD CSV WITH HEADERS
FROM 'file:///bills.csv' AS row
CREATE (b:Bill {
id: row.id,
days: toInteger(row.days),
serviceFee: toFloat(row.serviceFee),
totalAmount: toFloat(row.totalAmount),
booking_id: row.booking_id
});

LOAD CSV WITH HEADERS
FROM 'file:///bookings.csv' AS row
MERGE (b:Booking {id: row.id})
SET b.startDate = row.startDate,
b.endDate = row.endDate,
b.deposit = toFloat(row.deposit),
b.note = row.note;

// tạo relationship
LOAD CSV WITH HEADERS
FROM 'file:///bills.csv' AS row
MATCH(b:Bill{id:row.id})
MATCH(bk:Booking{id:row.booking_id})
CREATE (b)-[:CREATE_FROM]->(bk)


LOAD CSV WITH HEADERS
FROM 'file:///bookings.csv' AS row
MATCH (b:Booking {id: row.id})
MATCH (r:Room {id: row.room_ids})
CREATE (b)-[:BOOKING_FOR]->(r);

LOAD CSV WITH HEADERS
FROM 'file:///bookings.csv' AS row
MATCH (b:Booking {id: row.id})
MATCH (c:Customer {id: row.customer_id})
CREATE (b)-[:BOOKING_BY]->(c);

//MATCH (n)
//DETACH DELETE n; ==>XÓA RELATIONSHIP VÀ NODE

//MATCH ()-[r]->()
//DELETE r; ==>RELATIONSHIP
//MATCH (b:Booking)
//DETACH DELETE b; ==> XÓA THEO LABEL