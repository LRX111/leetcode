package leetcode_7;

import java.util.Scanner;

public class Solution {
    public int reverse(int x) {
        Long num = Long.valueOf(x);
        boolean flag = true;
        if (num < 0) {
            flag = false;
            num = Math.abs(num);
        }
        String s = num.toString();

        StringBuilder s1 = new StringBuilder();
        boolean t = false;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '0' && t == false) {
            } else {
                s1.append(s.charAt(i));
                t = true;
            }
        }
        Integer maxint = Integer.MAX_VALUE;
//        Integer minint = Integer.MIN_VALUE;
        String maxintstring = maxint.toString();
//        String minintstring = minint.toString();
//        System.out.println(maxintstring + '\n' + minintstring + '\n' + s1);
//        StringBuilder maxintstringReverse=new StringBuilder();
//        for (int i = maxintstring.length() - 1; i >= 0; --i) {
//            if (maxintstring.charAt(i) == '0') {
//            } else {
//                maxintstringReverse.append(maxintstring.charAt(i));
//            }
//        }
        if (flag) {
            if (s1.length() == maxintstring.length()) {
                for (int i = 0; i < s1.length(); i++) {
                    if (s1.charAt(i) > maxintstring.charAt(i)) return 0;
                    else if (s1.charAt(i) < maxintstring.charAt(i)) break;
                }
            }
        } else {
            if (s1.length() == maxintstring.length()) {
                for (int i = 0; i < s1.length(); i++) {
                    if (s1.charAt(i) > maxintstring.charAt(i)) {
                        if (i == s1.length() - 1) {
                            if (s1.charAt(i) > '8') {
                                return 0;
                            } else {
                                //如果最后一位等于‘8’
                                s1.replace(i, i + 1, "7");
                                x = Integer.valueOf(s1.toString());
                                return 0 - x - 1;
                            }
                        } else {
                            //s1.charAt(i)>maxintstring.charAt(i)
                            //且i ！= s1.length() - 1
                            return 0;
                        }
                    } else if (s1.charAt(i) < maxintstring.charAt(i)) break;
                }
            }
        }

        if (s1.length() == 0) {
            //输入为前n个0
            return 0;
        }
        x = Integer.valueOf(s1.toString());
        if (flag) return x;
        else {
            x = 0 - x;
            return x;
        }
    }

    //按位反转
    public int referenceAnswer(int x) {
        int num = x;
        int ans = 0;
        int maxint = Integer.MAX_VALUE;
        while (num != 0) {
            int current = num % 10;
            num = num / 10;
            //在推入之前判断是否满足MIN_VALUE <= ans*10+current <= MAX_VALUE
            //即(MIN_VALUE-current)/10 <= ans <= (MAX_VALUE-current)/10
            if (x > 0) {
                //只检测是否最大越界
                if ((Integer.MAX_VALUE - current) / 10 >= ans) {
                    ans = ans * 10 + current;
                } else return 0;
            } else if (x < 0) {
                //只检测是否最小越界
                if ((Integer.MIN_VALUE - current) / 10 <= ans) {
                    ans = ans * 10 + current;
                } else return 0;
            } else return 0;
//            if (((Integer.MIN_VALUE - current) / 10 <= ans) && ((Integer.MAX_VALUE - current) / 10 >= ans)) {
//                ans = ans * 10 + current;
//            } else return 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        System.out.println("输入整数：");
        while (scanner.hasNextInt()) {
            x = scanner.nextInt();
            break;
        }
        scanner.close();
        System.out.println("反转后的整数为：" + new Solution().reverse(x));
        System.out.println("标准答案解法为：" + new Solution().referenceAnswer(x));
    }
}
