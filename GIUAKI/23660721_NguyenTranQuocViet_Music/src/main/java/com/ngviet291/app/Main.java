package com.ngviet291.app;

import com.ngviet291.dao.AlbumDao;
import com.ngviet291.dao.ArtistDao;
import com.ngviet291.entity.Album;
import com.ngviet291.entity.Artist;
import com.ngviet291.mapper.DataMapper;
import com.ngviet291.service.AlbumService;
import com.ngviet291.service.ArtistService;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
//        Artist artist= new Artist("101","ASS", LocalDate.of(2020,01,29),"asdjasdas");
//        ArtistDao artistDao= new ArtistDao();
//        ArtistService artistService= new ArtistService(artistDao);
//        artistService.addArtist(artist);


//        Album album= Album.builder()
//                .id("ALB1")
//                .build();
        DataMapper dataMapper= new DataMapper();
        AlbumDao albumDao= new AlbumDao(dataMapper);
        AlbumService albumService= new AlbumService(albumDao);
        if(

        albumService.updatePriceOfAlbum("AL005",160000)
        ){
            System.out.println("Cap nhat thanh cong");
        }
        else System.out.println("Cap nhat that bai");
        albumService.listAlbumsByGenre("Pop").forEach(System.out::println);
        System.out.println(albumService.getNumberOfAlbumsByGenre());;
    }
}
