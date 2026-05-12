package app;

import entity.Doctor;
import entity.Patient;
import network.ClientHandle;
import service.DoctorService;
import service.PatientService;

import javax.print.Doc;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static void main() {
//        Patient patient = Patient.builder().id("P009").name("Khong Lo").phone("09092323312").address("Nguyen Van Bao").dateOfBirth(LocalDate.of(2005,1,29)).gender("Male").build();
//        PatientService patientService= new PatientService();
//        patientService.addPatient(patient);
//        DoctorService doctorService= new DoctorService();
//        List<Doctor> doctors = doctorService.getDoctorsByDepartment("Cardiology");
//        for(Doctor doctor:doctors){
//            System.out.println(doctor);
//        }
//        Map<Doctor,Integer> map = doctorService.getNoTreatmentsByDoctors(05,2023);
//        map.forEach((doctor, integer) -> System.out.println(doctor+" : "+integer));
        try (
                ServerSocket serverSocket= new ServerSocket(0721)
                ){
            ExecutorService service= Executors.newFixedThreadPool(4);

            while (true){
                Socket socket= serverSocket.accept();
                service.execute(new ClientHandle(socket));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
