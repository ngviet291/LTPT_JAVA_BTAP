package app;

import network.ClientHandle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static void main() {
        try(ServerSocket serverSocket = new ServerSocket(0721)){
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
