package leetcode_50;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public double myPow(double x, int n) {
        double ans = 1;
        if (n == 0) return ans;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                ans *= x;
            }
        } else {
            for (int i = 0; i < -n; i++) {
                ans /= x;
            }
        }
        return ans;
    }


    public double myBDPow(double x, int n) {
        double ans = 1;
        if (n == 0) return ans;
        if (n == 1) return x;
        if (n == -1) return 1 / x;
        ans = myPow(x, n / 2);
        if ((n & 1) == 0) ans *= ans;
        else ans *= myPow(x, n - n / 2);
        return ans;
    }

    public double myDDPow(double x, int n) {
        double ans = 1;
        double mod = x;
        if (n == 0) return 1;
        boolean flg = true;
        if (n < 0) {
            flg = false;
            n = -(n + 1);
        }
        int i = 1;
        int count=0;
        double[] dp = new double[31];
        dp[0] = 1;
        while (i <= n) {
            while (i <= n / 2) {
                mod *= mod;
                i *= 2; //此时mod=x^i
                dp[++count] = mod;
            }
            //此时i①等于n；②刚好小于n
            if (i <= n) {
                n = n - i;
                ans *= mod;
//                mod = x;
//                i = 1;
            }
            while (i > n) {
                i /= 2;
                mod = dp[--count];
            }
        }
        return flg ? ans : 1.0 / (ans * x);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        double x = 2.00000;
        int n = -2147483648;
        System.out.println(solution.myPow(x, n));
        System.out.println(solution.myBDPow(x, n));
        System.out.println(solution.myDDPow(x, n));
    }
}
