package problems;

import org.junit.jupiter.api.Test;

/**
 * Created by Victor on 2017/10/5.
 */
public class Josephus {
    public static int getResult1(int n, int m) {
        //递推方法
        if (n < 1 || m < 1) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }

        return (getResult1(n - 1, m) + m - 1) % n + 1;
    }

    public static int getResult2(int n, int m) {
        //迭代方法,从底往上算
        if (n < 1 || m < 1) {
            return -1;
        }
        int last = 1;
        for (int i = 2; i <= n; i++) {
            last = (last + m - 1) % i + 1;
        }
        return last;
    }

    @Test
    public void test() {
        assert 4 == getResult1(5, 3);
        assert 4 == getResult2(5, 3);
        assert 31 == getResult1(41, 3);
        assert 31 == getResult2(41, 3);
    }
}
