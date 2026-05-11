/*
 * @ (#) Client.java     1.0    5/11/2026
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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket =null;
        ObjectOutputStream out =null;
        ObjectInputStream in =null;
        try{

             socket = new Socket("NguyenVanTruong",1133);
            Scanner sc = new Scanner(System.in);
             out = new ObjectOutputStream(socket.getOutputStream());
             in = new ObjectInputStream(socket.getInputStream());
            Request request=null;
            int choice =0;
            while(true){
            System.out.println("---Menu--");
            System.out.println("1.Update ");
            System.out.println("2.ListShow ");
                System.out.println("Vui lòng nhập lựa chọn ");
            choice= sc.nextInt();
            switch (choice){
                case 1->{
                    request = new Request();
                    LocalDateTime dateTimeDate = LocalDate.now().plusWeeks(1).atStartOfDay();
                   // boolean update =showService.updateShowDateTime("s028",dateTimeDate);
                    request.setData(new ShowDTO("s028",dateTimeDate));
                    request.setSelected(SELECTED.UPDATE);
                    break;
                }
                case 2->{
                    request = Request.builder()
                            .selected(SELECTED.LISTSHOW)
                            .data("The Wachowskis")
                            .build();
                    break;
                }
                default -> {
                    System.out.println("LUA CHON KO HOP LE ");
                    break;
                }
            }
            out.writeObject(request);

            Response response = (Response) in.readObject();
                System.out.println(response.toString());
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            out.close();
            in.close();
            socket.close();
        }
    }
}
