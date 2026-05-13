package service;

import dao.ShowDAO;
import dto.MovieDTO;
import dto.ShowDTO;
import entity.Show;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ShowService {
    private ShowDAO showDAO = new ShowDAO();
    public List<ShowDTO> listShowsByCurrentDateAndDirector(String director){
        return showDAO.listShowsByCurrentDateAndDirector(director).stream().map(this::toDTO).toList();
    }
    public boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime){
        Show show =showDAO.findById(showId);
        if(show.getTickets()!=null&&!show.getTickets().isEmpty()){
            throw new RuntimeException("Không đc cập nhật vì đã có khách hàng đặt vé");
        }
        return showDAO.updateShowDateTime(showId,newShowDateTime);
    }
    public ShowDTO toDTO(Show show){
        MovieDTO movieDTO= MovieDTO.builder().id(show.getMovie().getId()).director(show.getMovie().getDirector()).build();
        return ShowDTO.builder().id(show.getId()).showDateTime(show.getShowDateTime()).movieDTO(movieDTO).build();
    }
}
