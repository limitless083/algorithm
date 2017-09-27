package sort;

/**
 * Created by Victor on 2017/9/27.
 */
public class CountingSort {
    private static int[] countingSort(int[] input) {
        int n = input.length;
        int[] output = new int[n];

        // 1. 找到数组的最大最小值。
        int max = input[0];
        int min = input[0];
        for (int x : input) {
            if (max < x) {
                max = x;
            }
            if (min > x) {
                min = x;
            }
        }
        int k = max - min + 1;
        // c[0] -> min c[k-1] -> max, 比如min为3，max为7，则数的范围为3-7，共5种数，因此k=max-min+1.
        int[] c = new int[k];
        // 2. 统计input数组中每个元素i出现的个数
        for (int i = 0; i < n; i++) {
            c[input[i] - min] = c[input[i] - min] + 1;
        }

        // 3. 对所有的计数累加，表示的是小于等于当前数字的数字的个数
        for (int i = 1; i < k; i++) {
            c[i] = c[i-1] + c[i];
        }

        // 4. 通过原来的输入数组，反向填充目标数组
        for (int i = n - 1; i >= 0; i--) {
            output[c[input[i] - min] - 1] = input[i];
            c[input[i] - min]--;
        }
        return output;
    }

    private static void print(int[] a) {
        for (int x : a) {
            System.out.print(x + " ");
        }
    }
    public static void main(String[] args) {
        int[] out = countingSort(new int[]{3,2,3,3,5,2,3,7,2,4});
        print(out);
    }
}
