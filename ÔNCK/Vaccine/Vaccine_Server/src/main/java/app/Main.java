package app;

import Service.PersonService;
import Service.RecordService;
import Service.VaccineService;
import dto.PersonDTO;
import dto.RecordDTO;
import dto.UpdateStatusDTO;
import dto.VaccineDTO;
import entity.DoseStatus;
import network.ClientHandle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static void main() throws IOException {
        RecordService recordService = new RecordService();

//        RecordDTO recordDTO=RecordDTO.builder().id(21).doseNumber(1)
//                .injectionDate(LocalDate.now())
//                .status(DoseStatus.SCHEDULED)
//                .personId("P001")
//                .vaccineId("V001").build();
//        recordService.createNewRecord(recordDTO);
//        UpdateStatusDTO updateStatusDTO = UpdateStatusDTO.builder().recordId(21L).doseStatus(DoseStatus.COMPLETED).build();
//        if(recordService.updateScheduledRecord(updateStatusDTO)){
//            System.out.println("update oke");
//        };

//        PersonService personService = new PersonService();
//        List<PersonDTO> list = personService.listObesePeople();
//        for(PersonDTO personDTO: list){
//            System.out.println(personDTO);
//        }
//        VaccineService vaccineService = new VaccineService();
//        Map<VaccineDTO, Integer> map = vaccineService.countRecordsByVaccines();
//        map.forEach((vaccineDTO, integer) -> System.out.println(vaccineDTO+" : "+integer));
        ServerSocket serverSocket= null;
        try {
            serverSocket = new ServerSocket(0721);
            ExecutorService es= Executors.newFixedThreadPool(5);
            while (true){
            Socket socket = serverSocket.accept();
            System.out.println(serverSocket.getInetAddress());
            es.execute(new ClientHandle(socket));
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
