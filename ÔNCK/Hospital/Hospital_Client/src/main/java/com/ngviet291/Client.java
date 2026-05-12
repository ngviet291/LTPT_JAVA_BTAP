package com.ngviet291;

import dto.PatientDTO;
import entity.Patient;
import network.CommandType;
import network.Request;
import network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

public class Client {
    static void main() {

        try (
                Socket socket= new Socket("DESKTOP-ViET",0721);
                ObjectOutputStream obs= new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois= new ObjectInputStream(socket.getInputStream());
                Scanner scanner= new Scanner(System.in)
                ){
                while (true){
                    System.out.println("1. Them benh nhan");
                    System.out.println("2. Tim kiem danh sasch bac si theo khoa");
                    System.out.println("3. Thong ke so luot dieu tri cua bac si theo thang nam");
                    int choice = scanner.nextInt();
                    Request request= null;
                    switch (choice){
                        case 1  ->{
                            PatientDTO patient= PatientDTO.builder()
                                    .id("P026773ê3")
                                    .phone("032193213")
                                    .name("Viet")
                                    .gender("Male")
                                    .dateOfBirth(LocalDate.of(2005,1,29))
                                    .address("LHP").build();
                            request = Request.builder().commandType(CommandType.ADD_PATIENT).data(patient).build();
                        }
                        case 2->{
                            String deptName = "Neurology";
                            request= Request.builder().commandType(CommandType.GET_DOCTORS_BY_DEPARTMENT).data(deptName).build();
                        }
                        case 3->{
                            LocalDate localDate= LocalDate.of(2023,5,29);
                            request= Request.builder().commandType(CommandType.GET_NO_TREATMENTS_BY_DOCTORS).data(localDate).build();
                        }
                    }
                    obs.writeObject(request);
                    obs.flush();
                    Response response= (Response) ois.readObject();
                    System.out.println(response);
                }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
