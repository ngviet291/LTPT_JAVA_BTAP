/*
 * @ (#) MovieServiceImpl.java     1.0    5/11/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package service.impl;


/*
 * @description
 * @author:NguyenTruong
 * @date:  5/11/2026
 * @version:    1.0
 */

import dao.MovieDao;
import dao.impl.MovieImpl;
import entity.Movie;

import java.io.Serializable;

public class MovieServiceImpl implements Serializable, service.MovieService {
    MovieDao movieDao = new MovieImpl();
    @Override
    public boolean addMovie(Movie movie){
        if(!(movie.getId().matches("^M\\d{3,}"))){
            return false;
        }
    if(movie.getDuration()<0){
        return false;
    }
    return movieDao.addMovie(movie);
    }
}
