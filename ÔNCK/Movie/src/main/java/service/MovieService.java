package service;

import dao.MovieDAO;
import dto.MovieDTO;
import entity.Movie;

public class MovieService {
    private MovieDAO movieDAO = new MovieDAO();
    public boolean addMovie(MovieDTO movieDTO){
        if(!movieDTO.getId().matches("M\\d{3,}")){
            throw new RuntimeException("Ma phai bat dau bang M");
        }
        if(movieDTO.getDuration()<0){
            throw new RuntimeException("Thoi luong phai lon hon 0");
        }
        Movie movie = Movie.builder().id(movieDTO.getId()).title(movieDTO.getTitle()).releaseYear(movieDTO.getReleaseYear())
                .director(movieDTO.getDirector())
                .duration(movieDTO.getDuration())
                .genre(movieDTO.getGenre())
                .build();
        return movieDAO.addMovie(movie);
    }
}
