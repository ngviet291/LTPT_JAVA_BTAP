package com.ngviet291.service;

import com.ngviet291.dao.AlbumDao;
import com.ngviet291.entity.Album;

import java.util.List;
import java.util.Map;

public class AlbumService {
    private final AlbumDao albumDao;

    public AlbumService(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    public boolean updatePriceOfAlbum(String id, double newPrice){
        if(id.isEmpty()){
            throw new RuntimeException("Thieu Id");
        }

        return albumDao.updatePriceOfAlbum(id,newPrice);
    }
    public List<Album> listAlbumsByGenre(String genreName) {
        if(genreName.isEmpty()){
            throw  new RuntimeException("GenreName bi thieu");
        }
        return albumDao.listAlbumsByGenre(genreName);
    }
    public Map<String,Long> getNumberOfAlbumsByGenre() {
        return albumDao.getNumberOfAlbumsByGenre();
    }
}
