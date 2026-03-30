package com.ngviet291.db;

import org.neo4j.driver.*;

public class Neo4jUtil {
    public static final String URL="neo4j://127.0.0.1:7687";
    public static final String USER="neo4j";
    public static final String PWD="sapassword";
    public static final String DB="viet23660721music";
    public static Driver driver;
    public Neo4jUtil() {
    }
    static {
        driver= GraphDatabase.driver(URL, AuthTokens.basic(USER,PWD));
    }
    public static Session getSession(){
        return driver.session(SessionConfig.forDatabase(DB));
    }
}
