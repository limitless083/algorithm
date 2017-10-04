package list;

/**
 * Created by Victor on 2017/10/4.
 */
public class CircularlySinglyLinkedList {
    private Node rear; // 尾指针,rear.next即为头结点
    private int size;

    public CircularlySinglyLinkedList() {
        this.size = 0;
        this.rear = new Node();
        this.rear.next = rear;
    }

    public int getSize() {
        return this.size;
    }

    // 在末尾添加节点
    public void addNode(Node node) {
        node.next = rear.next;
        rear.next = node;
        rear = node;
        size++;
    }

    public void deleteNode(Node node) {
        Node p = node;
        while (p.next != node) {
            p = p.next;
        }
        // p是node前一个节点
        p.next = node.next;
        if (node == rear) {
            rear = p;
        }
        size--;
    }


    public void print() {
        Node p = rear.next.next;
        while (p != rear.next) {
            System.out.print(p.data + "  ");
            p = p.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        CircularlySinglyLinkedList circularlySinglyLinkedList = new CircularlySinglyLinkedList();
        Node node1 = new Node(1);
        circularlySinglyLinkedList.addNode(node1);
        circularlySinglyLinkedList.addNode(new Node(6));
        Node node2 = new Node(2);
        circularlySinglyLinkedList.addNode(node2);
        Node node4 = new Node(4);
        circularlySinglyLinkedList.addNode(node4);
        circularlySinglyLinkedList.print();

        circularlySinglyLinkedList.deleteNode(node2);
        circularlySinglyLinkedList.print();
        circularlySinglyLinkedList.deleteNode(node4);
        circularlySinglyLinkedList.print();
        circularlySinglyLinkedList.deleteNode(node1);
        circularlySinglyLinkedList.print();

        System.out.println(circularlySinglyLinkedList.getSize());
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
