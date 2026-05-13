package service;

import dao.ShowDao;
import dto.ShowDTO;
import entity.Show;

import java.time.LocalDateTime;
import java.util.List;

public class ShowService {
    private ShowDao showDao= new ShowDao();
    public List<ShowDTO> listShowsByCurrentDateAndDirector(String director){
        List<Show> shows =
                showDao.listShowsByCurrentDateAndDirector(director);
        return shows.stream().map(this::toDTO).toList();
    }
    public boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime){
        return  showDao.updateShowDateTime(showId,newShowDateTime);
    }
    public ShowDTO toDTO(Show show){
        return ShowDTO.builder().id(show.getId()).hallName(show.getHallName()).showDateTime(show.getShowDateTime()).build();
    }
}
