package network;

import dto.MovieDTO;
import dto.ShowDTO;
import service.MovieService;
import service.ShowService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandle implements Runnable {
    private Socket socket;
    public ClientHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ){
            MovieService movieService = new MovieService();
            ShowService showService= new ShowService();
            while (true){
                Request request= (Request) ois.readObject();
                Response response = null;
                switch (request.getCommandType()){
                    case LIST_SHOWS -> {
                        response = Response.builder().data(showService.listShowsByCurrentDateAndDirector((String) request.getData())).build();
                    }
                    case UPDATE_SHOW -> {
                        ShowDTO showDTO = (ShowDTO) request.getData();
                        boolean result = showService.updateShowDateTime(showDTO.getId(),showDTO.getShowDateTime());
                        response = Response.builder().data(result).build();
                    }
                    case ADD_MOVIE -> {
                        MovieDTO movieDTO = (MovieDTO) request.getData();
                        response = Response.builder().data(movieService.addMovie(movieDTO)).build();
                    }
                }
                oos.writeObject(response);
                oos.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
