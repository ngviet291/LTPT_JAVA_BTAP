package com.ngviet291.dao;

import com.ngviet291.db.Neo4jUtil;
import com.ngviet291.entity.Album;
import com.ngviet291.entity.Genre;
import com.ngviet291.mapper.DataMapper;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AlbumDao {
    private final DataMapper dataMapper;

    public AlbumDao(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public boolean updatePriceOfAlbum(String id, double newPrice){
        try (Session session= Neo4jUtil.getSession()){
            String cypher="MATCH(a:Album{id:$id}) SET a.price=$price";
            return session.executeWrite(tx->{
                Result result= tx.run(cypher, Values.parameters("id",id,"price",newPrice));
                return result.consume().counters().propertiesSet()>0;
            });
        }
    }
    public List<Album> listAlbumsByGenre(String genreName){
        try (Session session= Neo4jUtil.getSession()){
            String cypher = "MATCH(a:Album) - [:BELONG_TO] -> (g:Genre {name:$name}) return a,g";
            return session.executeRead(tx->{
                Result result= tx.run(cypher,Values.parameters("name",genreName));
                //Trả về list cái result
                return result.list(record ->{
                    Album album=dataMapper.toObject(record.get("a").asMap(),Album.class);
                    Genre genre= dataMapper.toObject(record.get("g").asMap(),Genre.class);
                    //set Genre rồi trả về album
                    album.setGenre(genre);
                    return album;
                });
            });
        }
    }
    public Map<String,Long> getNumberOfAlbumsByGenre(){
        try (Session session= Neo4jUtil.getSession()){
            String cypher= "MATCH(a:Album) -[:BELONG_TO] -> (g:Genre) RETURN g.name as genre,COUNT(a) AS total ORDER BY g.name ASC;";

            return session.executeRead(tx->{
                Map<String,Long> map= new LinkedHashMap<>();
                Result result=tx.run(cypher);
                result.list(r->{
                    String genre= r.get("genre").asString();
                    long total = r.get("total").asLong();
                    map.put(genre,total);
                    return null;
                });
                return map;
            });

        }
    }
}
