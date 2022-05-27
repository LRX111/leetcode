package leetcode_38;

public class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        if (n == 2) return "11";
        StringBuilder ans = new StringBuilder();
        String lastStr = countAndSay(n - 1);
        int count = 1;
        char lastChar = lastStr.charAt(0);
        for (int i = 1; i < lastStr.length(); i++) {
            if (lastStr.charAt(i) != lastChar) {
                ans.append(count);
                ans.append(lastChar);
                lastChar = lastStr.charAt(i);
                count = 1;
            } else count++;
        }
        ans.append(count);
        ans.append(lastChar);
        return ans.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String ans = solution.countAndSay(4);
        System.out.println(ans);
    }
}
