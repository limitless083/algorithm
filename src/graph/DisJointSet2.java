package graph;

/**
 * Created by Victor on 2017/12/7.
 */
public class DisJointSet2 {
    private int[] id; //父链接索引，id[1]=2表示1的父节点为2
    private int[] rank; //每个set的大小，两个set大小相同union时加一
    private int count; // 连通分量的个数
    public DisJointSet2(int N) {
        id = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            rank[i] = 1;
        }
        count = N;
    }

    private boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        if (id[p] == p) {
            return p;
        } else {
            id[p] = find(id[p]);
            return id[p];
        }
    }

    private void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i != j) {
            // p 和 q不在一个集合里， 合并两个集合
            if (rank[i] >= rank[j]) {
                rank[i] = rank[i] == rank[j] ? rank[i] + 1 : rank[i];
                id[j] = i;
            } else {
                id[i] = j;
            }
            count--;
        }
    }

    private int count() {
        return count;
    }

    public static void main(String[] args) {
        DisJointSet2 disJointSet2 = new DisJointSet2(7);

        disJointSet2.union(0,1);
        disJointSet2.union(1,2);
        disJointSet2.union(3,4);
        disJointSet2.union(5,6);
        disJointSet2.union(4,5);
        disJointSet2.union(2,6);

        System.out.println(disJointSet2.find(0));
        System.out.println(disJointSet2.find(1));
        System.out.println(disJointSet2.find(2));
        System.out.println(disJointSet2.find(3));
        System.out.println(disJointSet2.find(4));
        System.out.println(disJointSet2.find(5));
        System.out.println(disJointSet2.find(6));

        System.out.println(disJointSet2.count());
    }
}
