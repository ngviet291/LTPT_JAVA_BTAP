package Service;

import dao.PersonDAO;
import dto.PersonDTO;
import entity.Person;

import java.util.List;

public class PersonService {
    private final PersonDAO personDAO = new PersonDAO();
    public List<PersonDTO> listObesePeople(){

        return  personDAO.listObesePeople().stream().map(this::toDTO).toList();
    }
    public PersonDTO toDTO(Person person){
        return PersonDTO.builder().id(person.getId()).fullname(person.getFullname()).height(person.getHeight()).gender(person.getGender()).weight(person.getWeight()).build();
    }
}
