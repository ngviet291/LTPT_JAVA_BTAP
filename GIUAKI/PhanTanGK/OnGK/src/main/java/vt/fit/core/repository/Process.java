/*
 * @ (#)
 * CustomerRepository.java     1.0    3/8/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package vt.fit.core.repository;

import vt.fit.core.entity.Booking;
import vt.fit.core.entity.Customer;
import vt.fit.core.entity.Room;

import java.util.List;

/*
 * @description
 * @author:NguyenTruong
 * @date:  3/8/2026
 * @version:    1.0
 */
public interface Process {
    boolean addCustomer(Customer cus);
    boolean upDateRoom(String id,Room room);
    List<Booking>listBooking(int month,int year);
    List<Room>listRooms(String keyWord);
}
