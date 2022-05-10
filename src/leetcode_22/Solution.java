package leetcode_22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(n, "", 0, ans);
        return ans;
    }

    private boolean dfs(int n, String path, int stack, List<String> ans) {
        if (n < 0) return false;
        else if (n == 0) {//n==0表示左括号已经全部添加完
            for (int i = 0; i < stack; i++) {
                path += ')';
            }
            ans.add(path);
            return true;
        }
        boolean flg = true;
        if (stack==0) {
            //如果栈为空则只能选择左括号
            flg = flg && dfs(n - 1, path+'(', stack+1, ans);
        } else {
            //如果栈不为空则左括号和右括号都能选择
            //选择左括号
            flg = flg && dfs(n - 1, path + '(', stack+1, ans);
            //选择右括号
            flg = flg && dfs(n, path + ')', stack-1, ans);
        }
        return flg;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> ans = solution.generateParenthesis(2);
        System.out.println(ans);
    }
}
