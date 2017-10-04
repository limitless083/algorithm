package list;

/**
 * Created by Victor on 2017/10/4.
 */
public class DoublyLinkedList {
    private Node front;
    private int size;
    public DoublyLinkedList() {
        this.size = 0;
        this.front = new Node();
    }

    public void addNode(Node node) {
        Node p = front;
        while (p.next != null) {
            p = p.next;
        }
        p.next = node;
        node.prev = p;
        size++;
    }

    public void deleteNode(Node node) {
        if (node != null) {
            node.prev.next = node.next;
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            size--;
        }
    }

    public boolean insertByIndex(int index, Node node) {
        int j = 0;
        Node p = front;
        while (p != null && j < index - 1) {
            p = p.next;
            j++;
        }
        if (p != null) {
            node.next = p.next;
            p.next = node;
            node.prev = p;
            if (node.next != null) {
                node.next.prev = node;
            }
            size++;
            return true;
        } else {
            return false;
        }
    }

    public int length() {
        return size;
    }

    public void print() {
        Node p = front.next;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public void reversePrint() {
        Node p = front;
        while (p.next != null) {
            p = p.next;
        }
        while (p.prev != null) {
            System.out.print(p.data + " ");
            p = p.prev;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addNode(new Node(3));
        Node node = new Node(5);
        doublyLinkedList.addNode(node);
        doublyLinkedList.addNode(new Node(7));
        doublyLinkedList.addNode(new Node(9));
        doublyLinkedList.print();
        doublyLinkedList.deleteNode(node);
        doublyLinkedList.print();
        doublyLinkedList.insertByIndex(2, new Node(1));
        doublyLinkedList.print();
        doublyLinkedList.reversePrint();
        System.out.println(doublyLinkedList.length());
    }

    private static class Node {
        int data;
        Node prev;
        Node next;
        Node() {

        }

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
