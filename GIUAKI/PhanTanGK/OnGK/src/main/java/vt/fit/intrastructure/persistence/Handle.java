/*
 * @ (#) Handle.java     1.0    3/8/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package vt.fit.intrastructure.persistence;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/8/2026
 * @version:    1.0
 */

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import vt.fit.core.entity.Booking;
import vt.fit.core.entity.Customer;
import vt.fit.core.entity.Room;
import vt.fit.core.repository.Process;
import vt.fit.intrastructure.db.Neo4jConnectManager;
import vt.fit.intrastructure.mapper.GenericDataMapper;
import vt.fit.intrastructure.mapper.JackSonDataMapper;

import java.util.List;
import java.util.Map;

public class Handle implements Process {
    private Neo4jConnectManager con;
    private GenericDataMapper dataMapper;
    public Handle(Neo4jConnectManager con,GenericDataMapper map){
        this.dataMapper=map;
        this.con=con;
    }
    //id,customerName,phoneNumber,email
    @Override
    public boolean addCustomer(Customer cus) {
        String cypher ="Create (c:Customer{id:$id,customerName:$customerName,phoneNumber:$phoneNumber,email:$email})" ;
        Map<String,Object>params = dataMapper.toMap(cus);
        try(Session session = con.getSession()){
            return session.executeWrite(tx->{
                Result result=tx.run(cypher,params);
                return result.consume().counters().nodesCreated()>0;
            });
        }

        //return false;
    }
//    private String id;
//    private String description;
//    private String type;
//    private String bedOptions;
//    private int sleepsCount;
//    private boolean smokingAllowed;
//    private double price;
@Override
public boolean upDateRoom(String id, Room room) {

    String cypher = """
        MATCH (r:Room {id:$id})
        SET r.description = $description,
            r.type = $type,
            r.bedOptions = $bedOptions,
            r.sleepsCount = $sleepsCount,
            r.smokingAllowed = $smokingAllowed,
            r.price = $price
        """;

    Map<String,Object> params = dataMapper.toMap(room);
    params.remove("id");
    params.put("id", id);

    try(Session session = con.getSession()){
        return session.executeWrite(tx->{
            Result result = tx.run(cypher, params);
            return result.consume().counters().propertiesSet() > 0;
        });
    }
}
    @Override
    public List<Booking> listBooking(int month, int year) {
        String cypher = "MATCH (b:Booking) " +
                "WHERE b.startDate.month = $month " +
                "AND b.startDate.year = $year " +
                "RETURN b";
        Map<String,Object>map =Map.of("month",month,"year",year);
        try(Session session = con.getSession()){
            return session.executeRead(tx->{
               Result result = tx.run(cypher,map);
                return result.list(r->{
                    Node node = r.get("b").asNode();
                    return dataMapper.fromMap(node.asMap(),Booking.class);
                });
            });
        }
        //return List.of();
        //return null;
    }

    @Override
    public List<Room> listRooms(String keyWord) {
        return List.of();
    }

    public static void main(String[] args) {
         String dbName ="room";
         String pass="nguyenvantruong";
         String url ="neo4j://127.0.0.1:7687";
        Neo4jConnectManager con = new Neo4jConnectManager(dbName,"neo4j",pass,url);
        GenericDataMapper mapper = new JackSonDataMapper();
        Process process =new Handle(con,mapper);
        List<Booking>ds =process.listBooking(01,2022);
        ds.forEach(t-> System.out.println(t.toString()));
//        Customer cus = Customer.builder()
//                .id("C001")
//                .customerName("Nguyen Van Trường")
//                .phoneNumber("0123456789")
//                .email(" nguyevantruong@gmail.cpm")

//                .build();
//    boolean kq =process.addCustomer(cus);
//        if(kq){
//            System.out.println("Thêm khách hàng thành công");
//        }
        Room room = Room.builder()
                .id("R001")
                .description("Phòng Deluxe với view biển tuyệt đẹp")
                .type("Deluxe")
                .bedOptions("1 giường King")
                .sleepsCount(2)
                .smokingAllowed(false)
                .price(150.0)
                .build();
        boolean updateKq = process.upDateRoom("R101", room);
        if(updateKq){
            System.out.println("Cập nhật phòng thành công");
    }

}}
