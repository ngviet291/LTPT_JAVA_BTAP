/*
 * @ (#) Main.java     1.0    5/11/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */


/*
 * @description
 * @author:NguyenTruong
 * @date:  5/11/2026
 * @version:    1.0
 */

import service.MovieService;
import service.ShowService;
import service.impl.MovieServiceImpl;
import service.impl.ShowServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ShowService showService = new ShowServiceImpl();
        MovieService movieService = new MovieServiceImpl();

        showService.listShowByCurrentDateAndDirector("The Wachowskis").forEach(
                v-> System.out.println(v.toString())
        );
        LocalDateTime dateTimeDate = LocalDate.now().plusWeeks(1).atStartOfDay();
        boolean update =showService.updateShowDateTime("s028",dateTimeDate);
        if(update){
            System.out.println("Cập nhật thành công ....");
        }
    }
}
