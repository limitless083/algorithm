package list;

import java.util.EmptyStackException;

/**
 * Created by Victor on 2017/10/6.
 */
public class ArrayStack {
    private int top;
    private int[] stack;
    private int capacity;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        stack = new int[this.capacity];
        top = -1;
    }

    public void push(int data) {
        if (top < capacity - 1) {
            stack[++top] = data;
        } else {
            capacity = capacity * 2;
            int[] stack2 = new int[capacity];
            System.arraycopy(stack, 0, stack2, 0, stack.length);
            stack = stack2;
            stack[++top] = data;
        }
    }

    public int pop() {
        if (top == -1) {
            throw new EmptyStackException();
        } else {
            int data = stack[top];
            top--;
            return data;
        }
    }

    public int top() {
        if (top == -1) {
            throw new EmptyStackException();
        } else {
            return stack[top];
        }
    }

    public boolean isEmpty() {
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(8);
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
}
