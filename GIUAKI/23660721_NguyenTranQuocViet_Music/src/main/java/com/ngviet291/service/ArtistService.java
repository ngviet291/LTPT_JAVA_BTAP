package com.ngviet291.service;

import com.ngviet291.dao.ArtistDao;
import com.ngviet291.entity.Artist;

public class ArtistService {
    public final ArtistDao artistDao;

    public ArtistService(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }

    public boolean addArtist(Artist artist){
        if(artist.getId().isEmpty()){
            throw new RuntimeException("Vui long nhap id");
        }

        return artistDao.addArtist(artist);
    }
}
