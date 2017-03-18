package com.geofeedia;

import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.geofeedia.crosscloudqueue.main.App;
import com.geofeedia.crosscloudqueue.main.ConnectRedis;
import com.geofeedia.crosscloudqueue.main.FifoQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private App app;
    private FifoQueue queue;
    private ConnectRedis redis;
    private int status;

    @Before
    public void setUp() throws Exception{
        app = new App();
        queue = new FifoQueue();
        redis = new ConnectRedis();
        status = 0;
    }

    @After
    public void tearDown() throws Exception{
        app = null;
        queue = null;
        redis = null;
    }

    @Test
    public void testConnect() throws Exception{
        assertTrue(app.connect(status, "localhost"));
    }

    @Test(expected = NoHostAvailableException.class)
    public void testConnectLocal() throws Exception{
        assertTrue(app.connect( 1, "172.17.0.1"));
    }

    @Test
    public void testConnectRedis() throws Exception{
        redis.connect("localhost");
    }

    @Test(expected = Exception.class)
    public void testConnectRedisFail() throws Exception{
        redis.connect("testaddress");
    }

    @Test
    public void testPush() throws Exception{
        redis.connect("localhost");
        redis.push("pie", "cake");
        assertEquals("cake", redis.pop("pie"));
    }

    @Test(expected = Exception.class)
    public void testConnectException() throws Exception{
        assertTrue(app.connect(1, "172.17.0.1"));
    }

    @Test
    public void testCheck() throws Exception{
        Integer one = 3;
        String two = "Bob";
        assertArrayEquals(new Object[]{3, "Bob"}, app.check(one, two));
        assertNull(app.check("345", "Matt"));
    }

    @Test
    public void testIsOnline() throws Exception{
        assertTrue(app.isOnline(status));
        assertFalse(app.isOnline(-1));
    }

    @Test
    public void testIsEmptyOnEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmptyOnNotEmpty() {
        queue.enqueue(5);
        assertTrue(!queue.isEmpty());
    }

    @Test
    public void testOneEnqueue() {
        queue.enqueue(5);
    }

    @Test
    public void testOneDequeue() {
        queue.enqueue(5);
        assertTrue(5==queue.dequeue());
    }

    @Test
    public void testOrdering() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1,queue.dequeue());
        assertEquals(2,queue.dequeue());
        assertEquals(3,queue.dequeue());
    }


    @Test(expected=NoSuchElementException.class)
    public void testDequeueOnEmpty() {
        queue.dequeue();
    }

    @Test
    //this checks to make sure that enqueueing then dequeueing doesn't break isEmpty()
    public void testEmptyAfterDequeue() {
        queue.enqueue(5);
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Ignore
    //you can use the '@Ignore' annotation to effectively hide a test
    public void ignoreMe()
    {
        throw new RuntimeException("Error");
    }
}
