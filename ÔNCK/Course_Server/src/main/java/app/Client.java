package app;

import network.CommandType;
import network.Request;
import network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    static void main() {
        try (
                Socket socket = new Socket("DESKTOP-VIET",0721);
                ObjectOutputStream oos= new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Scanner scanner= new Scanner(System.in);
                ){
            while (true){
                System.out.println("1.Calculate the number of students in each department, the result is decreasing the number of students.");
                System.out.println("2. Calculate the average score of the students' courses");
                System.out.println("3. Departments without students");
                System.out.println("4. Students studying the subject's name \"Distributed Programming with Java Technology\" have the highest\n" +
                        "scores.");
                int choice = scanner.nextInt();
                Request request = null;
                switch (choice){
                    case 1->{
                        request = Request.builder().commandType(CommandType.GET_NUMBER_OF_STUDENTS_BY_DEPARTMENT).build();
                    }
                    case 2->{
                        request = Request.builder().commandType(CommandType.GET_AVERAGE_SCORE_OF_STUDENTS).build();
                    }
                    case 3->{
                        request= Request.builder().commandType(CommandType.LIST_DEPARTMENTS_WITHOUT_STUDENTS).build();
                    }
                    case 4->{
                        String name = "Microeconomics";
                        request= Request.builder().commandType(CommandType.LIST_STUDENTS_STUDYING_COURSE_WITH_HIGHEST_SCORE).data(name).build();
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

