package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(9999);){
            System.out.println("Server Started");

            while(true){ //24/7
                Socket socket = serverSocket.accept();//1 client

                System.out.println(socket.getInetAddress().getHostName());
                System.out.println(socket.getRemoteSocketAddress());

                ClientHandler handler = new ClientHandler(socket);
                Thread thread = new Thread(handler);
                thread.start();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class ClientHandler implements Runnable{
    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try(PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);){

            while (true){
                //Received from client's message
                String message = in.readLine();
                System.out.println("Received from client's message: " + message);

                //Send back message to client
                System.out.println("Enter server's message: ");
                message = sc.nextLine();
                out.println(message); //Send message to client
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}