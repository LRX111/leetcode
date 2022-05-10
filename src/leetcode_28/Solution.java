package leetcode_28;

public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int haystackPointer = 0;
        while (haystackPointer < haystack.length()) {
            if (haystack.charAt(haystackPointer) == needle.charAt(0)) {
                int i = 0;
                for (; i < needle.length() && (i + haystackPointer) < haystack.length(); i++) {
                    if (haystack.charAt(i + haystackPointer) != needle.charAt(i))
                        break;
                }
                if (i == needle.length()) return haystackPointer;
                else haystackPointer++;
            } else {
                haystackPointer++;
            }
        }
        return -1;
    }

    public int kmp(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int[] next = getNextArr(needle);
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else if (next[j] == -1) i++;
            else j = next[j];
        }
        return j == needle.length() ? i - j : -1;
    }

    private int[] getNextArr(String needle) {
        if (needle.length() == 0) return null;
        int[] next = new int[needle.length()];
        next[0] = -1;
        if (needle.length() == 1) return next;
        next[1] = 0;
        if (needle.length() == 2) return next;
        int cn = 0;//needle[cn]与needle[i-1]比较
        int i = 2;
        while (i < needle.length()) {
            if (needle.charAt(i - 1) == needle.charAt(cn)) next[i++] = ++cn;
            else if (next[cn] == -1) next[i++] = 0;
            else cn = next[cn];
        }
        return next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.strStr("aaaaa", "ba"));
        System.out.println(solution.kmp("aababaab", "abaab"));
    }
}
