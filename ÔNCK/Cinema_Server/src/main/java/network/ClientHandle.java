package network;

import dto.MovieDTO;
import dto.ShowDTO;
import service.impl.MovieServiceImpl;
import service.impl.ShowServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

public class ClientHandle implements Runnable {
    private Socket socket;
    public ClientHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois=null;
        ObjectOutputStream oos= null;
        try {
            oos= new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            ShowServiceImpl showService = new ShowServiceImpl();
            MovieServiceImpl movieService = new MovieServiceImpl();
            while (true){
                Response response = null;
                Request request = (Request) ois.readObject();
                switch (request.getCommandType()){
                    case LIST_SHOW -> {
                        response = Response.builder().data(showService.listShowsByCurrentDateAndDirector((String) request.getData())).build();
                    }
                    case UPDATE_TIME_SHOW -> {
                        ShowDTO showDTO = (ShowDTO) request.getData();
                        response = Response.builder().data(showService.updateShowDateTime(showDTO)).build();
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
