package Service;

import dao.VaccineDAO;
import dto.VaccineDTO;
import entity.Vaccine;

import java.util.LinkedHashMap;
import java.util.Map;

public class VaccineService {
    private final VaccineDAO vaccineDAO = new VaccineDAO();
    public Map<VaccineDTO, Integer> countRecordsByVaccines(){
        Map<VaccineDTO, Integer> map = new LinkedHashMap<>();
        vaccineDAO.countRecordsByVaccines().forEach((vaccine, integer) -> map.put(toDTO(vaccine),integer));
        return  map;
    }
    public VaccineDTO toDTO(Vaccine vaccine){
        return VaccineDTO.builder()
                .id(vaccine.getId())
                .name(vaccine.getName())
                .expirationDate(vaccine.getExpirationDate())
                .manufacturer(vaccine.getManufacturer())
                .build();
    }
}
