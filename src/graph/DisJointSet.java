package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Victor on 2017/12/7.
 */
public class DisJointSet {
    private Map<Long, Node> map = new HashMap<>();

    class Node {
        long data;
        Node parent;
        int rank;
    }

    /*
    Create a set with only one element
     */
    private void makeSet(long data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    private void union(long data1, long data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        if (parent1.data != parent2.data) {
            if (parent1.rank >= parent2.rank) {
                parent1.rank = parent1.rank == parent2.rank ? parent1.rank + 1 : parent1.rank;
                parent2.parent = parent1;
            } else {
                parent1.parent = parent2;
            }
        }
    }

    private Node findSet(Node node) {
        if (node.parent == node) { // base case
            return node;
        } else {
            node.parent = findSet(node.parent);
            return node.parent;
        }
    }

    private long findSet(long data) {
        return findSet(map.get(data)).data;
    }

    public static void main(String[] args) {
        DisJointSet disJointSet = new DisJointSet();
        disJointSet.makeSet(1);
        disJointSet.makeSet(2);
        disJointSet.makeSet(3);
        disJointSet.makeSet(4);
        disJointSet.makeSet(5);
        disJointSet.makeSet(6);
        disJointSet.makeSet(7);

        disJointSet.union(1,2);
        disJointSet.union(2,3);
        disJointSet.union(4,5);
        disJointSet.union(6,7);
        disJointSet.union(5,6);
        disJointSet.union(3,7);

        System.out.println(disJointSet.findSet(1));
        System.out.println(disJointSet.findSet(2));
        System.out.println(disJointSet.findSet(3));
        System.out.println(disJointSet.findSet(4));
        System.out.println(disJointSet.findSet(5));
        System.out.println(disJointSet.findSet(6));
        System.out.println(disJointSet.findSet(7));

    }
}

