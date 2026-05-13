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

public class Client {
    static void main() {
        try (
                Socket socket = new Socket("DESKTOP-VIET",0721);
                ObjectOutputStream  oos= new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois= new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)
                ){
                while (true){
                    System.out.println("1. Liệt kê các buổi chiếu phim");
                    System.out.println("2. Cap nhat ngay gio vuoi chieu phim");
                    System.out.println("3. Them 1 bo phim");
                    int choice = scanner.nextInt();
                    Request request=null;
                    switch (choice){
                        case 1->{
                            String name = "Bong";
                            request = Request.builder().commandType(CommandType.LIST_SHOWS).data(name).build();
                        }
                        case 2->{
                            ShowDTO showDTO = ShowDTO.builder().id("S004").showDateTime(LocalDateTime.of(2026,12,29,3,0)).build();
                            request = Request.builder().commandType(CommandType.UPDATE_SHOW).data(showDTO).build();
                        }
                        case 3->{
                            MovieDTO movieDTO = MovieDTO.builder().id("M45543").title("ascsa")
                .director("2wsqqwe")
                .releaseYear(2004)
                .genre("sadsa")
                .duration(36)
                .build();
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
    }
}
