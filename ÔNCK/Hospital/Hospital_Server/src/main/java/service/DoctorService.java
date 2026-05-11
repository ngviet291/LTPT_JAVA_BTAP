package service;

import dao.impl.DoctorDAOImpl;
import dto.DoctorDTO;
import entity.Doctor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoctorService {
    private DoctorDAOImpl doctorDAO= new DoctorDAOImpl();
    public List<DoctorDTO> getDoctorsByDepartment(String deptName){

        return doctorDAO.getDoctorsByDepartment(deptName).stream().map(this::mapDTO).toList();
    }
    public Map<DoctorDTO,Integer> getNoTreatmentsByDoctors(int month, int year){
//        return doctorDAO.getNoTreatmentsByDoctors(month,year).entrySet().stream().collect(Collectors.toMap(this::mapDTO,Map.Entry::getValue));
        Map<DoctorDTO,Integer> doctorDTO= new LinkedHashMap<>();
        doctorDAO.getNoTreatmentsByDoctors(month,year).forEach((k,v)->doctorDTO.put(mapDTO(k),v));
        return doctorDTO;
    }
    public DoctorDTO mapDTO(Doctor doctor){
        DoctorDTO doctorDTO= DoctorDTO.builder().id(doctor.getId())
                .name(doctor.getName())
                .phone(doctor.getPhone())
                .speciality(doctor.getSpeciality())
                .build();
        return doctorDTO;
    }
}
