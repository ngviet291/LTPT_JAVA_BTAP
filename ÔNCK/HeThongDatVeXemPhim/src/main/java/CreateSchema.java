/*
 * @ (#) main.java     1.0    5/10/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */


/*
 * @description
 * @author:NguyenTruong
 * @date:  5/10/2026
 * @version:    1.0
 */

import jakarta.persistence.Persistence;

public class CreateSchema {

    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("mariadb-pu").createEntityManager();
    }
}
