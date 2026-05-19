package service.impl;

import dao.impl.MovieDAOImpl;
import dto.MovieDTO;
import entity.Movie;

import java.util.Set;

public class MovieServiceImpl {
    private final MovieDAOImpl movieDAO= new MovieDAOImpl();
    public boolean addMovie(MovieDTO movieDTO){
        if(!movieDTO.getId().matches("M\\d{3,}")){
            return false;
        }
        if(movieDTO.getDuration()<=0){
            return false;
        }
        Movie movie= Movie.builder()
                .id(movieDTO.getId())
                .genre(movieDTO.getGenre())
                .duration(movieDTO.getDuration())
                .title(movieDTO.getTitle())
                .director(movieDTO.getDirector())
                .releaseYear(movieDTO.getReleaseYear())
                .actors(movieDTO.getActors()).build();
        return movieDAO.addMovie(movie);
    }
}
