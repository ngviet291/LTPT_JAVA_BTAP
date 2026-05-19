package Service;

import dao.PersonDAO;
import dao.RecordDAO;
import dao.VaccineDAO;
import dto.PersonDTO;
import dto.RecordDTO;
import dto.UpdateStatusDTO;
import entity.DoseStatus;
import entity.Person;
import entity.Record;
import entity.Vaccine;

import java.time.LocalDate;

public class RecordService {
    private final RecordDAO recordDAO = new RecordDAO();
    public boolean createNewRecord(RecordDTO recordDTO){
        if(recordDTO.getDoseNumber()<=0){
            throw  new RuntimeException("So mui tiem phai lon hon 0");
        }
        if(recordDTO.getInjectionDate()
                .isBefore(LocalDate.now())) {

            throw new RuntimeException(
                    "Ngay tiem phai sau hoac bang ngay hien tai"
            );
        }
        PersonDAO personDAO = new PersonDAO();
        Person person =personDAO.findById(recordDTO.getPersonId());

        VaccineDAO vaccineDAO = new VaccineDAO();
        Vaccine vaccine=vaccineDAO.findById(recordDTO.getVaccineId());
        if(person == null){
            throw new RuntimeException("Khong tim thay nguoi dan");
        }

        if(vaccine == null){
            throw new RuntimeException("Khong tim thay vaccine");
        }
        Record record = Record.builder().id(recordDTO.getId())
                .doseNumber(recordDTO.getDoseNumber())
                .injectionDate(recordDTO.getInjectionDate())
                .status(recordDTO.getStatus())
                .vaccine(vaccine)
                .person(person).build();
        return  recordDAO.createNewRecord(record);
    }
    public boolean updateScheduledRecord(UpdateStatusDTO updateStatusDTO){
        Record record = recordDAO.findById(updateStatusDTO.getRecordId());
        if(record==null) {
            throw new RuntimeException("Khong thay record");
        }
        if(!record.getInjectionDate().isEqual(LocalDate.now()) || !record.getStatus().equals(DoseStatus.SCHEDULED)){
            throw new RuntimeException("Ngày tiêm là ngày hiện tại và chỉ được phép cập nhật trạng thái mới nếu trạng thái hiện tại của lượt tiêm là đã lên lịch");
        }

        return recordDAO.updateScheduledRecord(updateStatusDTO.getRecordId(),updateStatusDTO.getDoseStatus());
    }
}
