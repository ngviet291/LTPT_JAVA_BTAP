package app;

import network.ClientHandle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static void main() throws IOException {
        ServerSocket serverSocket=null;
        try {
            serverSocket= new ServerSocket(0721);
            ExecutorService service = Executors.newFixedThreadPool(5);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println(serverSocket.getInetAddress());
                service.execute(new ClientHandle(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(serverSocket!=null){
                serverSocket.close();
            }
        }
    }
}
