package app;

import dto.MovieDTO;
import dto.ShowDTO;
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
        ObjectOutputStream oos= null;
        Socket socket = null;
        ObjectInputStream ois = null;
        try {
            socket = new Socket("DESKTOP-VIET",0721);
            oos= new ObjectOutputStream(socket.getOutputStream());
            ois= new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("1.Liet ke cac buoi bieu dien cua 1 dao dien nao do");
                System.out.println("2. Cap nhat ngay gio chieu phim");
                System.out.println("3. Them bo phim");
                Request  request = null;
                int choice =scanner.nextInt();
                switch (choice){
                    case 1->{
                        String director= "Ru";
                        request= Request.builder().commandType(CommandType.LIST_SHOW).data(director).build();
                    }
                    case 2->{
                        ShowDTO showDTO = ShowDTO.builder().id("S004").showDateTime(LocalDateTime.of(2024,2,20,23,4)).build();
                        request = Request.builder().commandType(CommandType.UPDATE_TIME_SHOW).data(showDTO).build();
                    }
                    case 3->{
                        MovieDTO movieDTO = MovieDTO.builder().id("M006").director("Viet")
                                .genre("He;loo")
                                .title("Phim con")
                                .releaseYear(2026)
                                .duration(5)
                                .actors(Set.of("asdasd","adsdsad","sadasdsa")).build();
                        request = Request.builder().commandType(CommandType.ADD_MOVIE).data(movieDTO).build();
                    }
                }
                oos.writeObject(request);
                oos.flush();
                Response response = (Response) ois.readObject();
                System.out.println(response);
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(oos!=null)
                    oos.close();
                if(ois!=null)
                    ois.close();
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
