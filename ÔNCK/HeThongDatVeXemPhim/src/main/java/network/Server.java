/*
 * @ (#) Server.java     1.0    5/11/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package network;


/*
 * @description
 * @author:NguyenTruong
 * @date:  5/11/2026
 * @version:    1.0
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ServerSocket server = null;
        try {
            server = new ServerSocket(1133);
            System.out.println("Van truong dep trai");
            while (true) {
                pool.submit(new ClientHandle(server.accept()));
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.close();
        }
    }
}
