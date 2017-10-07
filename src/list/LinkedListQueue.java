package list;

import java.util.NoSuchElementException;

/**
 * Created by Victor on 2017/10/7.
 */
public class LinkedListQueue {
    private Node front;
    private Node rear;

    LinkedListQueue() {
        Node node = new Node();
        front = node;
        rear = node;
    }

    public void enqueue(int data) {
        Node p = new Node(data);
        rear.next = p;
        rear = p;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node p = front.next;
        front.next = p.next;
        return p.data;
    }

    public boolean isEmpty() {
        return front.next == null;
    }

    public void print() {
        while (!isEmpty()) {
            System.out.print(dequeue() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListQueue linkedListQueue = new LinkedListQueue();
        linkedListQueue.enqueue(3);
        linkedListQueue.enqueue(5);
        linkedListQueue.enqueue(7);
        linkedListQueue.dequeue();
        linkedListQueue.dequeue();
        linkedListQueue.enqueue(9);
        linkedListQueue.enqueue(2);
        linkedListQueue.enqueue(6);
        linkedListQueue.enqueue(1);
        linkedListQueue.print();
    }

    static class Node {
        int data;
        Node next;

        public Node() {

        }

        public Node(int data) {
            this.data = data;
        }
    }
}
