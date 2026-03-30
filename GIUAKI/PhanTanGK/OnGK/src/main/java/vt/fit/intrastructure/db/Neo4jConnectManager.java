/*
 * @ (#) Neo4jConnectManager.java     1.0    3/8/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package vt.fit.intrastructure.db;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/8/2026
 * @version:    1.0
 */

import org.neo4j.driver.*;


public class Neo4jConnectManager implements AutoCloseable {
    private String dbName;
    private Driver driver;
public Neo4jConnectManager(String dbName,String userName,String password,String url){
        this.driver= GraphDatabase.driver(url, AuthTokens.basic(userName,password));
        this.dbName=dbName;
}
    public Session getSession(){
    return driver.session(SessionConfig.forDatabase(dbName));
    }

    @Override
    public void close() throws Exception {

    }
}
