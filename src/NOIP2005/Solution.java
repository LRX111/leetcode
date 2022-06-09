package NOIP2005;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    public static int mySol(int n, int k) {
        int modd = (int) Math.pow(10, k);
        int res = n % modd;
        int nPow = (n * n) % modd;
        int count = 1;
        while (nPow != res) {
            int new_nPow = (nPow * n) % modd;
            if (new_nPow != nPow)
                nPow = new_nPow;
            else return -1;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            System.out.println(mySol(n, k));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
