package search;

import java.util.LinkedList;

/**
 * Created by Victor on 2017/10/10.
 */
public class HashTable {
    private LinkedList[] buckets;
    private int maxPrime;

    HashTable(int capacity) {
        this.maxPrime = maxPrime(capacity);
        this.buckets = new LinkedList[maxPrime];
        for (int i = 0; i < maxPrime; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public int getBucketIndex(int data) {
        return data % maxPrime;
    }

    public void add(int data) {
        LinkedList bucket = buckets[getBucketIndex(data)];
        if (!bucket.contains(data)) {
            bucket.add(data);
        }
    }

    public int get(int data) {
        LinkedList bucket = buckets[getBucketIndex(data)];
        if (bucket.contains(data)) {
            return data;
        } else {
            return -1;
        }
    }

    public static int maxPrime(int num) {
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

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        hashTable.add(0);
        hashTable.add(109);
        hashTable.add(12);
        hashTable.add(7);
        System.out.println(hashTable.get(109));
        System.out.println(hashTable.get(3));
        System.out.println(hashTable.get(7));
    }

}
