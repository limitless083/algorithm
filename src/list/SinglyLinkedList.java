package list;

/**
 * Created by Victor on 2017/10/4.
 */
public class SinglyLinkedList {
    private Node front; // 头结点，数据域无意义
    private int size;

    public SinglyLinkedList() {
        this.size = 0;
        this.front = new Node();
    }

    public int getSize() {
        return this.size;
    }

    // 在末尾添加数据为data的节点
    public void addData(int data) {
        Node node = new Node(data);
        addNode(node);
    }

    // 在末尾添加节点
    public void addNode(Node node) {
        Node p = front;
        while (p.getNext() != null) {
            p = p.getNext();
        }
        p.setNext(node);
        size++;
    }

    // 返回数据对应节点的前驱节点
    public Node getPrevNodeByData(int data) {
        Node prev = front;
        Node p = prev.getNext();
        while (p != null) {
            if (p.getData() == data) {
                return prev;
            }
            prev = prev.getNext();
            p = p.getNext();
        }
        return null;
    }

    // 删除data对应的节点，如果有多个则只删除第一个
    public void deleteByData(int data) {
        Node prev = getPrevNodeByData(data);
        if (prev != null) {
            prev.setNext(prev.getNext().getNext());
            size--;
        } else {
            System.out.println("there is no data:" + data);
        }
    }

    // 在index位置处插入node
    public boolean insertNode(int index, int data) {
        Node node = new Node(data);
        int j = 0;
        Node p = front;
        while (p != null && j < index - 1) {
            j++;
            p = p.getNext();
        }
        if (p != null) {
            node.setNext(p.getNext());
            p.setNext(node);
            size++;
            return true;
        } else {
            System.out.println("index error!");
            return false;
        }
    }

    public boolean isCyclic() {
        Node tortoise = front;
        Node hare = front;

        while (hare != null && hare.getNext() != null) {
            tortoise = tortoise.getNext();
            hare = hare.getNext().getNext();
            if (tortoise == hare) {
                return true;
            }
        }
        return false;
    }

    public boolean isCyclic2() {
        Node tortoise = front;
        Node hare = front;
        int taken = 0;
        int limit = 2;
        while (hare != null) {
            hare = hare.getNext();
            taken++;
            if (hare == tortoise) {
                return true;
            }
            if (taken == limit) {
                taken = 0;
                limit <<= 1;
                tortoise = hare;
            }
        }
        return false;
    }

    public void reverse() {
        if (front.getNext() == null) {
            return;
        }

        Node prev = null;
        Node curr = front.getNext();
        Node next;

        while (curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }

        front.setNext(prev);
    }

    public void reverse(Node node) {
        if (node == null || node.getNext() == null) {
            front.setNext(node);
            return;
        }

        reverse(node.getNext());

        node.getNext().setNext(node);
        node.setNext(null);
    }

    public Node getHeadNode() {
        return front;
    }

    public void print() {
        Node p = front.getNext();
        while (p != null) {
            System.out.print(p.getData() + "  ");
            p = p.getNext();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.addData(3);
        singlyLinkedList.addData(4);
        singlyLinkedList.addData(7);
        singlyLinkedList.addData(6);
        singlyLinkedList.addData(2);
        singlyLinkedList.print();

        singlyLinkedList.deleteByData(4);
        singlyLinkedList.deleteByData(3);
        System.out.println(singlyLinkedList.getSize());
        singlyLinkedList.print();

        singlyLinkedList.insertNode(1, 8);
        singlyLinkedList.insertNode(1, 1);
        singlyLinkedList.print();
        singlyLinkedList.reverse(singlyLinkedList.getHeadNode().getNext());
        singlyLinkedList.print();
        System.out.println(singlyLinkedList.getSize());

        Node cycle = new Node(3);
        singlyLinkedList.addNode(cycle);
        singlyLinkedList.addData(9);
        System.out.println("is cycle:" + singlyLinkedList.isCyclic2());

        singlyLinkedList.addNode(cycle);
        System.out.println("is cycle:" + singlyLinkedList.isCyclic2());
    }


    private static class Node {
        private int data;
        private Node next;
        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getData() {
            return this.data;
        }

        public Node getNext() {
            return this.next;
        }
    }


}
