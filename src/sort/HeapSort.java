package sort;

/**
 * Created by Victor on 2017/9/26.
 */
public class HeapSort {

    // This class should not be instantiated.
    private HeapSort() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(int[] a) {
        // 1. 构建最大堆：从右向左扫描，执行下沉重构最大堆(以堆大小大于于1的堆为起点开始构建)。
        // 直接以原数组开始构建，假如子节点已满足堆的性质，则不用再下沉。
        // 找每个父节点，执行下沉，比逐个add的方法要快。
        // 0 1 2 3 4 5 6 比如该满完全二叉树，应该从2开始构建。因此为n/2 - 1
        int n = a.length;
        for (int k = n/2 - 1; k >= 0; k--) {
            heapifyDown(a, k, n);
        }

        // 2. 交换数组末尾和最大堆的最大元素，使堆的大小逐渐减小到2（当堆大小为2时，最小的在1位置，次小的在0位置
        while (n >= 2) {
            exch(a, 0, --n);
            heapifyDown(a, 0, n);
        }
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private static void heapifyDown(int[] a, int k, int n) {
        // 要和两个孩子节点大的那个交换才能保证堆的性质
        while (hasLeftChild(k, n)) {
            int biggerChildIndex = getLeftChildIndex(k);
            if (hasRightChild(k, n) && rightChild(a, k) > leftChild(a, k)) {
                biggerChildIndex = getRightChildIndex(k);
            }
            if (a[k] > a[biggerChildIndex]) {
                // 如果两个孩子节点都不必本节点大，则不需要再往下比较了
                break;
            } else {
                exch(a, k, biggerChildIndex);
            }
            k = biggerChildIndex;
        }
    }

    private static int getLeftChildIndex(int index) {
        return (index << 1) + 1;
    }

    private static int getRightChildIndex(int index) {
        return (index << 1) + 2;
    }

    private static boolean hasLeftChild(int index, int size) {
        return getLeftChildIndex(index) < size;
    }

    private static boolean hasRightChild(int index, int size) {
        return getRightChildIndex(index) < size;
    }

    private static int leftChild(int[] a, int index) {
        return a[getLeftChildIndex(index)];
    }

    private static int rightChild(int[] a, int index) {
        return a[getRightChildIndex(index)];
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     ***************************************************************************/
    private static void exch(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // print array to standard output
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; heapsorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int[] a = new int[]{10,3,2,5,4,6,8};
        HeapSort.sort(a);
        HeapSort.show(a);
    }
}