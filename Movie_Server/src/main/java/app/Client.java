package app;

import dto.MovieDTO;
import dto.ShowDTO;
import entity.Movie;
import network.CommandType;
import network.Request;
import network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;

public class Client {
    static void main() {
        try (
                Socket socket = new Socket("LAPTOP-VIET",3636);
                ObjectOutputStream oos= new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in);
                ){
            while (true){
                System.out.println("1.Liet ke cac buoi chieu phim cua dao dien: ");
                System.out.println("2.Cap nhat ngay gio chieu phim");
                System.out.println("3.Them 1 bo phim");
                int choice = scanner.nextInt();
                Request request= null;
                switch (choice){
                    case 1->{
                        String director= "o";
                        request= Request.builder().commandType(CommandType.LIST_SHOW).data(director).build();
                    }
                    case 2->{
                        ShowDTO showDTO = ShowDTO.builder().id("S001").showDateTime(LocalDateTime.of(2034,10,28,10,2)).build();
                        request= Request.builder().commandType(CommandType.UPDATE_SHOW).data(showDTO).build();
                    }
                    case 3->{
                        MovieDTO movieDTO = MovieDTO.builder().id("V004").director("Viet").releaseYear(2024).duration(5).genre("Ssa").title("Iuwq").actors(Set.of("asdsa","asdasd")).build();
                        request= Request.builder().commandType(CommandType.ADD_MOVIE).data(movieDTO).build();
                    }
                }
                oos.writeObject(request);
                oos.flush();
                Response response= (Response) ois.readObject();
                System.out.println(response);
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
