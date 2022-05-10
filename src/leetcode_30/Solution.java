package leetcode_30;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new LinkedList<>();
        List<String> subStrList=new ArrayList<>();

        return ans;
    }

    private int kmp(String s, String substr) {
        int[] next = getNext(substr);
        if (next == null) return -1;
        int s_p = 0;
        int sub_p = 0;
        while (s_p < s.length() && sub_p < substr.length()) {
            if (s.charAt(s_p) == substr.charAt(sub_p)) {
                s_p++;
                sub_p++;
            } else if (sub_p == 0) s_p++;
            else {
                sub_p = next[sub_p];
            }
        }
        return sub_p == substr.length() ? s_p - sub_p : -1;
    }

    private int[] getNext(String substr) {
        if (substr.length() == 0) return null;
        int[] next = new int[substr.length()];
        next[0] = -1;
        if (substr.length() == 1) return next;
        next[1] = 0;
        if (substr.length() == 2) return next;

        int cn = 0;//初始代表next[1] = 0中的0;
        for (int i = 2; i < substr.length(); ) {
            if (substr.charAt(i - 1) == substr.charAt(cn)) next[i++] = ++cn;
            else if (cn == 0) next[i++] = 0;
            else cn = next[cn];
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo","bar"};
        Solution solution = new Solution();
        System.out.println(solution.findSubstring(s, words));
    }
}
