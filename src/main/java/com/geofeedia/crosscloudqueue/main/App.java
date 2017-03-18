package com.geofeedia.crosscloudqueue.main;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import static java.util.Arrays.asList;

/**
 * Hello world!
 *
 */
public class App {

    Integer one = 3;
    String two = "Bob";

    public Boolean connect(int status, String address) throws Exception {
        if(status == 0){
            return true;
        }else{
            Cluster cluster = Cluster.builder().addContactPoints(address).withClusterName("Test Cluster").build();
            Session session = cluster.connect("test");
        }

        return false;
    }

    public Boolean isOnline(int status){
        Boolean online = false;

        if (status == 0){
            online = true;
        }

        return online;
    }

    public Object[] check(Object... args){
        if(asList(args).contains(one) && asList(args).contains(two)){
            System.out.printf("\nObjects %s and %s are in database", one, two);
            return args;
        }else {
            System.out.println("\nObjects are not in database");
            return null;
        }
    }

    /*public static Object[] format(Object... objs){
        Integer x = 3, y = 4, z = 5;

        return
    }*/
}
