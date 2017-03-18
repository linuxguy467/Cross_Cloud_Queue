package com.geofeedia.crosscloudqueue.main;

import redis.clients.jedis.Jedis;

/**
 *
 * Created by mchem on 3/16/2017.
 */
public class ConnectRedis {

    Jedis jedis;

    public void connect(String address) throws Exception{
        jedis = new Jedis(address);
        System.out.println("Connection successful.");
        System.out.println("Server ping: " + jedis.ping());
        //System.out.println("Server INFO: " + jedis.info());
    }

    public void push(String x, String... y) throws Exception{
        jedis.lpush(x, y);

    }

    public String pop(String x) {
        return jedis.lpop(x);
    }
}
