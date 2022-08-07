package leetcode_44;

import java.util.Arrays;

public class DP_Solution {
    public boolean isMatch(String s, String p) {
        //状态矩阵，dp[i][j]表示s的前i个字符与p的前j个字符是否匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //空字符串与空模式串匹配
        dp[0][0] = true;
        for (int i = 1; i < p.length() + 1; i++) {
            if (p.charAt(i - 1) == '*')
                dp[0][i] = true;
            else break;
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*')//算上*匹配或者不算*匹配
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        boolean[][] dp = new boolean[3][3];
        for (boolean[] booleans : dp) {
            System.out.println(Arrays.toString(booleans));
        }
//        System.out.println(Arrays.toString(dp));
    }
}
