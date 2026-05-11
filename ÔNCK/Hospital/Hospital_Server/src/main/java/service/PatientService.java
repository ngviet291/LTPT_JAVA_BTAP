package service;

import dao.impl.PatientDAOImpl;
import dto.PatientDTO;
import entity.Patient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientService {
    private final PatientDAOImpl patientDAO= new PatientDAOImpl();
    public boolean addPatient(PatientDTO patient){
        System.out.println(patient);
        Patient patient1= Patient.builder().id(patient.getId())
                .name(patient.getName())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .address(patient.getAddress())
                .phone(patient.getPhone())
                .build();
        return patientDAO.addPatient(patient1);
    }
}
