package network;

import dto.RecordDTO;
import entity.DoseStatus;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try(Socket socket = new Socket("DESKTOP-GFO363L", 9999);
            ObjectOutputStream out  = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in  = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
        ){
            while(true){
                System.out.println("==Menu==");
                System.out.println("1. Create record");
                System.out.println("2. Update record");
                System.out.println("3. List obese people");
                System.out.println("4. Count vaccines");

                int choice = sc.nextInt();
                Request request = new Request();

                switch (choice){
                    case 1 -> {
                        RecordDTO recordDTO = RecordDTO
                                .builder()
                                .personId("P005")
                                .vaccineId("V008")
                                .doseNumber(2)
                                .injectionDate(LocalDate.now().plusDays(10))
                                .build();

                        request.setCommandType(CommandType.CREATE_RECORD);
                        request.setData(recordDTO);
                    }
                    case 2 -> {
                        RecordDTO recordDTO = RecordDTO
                                .builder()
                                .recordId(8l)
                                .status(DoseStatus.COMPLETED)
                                .build();

                        request.setCommandType(CommandType.UPDATE_RECORD);
                        request.setData(recordDTO);
                    }
                    case 3 ->
                        request.setCommandType(CommandType.LIST_OBESE_PEOPLE);

                    case 4 -> {
                        String vaccineId = "V008";
                        request.setCommandType(CommandType.COUNT_VACCINES);
                        request.setData(vaccineId);
                    }
                }

                //1. Send request to server
                out.writeObject(request);
                out.flush();

                //5. Receive response from server
                Response response = (Response) in.readObject();
                System.out.println(response);
            }

        }catch (Exception e){
            throw  new RuntimeException(e);
        }

    }
}
