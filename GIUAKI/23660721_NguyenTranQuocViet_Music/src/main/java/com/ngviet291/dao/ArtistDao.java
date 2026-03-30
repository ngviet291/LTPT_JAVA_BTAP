package com.ngviet291.dao;

import com.ngviet291.db.Neo4jUtil;
import com.ngviet291.entity.Artist;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

public class ArtistDao {

    public boolean addArtist(Artist artist){
        try (Session session = Neo4jUtil.getSession()){
            String cypher="" +
                    "CREATE (:Artist {id:$id,name : $name,birthDate : $birthDate, url: $url})" ;
            return session.executeWrite(tx->{
                Result result=tx.run(cypher, Values.parameters(
                                "id",artist.getId(),
                                "name",artist.getName(),
                                "birthDate",artist.getBirthDate(),
                                "url",artist.getUrl()
                        )
                );
                return result.consume().counters().nodesCreated()>0;
            });
        }

    }

}
