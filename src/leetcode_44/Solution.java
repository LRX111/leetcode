package leetcode_44;

public class Solution {
    public boolean isMatch(String s, String p) {
        //先倒着检查一下是否匹配直到遇到‘*’,简化计算量
        int pIndex = p.length() - 1;
        int sIndex = s.length() - 1;
        for (; pIndex >= 0 && sIndex >= 0; pIndex--, sIndex--) {
            if (p.charAt(pIndex) == s.charAt(sIndex) || p.charAt(pIndex) == '?') continue;
            else if (p.charAt(pIndex) == '*') break;
            else return false;
        }
        return tryMatch(s, p, 0, 0);
    }

    private boolean tryMatch(String s, String p, int sIndex, int pIndex) {
        if (sIndex == s.length() && pIndex == p.length()) return true;
        else if (sIndex == s.length() && pIndex != p.length()) {
            //此时sIndex已经指向s末尾，不可能有任何字符能拿来与p[pIndex]及后面的字符匹配
            //此时唯一能返回true的情况是pIndex之后只有'*'。
            for (int i = pIndex; i < p.length(); i++) {
                if (p.charAt(i) != '*') return false;
            }
            return true;
        } else if (pIndex == p.length() && sIndex != s.length()) {
            //此时pIndex指向p末尾，不可能有任何字符能拿来与s[sIndex]及后面的字符匹配
            //此时若pIndex-1为'*'，表示sIndex及其后所有都匹配，返回true；否则返回false
            if (pIndex - 1 >= 0 && p.charAt(pIndex - 1) == '*') return true;
            return false;
        } else {
            //此时sIndex和pIndex没有到达末尾
            //如果当前字符串匹配，则sIndex与pIndex都向后移动一位
            //如果当前字符串不匹配，
            //  ①p[pIndex]处字符不是'*'，则立即判断不匹配
            //  ②p[pIndex]处字符是'*'，则尝试从pIndex+1开始与sIndex开始匹配，若有完全匹配的尝试则返回true，
            // 否则不断向后移动sIndex,若最终都没有成功的尝试匹配则返回false
            if (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?')
                return tryMatch(s, p, sIndex + 1, pIndex + 1);
            else {
                if (p.charAt(pIndex) != '*') return false;
                else {
                    for (int i = sIndex; i < s.length(); i++) {
                        if (tryMatch(s, p, i, pIndex + 1) == true) return true;
                    }
                    return false;
                }
            }
        }
    }

}
