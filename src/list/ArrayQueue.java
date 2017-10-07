package list;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

/**
 * Created by Victor on 2017/10/6.
 */
public class ArrayQueue {
    private int front;
    private int rear;
    private int capacity;
    private int[] items;
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        items = new int[capacity];
        front = 0;
        rear = 0;
    }

    public void enqueue(int data) {
        ensureCapacity();
        items[rear = ((rear + 1) % items.length)] = data;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[front = (front + 1) % items.length];
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[(front + 1) % items.length];
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void ensureCapacity() {
        if ((rear + 1) % items.length == front) {
            // 如果队列满了，就扩容两倍
            int[] items2 = new int[(capacity <<= 1)];
            if (front < rear) {
                System.arraycopy(items, front, items2, 0, items.length - front);
            } else {
                System.arraycopy(items, front, items2, 0, items.length - front);
                System.arraycopy(items, 0, items2, items.length - front, rear + 1);
            }
            front = 0;
            rear = items.length - 1;
            items = items2;
        }
    }

    public void print() {
        while (!isEmpty()) {
            System.out.print(dequeue() + " ");
        }
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(4);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(5);
        arrayQueue.enqueue(7);
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.enqueue(9);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(1);
        arrayQueue.print();
    }


}
