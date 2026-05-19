import dto.RecordDTO;
import dto.UpdateStatusDTO;
import entity.DoseStatus;
import network.CommandType;
import network.Request;
import network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Scanner;

public class Client {
    static void main() {
        Socket socket = null;
        ObjectOutputStream oos= null;
        ObjectInputStream ois = null;
        Scanner scanner = null;
        try {
            socket = new Socket("DESKTOP-VIET",0721);
            oos= new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            scanner = new Scanner(System.in);
            Request request = null;
            while (true){
                System.out.println("1. Thêm record");
                System.out.println("2. Update lịch");
                System.out.println("3. Danh sách bmi >25");
                System.out.println("4. Đếm vácxin tăng dần");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1->{
                        RecordDTO recordDTO=RecordDTO.builder().id(22).doseNumber(1)
                        .injectionDate(LocalDate.now())
                        .status(DoseStatus.SCHEDULED)
                        .personId("P001")
                        .vaccineId("V001").build();
                        request = Request.builder().commandType(CommandType.ADD_RECORD).data(recordDTO).build();
                    }
                    case 2->{
                        UpdateStatusDTO updateStatusDTO = UpdateStatusDTO.builder().recordId(22L).doseStatus(DoseStatus.MISSED).build();
                        request = Request.builder().commandType(CommandType.UPDATE_SCHEDULE).data(updateStatusDTO).build();
                    }
                    case 3->{
                        request = Request.builder().commandType(CommandType.LIST_OBESE_PERSONS).build();
                    }
                    case 4->{
                        request = Request.builder().commandType(CommandType.COUNT_RECORDS_BY_STATUS).build();
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
