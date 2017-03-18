package com.geofeedia.crosscloudqueue.main;

import java.util.NoSuchElementException;

/**
 * Created by mchem on 3/16/2017.
 */
public class FifoQueue {
    // enqueue at back, dequeue from front
    private LLNode front, back; //pointers point to back of list

    //initialize front and back to null
    public FifoQueue() {
        front = null;
        back = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    //add to back
    public void enqueue(int v) {
        if (isEmpty()) {
            back = front = new LLNode(v);
        } else {
            back.next = new LLNode(v);
            back=back.next;
        }
    }

    //remove from front
    //throw an exception if its empty
    public int dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int value = front.value;
        if (front == back) // only 1 node; set pointers to null
        {
            front = back = null;
        } else {
            front=front.next;
        }
        return value;
    }

    //our linked list node; just has next and value fields
    private class LLNode {
        private LLNode next;
        private int value;

        public LLNode(int v) {
            value = v;
            next=null;
        }
    }
}