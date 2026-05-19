package service.impl;

import dao.impl.ShowDAOImpl;
import dto.ShowDTO;
import entity.Show;

import java.time.LocalDateTime;
import java.util.List;

public class ShowServiceImpl {
    private final ShowDAOImpl showDAO = new ShowDAOImpl();
    public List<ShowDTO> listShowsByCurrentDateAndDirector(String director){
        return showDAO.listShowsByCurrentDateAndDirector(director).stream().map(this::toDTO).toList();
    }
    public ShowDTO toDTO(Show show){
        return ShowDTO.builder().id(show.getId()).showDateTime(show.getShowDateTime())
                .hallName(show.getHallName()).build();
    }
    public boolean updateShowDateTime(ShowDTO showDTO){
        Show show= showDAO.findById(showDTO.getId());
        if(!show.getTickets().isEmpty()){
            return false;
        }
        return showDAO.updateShowDateTime(showDTO.getId(),showDTO.getShowDateTime());
    }

}
