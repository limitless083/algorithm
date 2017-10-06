package list;

import java.util.EmptyStackException;

/**
 * Created by Victor on 2017/10/6.
 */
public class LinkedListStack {
    private Node front;

    public LinkedListStack() {
        front = new Node();
    }

    public void push(int data) {
        Node p = new Node(data);
        p.next = front.next;
        front.next = p;
    }

    public int pop() {
        Node p = front.next;
        if (p != null) {
            front.next = p.next;
            return p.data;
        } else {
            throw new EmptyStackException();
        }
    }

    public int top() {
        Node p = front.next;
        if (p != null) {
            return p.data;
        } else {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty() {
        return front.next == null;
    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        stack.push(3);
        stack.push(5);
        stack.push(7);
        stack.push(6);
        stack.push(7);
        stack.push(9);
        System.out.println(stack.top());

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }


    private static class Node {
        int data;
        Node next;

        public Node() {

        }

        public Node(int data) {
            this.data = data;
        }
    }
}
