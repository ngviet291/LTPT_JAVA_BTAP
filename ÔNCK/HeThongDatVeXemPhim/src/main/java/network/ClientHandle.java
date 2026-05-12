/*
 * @ (#) ClientHandle.java     1.0    5/11/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package network;


/*
 * @description
 * @author:NguyenTruong
 * @date:  5/11/2026
 * @version:    1.0
 */

import dto.ShowDTO;
import entity.Type;
import service.MovieService;
import service.ShowService;
import service.impl.MovieServiceImpl;
import service.impl.ShowServiceImpl;

import java.io.*;
import java.net.Socket;

public class ClientHandle implements Runnable {
    private ShowService showService;
    private MovieService movieService;
    private Socket socket;

    public ClientHandle(Socket socket) {
        this.socket = socket;
       this. showService = new ShowServiceImpl();
       this. movieService = new MovieServiceImpl();
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Response response = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            while (true) {

                Request request = (Request) in.readObject();
                SELECTED selected = request.getSelected();
                switch (selected) {
                    case UPDATE: {
                        ShowDTO showDTO = (ShowDTO) request.getData();

                        response = Response.builder()
                                .data(showService.updateShowDateTime(showDTO.getId(), showDTO.getLocalDateTime()))
                                .success(true)
                                .build();
                        break;
                    }
                    case LISTSHOW: {
                        response= Response.builder()
                                .data(showService.listShowByCurrentDateAndDirector(request.getData().toString()))
                                .success(true)
                                .build();
                        break;
                    }
                }
                out.writeObject(response);
                out.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null && in != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
