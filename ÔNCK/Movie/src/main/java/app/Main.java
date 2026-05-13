package app;


import dto.MovieDTO;
import dto.ShowDTO;
import network.ClientHandle;
import service.MovieService;
import service.ShowService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static void main() {

//    ShowService showService = new ShowService();
//        List<ShowDTO> shows=showService.listShowsByCurrentDateAndDirector("Bong");
//        for(ShowDTO show:shows){
//            System.out.println(show);
//        }
//        if(
//                showService.updateShowDateTime("S004", LocalDateTime.of(2026,12,29,3,0))){
//
//        System.out.println("thanh cong");
//        }else {
//            System.out.println("7");
//        }
//        MovieService movieService= new MovieService();
//        MovieDTO movieDTO = MovieDTO.builder().id("M4432").title("ascsa")
//                .director("2wsqqwe")
//                .releaseYear(2004)
//                .genre("sadsa")
//                .duration(36)
//                .build();
//        if(movieService.addMovie(movieDTO)){
//            System.out.println("oke");
//        }
//        else {
//            System.out.println("not oke");
//        }
//    }
        try (
                ServerSocket serverSocket = new ServerSocket(0721);
                ){
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress());
                executorService.execute(new ClientHandle(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
