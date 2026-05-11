package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {

        try(Socket socket = new Socket("DESKTOP-GFO363L", 9999);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);
        ){
            while (true) {
                System.out.println("Enter client's message: ");
                String message = sc.nextLine();
                out.println(message); //Send message to server

                message = in.readLine(); //Received from server
                System.out.println("Received from server's message: " + message);
            }
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
