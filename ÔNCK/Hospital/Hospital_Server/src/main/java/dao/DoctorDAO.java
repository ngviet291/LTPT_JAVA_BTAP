package dao;

import entity.Doctor;

import java.util.List;
import java.util.Map;

public interface DoctorDAO {
    List<Doctor> getDoctorsByDepartment(String deptName);

    Map<Doctor, Integer> getNoTreatmentsByDoctors(int month, int year);
}
