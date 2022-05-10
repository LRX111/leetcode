package leetcode_5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    private String findStringByCurrent(String s, int maxRange, int current) {
        int rightYC = 1;
        int sidesCur = 1;
        while (rightYC <= maxRange) {
            if (s.charAt(current) == s.charAt(current + rightYC)) {
                rightYC++;
            } else {
                break;
            }
        }
        if (rightYC > maxRange) {
            //rightYC因while结束而终止
            //以s[current]为轴的最大回文子串为连续相同的字符s[current]至s[current+maxRange]
            if (rightYC <= s.length() - current - 1) {
                //如果rightYC仅超过左边界
                while (rightYC <= (s.length() - current - 1)) {
                    //继续寻找与s[current]相同的字符
                    if (s.charAt(current) == s.charAt(current + rightYC)) {
                        rightYC++;
                    } else {
                        break;
                    }
                }
                if (rightYC > (s.length() - current - 1))
                    //rightYC因while结束而终止
                    //current直至末尾处字符都相等
                    return s.substring(current, s.length());
                else {
                    //rightYC因break而终止
                    //s[current]至s[current + rightYC-1]所有字符相同
                    //rightYC大于左边界且current + rightYC不越右边界
                    while (sidesCur <= Math.min(maxRange, s.length() - 1 - (current + rightYC - 1))) {
                        //寻找中间块俩侧相等字符的最大长度
                        //sidesCur应在中间块的左侧范围内和右侧范围内
                        if (s.charAt(current - sidesCur) == s.charAt(current + rightYC - 1 + sidesCur)) {
                            sidesCur++;
                        } else {
                            //s[current-(sidesCur-1)]到s[current+rightYC-1+(sidesCur-1)]为当前最大回文串
                            return s.substring(current - (sidesCur - 1), current + rightYC - 1 + (sidesCur - 1) + 1);
                        }
                    }
                    //s[current-(sidesCur-1)]至s[current+rightYC-1+(sidesCur-1)]为当前最大回文串
                    return s.substring(current - (sidesCur - 1), current + rightYC - 1 + (sidesCur - 1) + 1);
                }
            } else {
                //rightYC因while结束而终止
                //rightYC不超过左边界
                //rightYC超过右边界
                //s[current]至s[current+rightYC-1]所有字符相同
                //不存在中间块两端
                return s.substring(current, current + rightYC);
            }
        } else {
            //rightYC因break而终止（即rightYC小于中间块的左侧范围并且（current + rightYC）不越界）
            //s[current]至s[current+rightYC-1]的所有字符相同
            while (sidesCur <= Math.min(maxRange, s.length() - 1 - (current + rightYC - 1))) {
                //寻找中间块俩侧相等字符的最大长度
                //sidesCur应在中间块的左侧范围内和右侧范围内
                if (s.charAt(current - sidesCur) == s.charAt(current + rightYC - 1 + sidesCur)) {
                    sidesCur++;
                } else {
                    //s[current-(sidesCur-1)]至s[current+rightYC-1+(sidesCur-1)]为当前最大回文串
                    return s.substring(current - (sidesCur - 1), current + rightYC - 1 + (sidesCur - 1) + 1);
                }
            }
            //s[current-(sidesCur-1)]至s[current+rightYC-1+(sidesCur-1)]为当前最大回文串
            return s.substring(current - (sidesCur - 1), current + rightYC - 1 + (sidesCur - 1) + 1);
        }
    }

    public String longestPalindrome(String s) {
        int maxlength = 0;
        String[] maxsubstr = new String[s.length()];
        String substr = "";
        for (int current = 0; current < s.length(); ++current) {
            substr = findStringByCurrent(s, Math.min(current, s.length() - 1 - current), current);
            if (substr.length() > maxlength) {
                maxlength = substr.length();
                maxsubstr[maxlength - 1] = substr;
                if (maxlength == s.length()) return s;
            }
        }
        return maxsubstr[maxlength - 1];
    }

    public String manacher(String s) {
        String str = preProcessStr(s);
        //回文半径数组
        //ada的rArr[1]==2-1==1
        int[] rArr = new int[str.length()];
        //最右回文边界
        int R = -1;
        //最右回文边界对应中心
        int C = -1;
        //最大回文中心
        int maxlengthcenter = 0;
        //最大回文半径
        int maxr = 0;

        for (int i = 0; i < str.length(); ++i) {
            if (i > R) {
                int right = findCurrentHuiWenSubstr(str, i, i);
                rArr[i] = right - i;
                R = right;
                C = i;
            } else {
                if (rArr[2 * C - i] + i > R) {
                    rArr[i] = R - i;
                } else if (rArr[2 * C - i] + i == R) {
                    int right = findCurrentHuiWenSubstr(str, i, R);
                    rArr[i] = right - i;
                    if (right > R) {
                        R = right;
                        C = i;
                    }
                } else {
                    rArr[i] = rArr[2 * C - i];
                }
            }
            if (rArr[i] > maxr) {
                maxlengthcenter = i;
                maxr = rArr[i];
            }
        }
        return s.substring((maxlengthcenter-maxr)/2,(maxlengthcenter+maxr-1)/2+1);
    }

    private int findCurrentHuiWenSubstr(String s, int current, int right) {
        int i = 1;
        if (2 * current - right == 0) return right;
        for (; (2 * current - right - i >= 0) && (right + i < s.length()); ++i) {
            if (s.charAt(2 * current - right - i) != s.charAt(right + i)) {
                break;
            }
        }
        return right + i - 1;
    }

    private String preProcessStr(String s) {
        char[] str = new char[s.length() * 2 + 1];
        for (int i = 0; i < str.length; ++i) {
            if (i % 2 == 0) str[i] = '#';
            else str[i] = s.charAt(i / 2);
        }
//        System.out.println(String.valueOf(str));
        return String.valueOf(str);
    }

    //中心扩散法
    public String centerExpand(String s) {
        int leftmaxstr = 0;
        int rightmaxstr = 0;
        if (s.length() == 0) return "";
        String str = preProcessStr(s);

        for (int i = 0; i < str.length(); i++) {
            //以i为中心扫描全部字符串
            int sides = 1;
            for (; (i - sides >= 0) && (i + sides < str.length()); ) {
                //中心扩散循环
                if (str.charAt(i - sides) == str.charAt(i + sides)) {
                    sides++;
                } else {
                    //发现未达边界的最大回文串
                    break;
                }
            }
            //以i为中心的最大回文串为s[i-(sides-1)]至s[i+(sides-1)]
            if ((rightmaxstr - leftmaxstr) < ((i + sides - 1) - (i - sides + 1))) {
                //若当前回文串比之前的都长
                rightmaxstr = i + sides - 1;
                leftmaxstr = i - sides + 1;
            }
        }
//        System.out.println(leftmaxstr+""+rightmaxstr);
        return s.substring(leftmaxstr / 2, (rightmaxstr - 1) / 2 + 1);
    }

    //动态规划
    public String dp(String s) {
        boolean[][] dpArr = new boolean[s.length()][s.length()];//默认全为false
        int maxlengthleft = 0;
        int maxlengthright = 0;
        for (int bias = 0; bias < s.length(); bias++) {
            for (int i = 0; i + bias < s.length(); ++i) {
                //可以理解为dpArr斜向走
                if (i == i + bias) {
                    //单个字符自己为回文串
                    dpArr[i][i] = true;
                } else if (s.charAt(i) == s.charAt(i + bias)) {
                    //如果不是单个字符的比较且相等
                    if (bias <= 1) {
                        //若i与i+bias相差为0或1（实际只检测1）
                        dpArr[i][i + bias] = true;
                        //只记录第一次遇到的最大回文
                        if (bias > maxlengthright - maxlengthleft) {
                            maxlengthleft = i;
                            maxlengthright = i + bias;
                        }
                    } else {
                        //若i与i+bias相差大于1时
                        if (dpArr[i + 1][i + bias - 1] == true) {
                            //s[i]与s[i+bias]的子回文s[i+1]与s[i+bias-1]为回文
                            //且s[i]==s[i+bias]
                            dpArr[i][i + bias] = true;
                            //只记录第一次遇到的最大回文
                            if (bias > maxlengthright - maxlengthleft) {
                                maxlengthleft = i;
                                maxlengthright = i + bias;
                            }
                        }
                    }
                } else {
                    //如果不是单个字符的比较且不相等
                    //dpArr[i][i + bias] = false;
                }
            }
        }
        return s.substring(maxlengthleft, maxlengthright + 1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = "";
        System.out.println("输入字符串：");
        if (scan.hasNext()) {
            s = scan.nextLine();
        }
        scan.close();
        System.out.println("我算的最长回文子串是：" + new Solution().longestPalindrome(s));
        System.out.println("中心扩散法算的最长回文子串是：" + new Solution().centerExpand(s));
        System.out.println("动态规划算法的最长回文子串是：" + new Solution().dp(s));
        System.out.println("Manacher算法的最长回文字串是："+new Solution().manacher(s));
    }
}
