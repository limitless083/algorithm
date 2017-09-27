package sort;

/**
 * Created by Victor on 2017/9/28.
 */
public class RadixSort {
    /*
    获取输入数组的最长位数
     */
    private static int maxbit(int[] input) {
        //获取最大值
        int max = input[0];
        for (int x : input) {
            if (x > max) {
                max = x;
            }
        }
        int d = 1;
        int p = 10;
        while (max >= p) {
            max /= p;
            d++;
        }
        return d;
    }

    private static int[] radixSort(int[] input) {
        int d = maxbit(input);
        int n = input.length;
        int[] temp = new int[n];
        // 比较d个位数
        int radix = 1;
        int[] c = new int[10]; // 每一位的范围是0-9
        for (int i = 0; i < d; i++) {
            //清零所有c
            for (int j = 0; j < 10; j++) {
                c[j] = 0;
            }

            // 每次循环对不同的位进行排序,使用CountingSort
            for (int j = 0; j < n; j++) {
                int k = (input[j] / radix) % 10;
                c[k] = c[k] + 1;
            }

            for (int j = 1; j < 10; j++) {
                c[j] = c[j-1] + c[j];
            }

            for (int j = n-1; j >= 0; j--) {
                int k = (input[j] / radix) % 10;
                temp[c[k] - 1] = input[j];
                c[k] = c[k] - 1;
            }

            // 注意这里拷贝数据，不能input = temp这样。
            System.arraycopy(temp, 0, input, 0, n);
            radix *= 10;
        }
        return temp;
    }

    private static void print(int[] a) {
        for (int x : a) {
            System.out.print(x + " ");
        }
    }

    public static void main(String[] args) {
        int[] out = radixSort(new int[]{13,2,31,231,52,21,36,711,27,403});
        print(out);
    }
}
