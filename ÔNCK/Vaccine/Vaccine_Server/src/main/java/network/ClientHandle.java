package network;

import Service.PersonService;
import Service.RecordService;
import Service.VaccineService;
import dto.RecordDTO;
import dto.UpdateStatusDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandle implements Runnable {
    private final Socket socket;
    public ClientHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectOutputStream oos= null;
        ObjectInputStream ois= null;
        try {
            oos= new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            PersonService personService = new PersonService();
            RecordService recordService = new RecordService();
            VaccineService vaccineService = new VaccineService();
            while (true){
                Request request= (Request) ois.readObject();
                Response response = null;
                switch (request.getCommandType()){
                    case ADD_RECORD -> {
                        response = Response.builder().data(recordService.createNewRecord((RecordDTO) request.getData())).build();
                    }
                    case UPDATE_SCHEDULE -> {
                        response = Response.builder().data(recordService.updateScheduledRecord((UpdateStatusDTO) request.getData())).build();
                    }
                    case LIST_OBESE_PERSONS -> {
                        response = Response.builder().data(personService.listObesePeople()).build();
                    }
                    case COUNT_RECORDS_BY_STATUS -> {
                        response= Response.builder().data(vaccineService.countRecordsByVaccines()).build();
                    }
                }
                oos.writeObject(response);
                oos.flush();
            }
            } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            try {

            if(oos!=null){
                oos.close();
            }
            if(ois!=null){
                ois.close();
            }
            if(socket!=null){
                socket.close();
            }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
