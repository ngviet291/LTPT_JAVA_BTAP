package app;

import dao.MovieDao;
import dao.ShowDao;
import entity.Movie;
import entity.Show;
import network.ClientHandle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static void main() {
//        ShowDao showDao= new ShowDao();
//
//        List<Show> shows=showDao.listShowsByCurrentDateAndDirector("o");
//        for(Show show:shows){
//            System.out.println(show);
//        }
//        showDao.updateShowDateTime("S001", LocalDateTime.of(2026,7,12,18,19));
//        MovieDao movieDao= new MovieDao();
//        movieDao.addMovie(Movie.builder().id("V004").director("Viet").releaseYear(2024).duration(5).genre("Ssa").title("Iuwq").actors(Set.of("asdsa","asdasd")).build());
        try (
                ServerSocket serverSocket= new ServerSocket(3636);
                ){
            ExecutorService executorService= Executors.newFixedThreadPool(5);

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
