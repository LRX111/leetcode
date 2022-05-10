package leetcode_13;

import leetcode_12.Solution;

import java.util.HashMap;
import java.util.Scanner;

public class Solution2 {
    public int romanToInt(String s) {
        int[] numlist = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] symbollist = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        HashMap<String, Integer> symbolTonumber = new HashMap<>();
        for (int i = 0; i < 13; i++) {
            symbolTonumber.put(symbollist[i], numlist[i]);
        }

        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) == 'V') {
                        //特殊情况
                        i += 1;
                        ans += symbolTonumber.get("IV");
                    } else if (s.charAt(i + 1) == 'X') {
                        //特殊情况
                        i += 1;
                        ans += symbolTonumber.get("IX");
                    } else
                        ans += symbolTonumber.get("I");
                } else
                    ans += symbolTonumber.get("I");

            } else if (s.charAt(i) == 'X') {
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) == 'L') {
                        //特殊情况
                        i += 1;
                        ans += symbolTonumber.get("XL");
                    } else if (s.charAt(i + 1) == 'C') {
                        //特殊情况
                        i += 1;
                        ans += symbolTonumber.get("XC");
                    } else
                        ans += symbolTonumber.get(String.valueOf(s.charAt(i)));
                } else
                    ans += symbolTonumber.get(String.valueOf(s.charAt(i)));

            } else if (s.charAt(i) == 'C') {
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) == 'D') {
                        //特殊情况
                        i += 1;
                        ans += symbolTonumber.get("CD");
                    } else if (s.charAt(i + 1) == 'M') {
                        //特殊情况
                        i += 1;
                        ans += symbolTonumber.get("CM");
                    } else
                        ans += symbolTonumber.get(String.valueOf(s.charAt(i)));
                } else
                    ans += symbolTonumber.get(String.valueOf(s.charAt(i)));
            } else {
                ans += symbolTonumber.get(String.valueOf(s.charAt(i)));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int num = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入数字：");
        if (scanner.hasNextInt()) {
            num = scanner.nextInt();
        }
        scanner.close();
        Solution solution = new Solution();
        String s = solution.intToRoman(num);
        System.out.println("罗马数字为：" + s);
        System.out.println("再转回数字为：" + new Solution2().romanToInt(s));
    }
}
