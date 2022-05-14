package leetcode_32;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int longestValidParentheses(String s) {
        int ans = 0, couples = 0;
        List<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                ((LinkedList<Character>) stack).push('(');
            } else {
                if (stack.isEmpty()) {
                    //如果stack为空，证明当前右括号无'('匹配
                    //此时，从上一次stack为空时到此时为空之间的couples数为一次完整的合法括号对数
                    if (couples * 2 > ans) ans = couples * 2;
                    couples = 0;
                } else {
                    //如果stack不为空，弹出一个'('与当前右括号匹配
                    ((LinkedList<Character>) stack).pop();
                    couples++;
                }
            }
        }
        if (stack.isEmpty()) {
            //循环结束后，如果stack为空，此时最后一次开始计数的couples数与ans比较
            if (couples * 2 > ans) ans = couples * 2;
        } else {
            //循环结束后，如果stack不为空，此时多出来的左括号在哪个位置决定了有几对合法couples
            int rightCouples = 0;
            List<Character> rightStack = new LinkedList<>();
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == ')') ((LinkedList<Character>) rightStack).push(')');
                else {
                    if (rightStack.isEmpty()) {
                        ans = rightCouples * 2 > ans ? rightCouples * 2 : ans;
                        rightCouples = 0;
                    } else {
                        ((LinkedList<Character>) rightStack).pop();
                        rightCouples++;
                    }
                }
            }
            if (rightStack.isEmpty()) {
                ans = rightCouples * 2 > ans ? rightCouples * 2 : ans;
            } else {
                ans = Math.min(couples, rightCouples) * 2 > ans ? Math.min(couples, rightCouples) * 2 : ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "))))())()()(()";
        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses(str));
    }
}
