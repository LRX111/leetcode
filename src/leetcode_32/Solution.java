package leetcode_32;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {
    public int longestValidParentheses(String s) {
        int ans = 0, start = 0, couples = 0;
        List<Integer> stack = new LinkedList<>();//存储左括号的索引
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') ((LinkedList<Integer>) stack).push(i);
            else {
                if (!stack.isEmpty()) {
                    ((LinkedList<Integer>) stack).pop();
                    couples++;
                } else {
                    //当遇到右括号而没有左括号相匹配时
                    //记录此时的最长有效括号对长度
                    ans = couples * 2 > ans ? couples * 2 : ans;
                    couples = 0;
                    start = i + 1;
                }
            }
        }
        if (!stack.isEmpty()) {
            //如果左括号栈不为空。说明则有效括号对数被多余的左括号划分
            //此时把从计数开始的字符串截取出来，然后从右往左扫一次
            if (start < s.length()) {
                String cutstr = s.substring(start, s.length());
                int rightCouples = 0;
                List<Integer> rightStack = new LinkedList<>();
                for (int i = cutstr.length() - 1; i >= 0; i--) {
                    if (cutstr.charAt(i) == ')') ((LinkedList<Integer>) rightStack).push(i);
                    else {
                        if (!rightStack.isEmpty()) {
                            ((LinkedList<Integer>) rightStack).pop();
                            rightCouples++;
                        } else {
                            ans = rightCouples * 2 > ans ? rightCouples * 2 : ans;
                            rightCouples = 0;
                        }
                    }
                }
                if (rightStack.isEmpty()) ans = rightCouples * 2 > ans ? rightCouples * 2 : ans;
            }
        } else {
            //如果左括号栈为空，说明右括号多余或正好
            ans = couples * 2 > ans ? couples * 2 : ans;
        }
        return ans;
    }

    public int dp_longestValidParentheses(String s) {
        int ans = 0;
        if (s.length() == 0) return 0;
        //dp[i]表示以dp[i]结尾的最长有效子串长度
        int[] dp = new int[s.length()];
        //dp[0]一定为0
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    if (i - dp[i - 1] - 2 >= 0) dp[i] = 2 + dp[i - 1] + dp[i - dp[i - 1] - 2];
                    else dp[i] = 2 + dp[i - 1];
                }
            }
            ans = dp[i] > ans ? dp[i] : ans;
        }
        return ans;
    }
}
