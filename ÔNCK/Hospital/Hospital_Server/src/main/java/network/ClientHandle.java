package network;

import dto.PatientDTO;
import entity.Patient;
import service.DoctorService;
import service.PatientService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;

public class ClientHandle implements Runnable{
    private Socket socket;
    public ClientHandle(Socket socket) {
        this.socket=socket;
    }
    @Override
    public void run() {
        try (
                ObjectOutputStream obs= new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ){
            DoctorService doctorService= new DoctorService();
            PatientService patientService = new PatientService();
            while(true){

            Request request= (Request) ois.readObject();
            Response response = null;
            switch (request.getCommandType()){
                case ADD_PATIENT ->
                    response = Response.builder().data(patientService.addPatient((PatientDTO) request.getData())).build();
                case GET_NO_TREATMENTS_BY_DOCTORS -> {
                    LocalDate date= (LocalDate) request.getData();
                    response= Response.builder().data( doctorService.getNoTreatmentsByDoctors(date.getMonthValue(),date.getYear())).build();
                }
                case GET_DOCTORS_BY_DEPARTMENT -> {
                    response= Response.builder().data(doctorService.getDoctorsByDepartment((String) request.getData())).build();
                }
            }
            obs.writeObject(response);
            obs.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
