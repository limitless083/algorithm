package list;

/**
 * Created by Victor on 2017/10/6.
 */
public class StaticLinkedList {
    private static final int MAXSIZE = 512;
    private Node[] array;
    private int size;
    public StaticLinkedList() {
        array = new Node[MAXSIZE];
        for (int i = 0; i < MAXSIZE - 1; i++) {
            array[i] = new Node();
            array[i].cur = i + 1;
        }
        array[MAXSIZE - 1] = new Node();
        array[MAXSIZE - 1].cur = 0;
        size = 0;
    }

    public void add(int data) {
        int i = MAXSIZE - 1;
        while (array[i].cur != 0) {
            i = array[i].cur; //找到链表的末尾
        }

        int index = array[0].cur;
        array[0].cur = array[index].cur;

        array[i].cur = index;
        array[index].data = data;
        array[index].cur = 0;
        size++;
    }

    public void insert(int index, int data) {
        int j = 0;
        int p = MAXSIZE - 1;
        while (p != 0 && j < index - 1) {
            p = array[p].cur;
            j++;
        }
        if (p == 0) {
            System.out.println("index error");
            return;
        }

        int space = array[0].cur;
        array[0].cur = array[space].cur;

        array[space].cur = array[p].cur;
        array[p].cur = space;
        array[space].data = data;
        size++;
    }

    public boolean delete(int data) {
        int p = MAXSIZE - 1;
        while (array[p].cur != 0) {
            int q = array[p].cur;
            if (array[q].data == data) {
                array[p].cur = array[q].cur;
                array[q].cur = array[0].cur;
                array[0].cur = q;
                size--;
                return true;
            }
            p = array[p].cur;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public void print() {
        int p = MAXSIZE - 1;
        while (array[p].cur != 0) {
            System.out.print(array[array[p].cur].data + " ");
            p = array[p].cur;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        StaticLinkedList staticLinkedList = new StaticLinkedList();
        staticLinkedList.add(5);
        staticLinkedList.add(3);
        staticLinkedList.add(9);
        staticLinkedList.add(7);
        staticLinkedList.add(1);
        staticLinkedList.print();
        staticLinkedList.delete(3);
        staticLinkedList.insert(1,6);
        staticLinkedList.insert(3,4);
        staticLinkedList.print();
        System.out.println(staticLinkedList.getSize());
    }

    private static class Node {
        int data;
        int cur;

        Node() {}

        Node(int data) {
            this.data = data;
        }
    }
}
