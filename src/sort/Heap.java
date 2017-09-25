package sort;

/**
 * Created by Victor on 2017/9/25.
 */
public class Heap {
    private int size = 0;
    private int capacity = 10;
    private int[] items = new int[capacity];

    private int getParentIndex(int index) {
        return (index - 1) >> 1;
    }

    private int getLeftChildIndex(int index) {
        return (index << 1) + 1;
    }

    private int getRightChildIndex(int index) {
        return (index << 1) + 2;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int parent(int index) {
        return items[getParentIndex(index)];
    }

    private int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    private int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    private void swap(int index1, int index2) {
        int tmp = items[index1];
        items[index1] = items[index2];
        items[index2] = tmp;
    }

    private void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + "  ");
        }
        System.out.println();
    }

    // 添加新节点
    private void add(int item) {
        items[size] = item;
        size++;
        heapifyUp(size - 1);
    }

    // 返回根节点
    public int peek() {
        return items[0];
    }

    // 删除根节点并返回根节点
    private int poll() {
        int item = items[0];
        items[0] = items[size - 1];
        size--;
        heapifyDown(0);
        return item;
    }

    // 上浮调整堆结构
    private void heapifyUp(int index) {
        while (hasParent(index) && items[index] > parent(index)) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    // 下沉调整堆结构
    private void heapifyDown(int index) {
        // 要和两个孩子节点大的那个交换才能保证堆的性质
        while (hasLeftChild(index)) {
            int biggerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) > leftChild(index)) {
                biggerChildIndex = getRightChildIndex(index);
            }
            if (items[index] > items[biggerChildIndex]) {
                // 如果两个孩子节点都不必本节点大，则不需要再往下比较了
                break;
            } else {
                swap(index, biggerChildIndex);
            }
            index = biggerChildIndex;
        }
    }


    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.add(10);
        heap.add(3);
        heap.add(2);
        heap.add(5);
        heap.add(4);
        heap.add(6);
        heap.add(8);
        heap.print();
        System.out.println(heap.peek());
        heap.print();
        System.out.println(heap.poll());
        heap.print();
    }
}
