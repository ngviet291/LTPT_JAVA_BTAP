package app;

import dto.ShowDTO;
import service.impl.ShowServiceImpl;

import java.util.List;

public class Main {
    static void main() {
        ShowServiceImpl showService= new ShowServiceImpl();
        List<ShowDTO> shows= showService.listShowsByCurrentDateAndDirector("Ru");
        for(ShowDTO show:shows){
            System.out.println(show);
        }
    }
}
