/**
 * @ (#) DemoList.java   1.0     20/1/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package com.ngviet291;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 20/1/2026
 */
public class DemoList {
//    static List<Integer>  list = new java.util.Vector<Integer>();
//    static List<Integer>  list = new java.util.ArrayList<Integer>();
    static List<Integer>  list = Collections.synchronizedList(new ArrayList<Integer>());
    public static void main(String[] args) {

        Random rd = new Random();
        Runnable task =()->{
            int n = rd.nextInt(10,100);
            synchronized (list) { //acquire lock on list object
            list.add(n);

            }
        };
        ExecutorService executor = java.util.concurrent.Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executor.submit(task);
         }
        executor.shutdown();
        while (!executor.isTerminated()) {;
        }
        System.out.println("List size: " + list.size());
    }
}
