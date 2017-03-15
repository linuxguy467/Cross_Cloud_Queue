package com.geofeedia.crosscloudqueue.main;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App {
    //final Logger logger = LoggerFactory.getLogger(App.class);

/*
    public static void main( String[] args ) {
        connectToCassandra("test","172.17.0.2", "172.17.0.3","172.17.0.4");
        //connectToRedis("127.0.0.1", 6379);
    }

    private static void connectToCassandra(String keyspace, String ...addresses) {
        try{
*/
/*            Cluster cluster = Cluster.builder().addContactPoints(addresses).withClusterName("Test Cluster").build();
            Session session = cluster.connect(keyspace);
            session.execute("INSERT INTO test(id, name, city) VALUES (38, 'Jose', 'Fort Myers')");*//*

Cluster cluster;
Session session;

cluster = Cluster.builder().addContactPoint("172.17.0.2").build();
session = cluster.connect(keyspace);
            System.out.println("Success");
        }catch(Exception e){
            System.out.printf("\"%s\"\n", e.getCause());
        }

    }
*/

    private static void connectToRedis(String hostname, Integer port) {
        try {
            Jedis jedis = new Jedis(hostname, port);
            jedis.auth("");
            jedis.connect();
            jedis.configSet("timeout", "30");
            System.out.println("Connected to Redis!");
        }catch (Exception e){
            System.err.printf("\"%s\"\n", e.getCause());
        }
    }
}
