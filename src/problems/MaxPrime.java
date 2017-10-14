package problems;

import org.junit.jupiter.api.Test;

import java.util.BitSet;

/**
 * Created by Victor on 2017/10/14.
 */
public class MaxPrime {

    /*
    埃拉托斯特尼筛法：时间复杂度O(nloglogn).
     */
    public static int maxPrime1(int num) {
        if (num <= 1) {
            throw new IllegalArgumentException();
        } else {
            boolean[] isPrime = new boolean[num + 1];
            for (int i = 2; i <= num; i++) {
                isPrime[i] = true;
            }
            for (int i = 2; i * i <= num; i++) {
                if (isPrime[i]) {
                    for (int j = i * i; j <= num; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
            int max = 0;
            for (int i = num; i >= 2; i--) {
                if (isPrime[i]) {
                    max = i;
                    break;
                }
            }
            return max;
        }
    }

    /*
    欧拉筛法：时间复杂度O(n)，是对埃拉托斯特尼筛法的改进
     */
    public static int maxPrime2(int num) {
        if (num <= 1) {
            throw new IllegalArgumentException();
        } else {
            boolean[] isPrime = new boolean[num + 1];
            int[] primes = new int[num];
            int cnt = 0;
            for (int i = 2; i <= num; i++) {
                isPrime[i] = true;
            }
            for (int i = 2; i <= num; i++) {
                if (isPrime[i]) {
                    primes[cnt++] = i;
                }

                for (int j = 0; j < cnt && i * primes[j] <= num; j++) {
                    isPrime[i * primes[j]] = false;
                    if (i % primes[j] == 0) {
                        break;
                    }
                }
            }
            int max = 2;
            for (int i = num; i >= 2; i--) {
                if (isPrime[i]) {
                    max = i;
                    break;
                }
            }
            return max;
        }
    }

    /*
   欧拉筛法：时间复杂度O(n)，只针对奇数遍历，maxPrime2的改进
    */
    public static int maxPrime3(int num) {
        if (num <= 1) {
            throw new IllegalArgumentException();
        } else {
            int N = num / 2 + 1;
            boolean[] isPrime = new boolean[N];
            int[] primes = new int[N];
            int cnt = 0;
            for (int i = 1; (i << 1) + 1 <= num; i++) {
                isPrime[i] = true;
            }
            for (int i = 1, t = (i << 1) + 1; t <= num; i++, t = (i << 1) + 1) {
                if (isPrime[i]) {
                    primes[cnt++] = t;
                }

                for (int j = 0; j < cnt && t * primes[j] <= num; j++) {
                    isPrime[(t * primes[j] - 1) >> 1] = false;
                    if (t % primes[j] == 0) {
                        break;
                    }
                }
            }
            int max = 2;
            for (int i = N - 1; i >= 1; i--) {
                if (isPrime[i]) {
                    max = (i << 1) + 1;
                    break;
                }
            }
            return max;
        }
    }

    /*
    欧拉筛法：时间复杂度O(n)，只针对奇数遍历，且使用BitSet。是对maxPrime3的改进
   */
    public static int maxPrime4(int num) {
        if (num <= 1) {
            throw new IllegalArgumentException();
        } else {
            int N = num / 2 + 1;
            BitSet isPrime = new BitSet(N);
            int[] primes = new int[N];
            int cnt = 0;
            for (int i = 1; (i << 1) + 1 <= num; i++) {
                isPrime.set(i, true);
            }
            for (int i = 1, t = (i << 1) + 1; t <= num; i++, t = (i << 1) + 1) {
                if (isPrime.get(i)) {
                    primes[cnt++] = t;
                }

                for (int j = 0; j < cnt && t * primes[j] <= num; j++) {
                    isPrime.set((t * primes[j] - 1) >> 1, false);
                    if (t % primes[j] == 0) {
                        break;
                    }
                }
            }
            int max = 2;
            for (int i = N - 1; i >= 1; i--) {
                if (isPrime.get(i)) {
                    max = (i << 1) + 1;
                    break;
                }
            }
            return max;
        }
    }

    @Test
    public void test() {
        assert 2 == maxPrime1(2);
        assert 2 == maxPrime2(2);
        assert 2 == maxPrime3(2);
        assert 2 == maxPrime4(2);
        assert 13 == maxPrime1(15);
        assert 13 == maxPrime2(15);
        assert 13 == maxPrime3(15);
        assert 13 == maxPrime4(15);
        assert 199999 == maxPrime1(200000);
        assert 199999 == maxPrime2(200000);
        assert 199999 == maxPrime3(200000);
        assert 199999 == maxPrime4(200000);
    }
}
