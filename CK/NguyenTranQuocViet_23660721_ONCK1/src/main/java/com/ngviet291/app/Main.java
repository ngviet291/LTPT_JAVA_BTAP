package com.ngviet291.app;

import jakarta.persistence.Persistence;

public class Main {
    static void main() {
        Persistence.createEntityManagerFactory("MariaDB").createEntityManager();

    }

}
