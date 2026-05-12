package network;

import dto.PersonDTO;
import dto.RecordDTO;
import dto.VaccineDTO;
import service.IPersonService;
import service.IRecordService;
import service.IVaccineService;
import service.impl.PersonService;
import service.impl.RecordService;
import service.impl.VaccineService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(9999);
            ExecutorService pool = Executors.newFixedThreadPool(10);
        ){
            System.out.println("Server Started");

            while(true){ //24/7
                Socket socket = serverSocket.accept();//1 client

                System.out.println(socket.getInetAddress().getHostName());
                System.out.println(socket.getRemoteSocketAddress());

                RequestHandler handler = new RequestHandler(socket);
                pool.submit(handler);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class RequestHandler implements Runnable{
    private Socket socket;
    private final IPersonService personService = new PersonService();
    private final IVaccineService vaccineService = new VaccineService();
    private final IRecordService recordService = new RecordService();

    public RequestHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try(ObjectOutputStream out  = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in  = new ObjectInputStream(socket.getInputStream());){

            while (true){
                //2. Receive request from client
                Request request = (Request) in.readObject();

                //3. Processing request
                Response response = processingRequest(request);

                //4. Return response to client
                out.writeObject(response);
                out.flush();
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private Response processingRequest(Request request) {
        Response response = null;
        switch (request.getCommandType()){
            case CREATE_RECORD -> {
                RecordDTO recordDTO = (RecordDTO) request.getData();
                boolean created = recordService.createNewRecord(recordDTO);
                response = Response.builder()
                        .success(created)
                        .data(null)
                        .message(created? "Create: Success" : "Create: Fail")
                        .build();
            }
            case UPDATE_RECORD -> {
                RecordDTO recordDTO = (RecordDTO) request.getData();
                boolean updated = recordService.updateScheduledRecord(recordDTO.getRecordId(), recordDTO.getStatus());
                response = Response.builder()
                        .success(updated)
                        .data(null)
                        .message(updated? "Update: Success" : "Update: Fail")
                        .build();
            }
            case LIST_OBESE_PEOPLE -> {
                List<PersonDTO> personDTOs = personService.listObesePeople();
                response = Response.builder()
                        .success(true)
                        .data(personDTOs)
                        .message(personDTOs.size()+" obese people found")
                        .build();
            }
            case COUNT_VACCINES -> {
                Map<VaccineDTO, Integer> vaccines = vaccineService.countRecordsByVaccines();
                String vaccineId = (String) request.getData();
                long numOfVaccines = vaccines.entrySet()
                        .stream()
                        .filter(entry -> entry.getKey().getVaccineId().equals(vaccineId))
                        .map(Map.Entry::getValue)
                        .findFirst()
                        .orElse(0);

                response = Response.builder()
                        .success(true)
                        .data(null)
                        .message("Number of vaccines with id "+vaccineId+" is "+numOfVaccines)
                        .build();
            }
        }

        return response;
    }
}
