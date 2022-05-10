package leetcode_8;

import java.util.Scanner;

public class Solution {
    public int myAtoi(String s) {
        int ans = 0;
        boolean flagnum = true;//是否为正数
        boolean flagchar = false;//是否为数字后字符
        for (int i = 0; i < s.length(); ++i) {
            //如果当前字符不与数字有关或空格，则直接返回ans
            if (s.charAt(i) != ' ' && s.charAt(i) != '+' && s.charAt(i) != '-' && (s.charAt(i) < '0' || s.charAt(i) > '9'))
                return ans;
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                flagchar = true;
                int current = Integer.parseInt(s.substring(i, i + 1));
                //如果 Integer.MIN_VALUE <= ans*10+current <=Integer.MAX_VALUE 则更新ans
                //否则返回Integer.MIN_VALUE或Integer.MAX_VALUE
                if (flagnum == true) {
                    //若为正数
                    if ((Integer.MAX_VALUE - current) / 10 >= ans) {
                        ans = ans * 10 + current;
                    } else return Integer.MAX_VALUE;
                } else {
                    //若为负数
                    if ((Integer.MIN_VALUE + current) / 10 <= ans) {
                        ans = ans * 10 - current;
                    } else return Integer.MIN_VALUE;
                }
            }
            //如果当前字符为数字后字符，则跳出
            else if (flagchar == true) break;
                //如果当前字符不是数字后字符，且为‘-’
            else if (flagnum != false && s.charAt(i) == '-') {
                flagnum = false;
                flagchar = true;
            }
            //如果当前字符不是数字后字符，且为‘+’
            else if (flagnum != false && s.charAt(i) == '+') {
                flagnum = true;
                flagchar = true;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        Solution example = new Solution();
        String str = new String();
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入欲转换为整数的字符串：");
        if (scanner.hasNextLine()) {
            str = scanner.nextLine();
        }
        scanner.close();
//        System.out.println(str);
        System.out.println("字符串中的数字为：" + example.myAtoi(str));
    }
}
