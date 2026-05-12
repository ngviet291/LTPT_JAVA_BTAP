package service;

import dao.MovieDao;
import dto.MovieDTO;
import entity.Movie;

public class MovieService {
    private MovieDao movieDao= new MovieDao();
    public boolean addMovie(MovieDTO movieDTO){
        Movie movie = Movie.builder()
                .id(movieDTO.getId())
                .director(movieDTO.getDirector())
                .genre(movieDTO.getGenre())
                .duration(movieDTO.getDuration())
                .releaseYear(movieDTO.getReleaseYear())
                .title(movieDTO.getTitle())
                .build();
        if(!movieDTO.getId().matches("M\\d{3,}"))
            return false;
        if(!(movieDTO.getDuration() >0))
            return false;
        return movieDao.addMovie(movie);
    }
}
