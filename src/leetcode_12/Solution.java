package leetcode_12;

import java.util.Scanner;

public class Solution {
    public String intToRoman(int num) {
        //先确定整数的范围
//        字符          数值
//        I             1
//        V             5
//        X             10
//        L             50
//        C             100
//        D             500
//        M             1000
//        I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
//        X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
//        C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
        if (num == 0) return "";
        String str = Integer.toString(num);
        String s = "";
        int len = str.length();
        switch (len) {
            case 1:
                //处理个位数字
                if (num == 4) return "IV";
                if (num == 9) return "IX";
                if (num >= 5) {
                    s += "V";
                    num = num - 5;
                    while (num != 0) {
                        s += "I";
                        num = num - 1;
                    }
                    return s;
                } else {
                    while (num != 0) {
                        s += "I";
                        num = num - 1;
                    }
                    return s;
                }
            case 2:
                //处理十位数字
                if (num / 10 == 4) {
                    s += "XL";
                    num = num % 10;//取个位数字
                    s += intToRoman(num);
                    return s;
                } else if (num / 10 == 9) {
                    s += "XC";
                    num = num % 10;
                    s += intToRoman(num);
                    return s;
                } else if ((num / 10) >= 5) {
                    s += "L";
                    num = num - 50;
                    while (num >= 10) {
                        s += "X";
                        num = num - 10;
                    }
                    //跳出循环后num仅剩个位
                    s += intToRoman(num);
                    return s;
                } else {
                    while (num >= 10) {
                        s += "X";
                        num = num - 10;
                    }
                    //跳出循环后num仅剩个位
                    s += intToRoman(num);
                    return s;
                }
            case 3:
                if (num / 100 == 4) {
                    s += "CD";
                    //取十位数字和个位数字
                    num = num % 100;
                    s += intToRoman(num);
                    return s;
                } else if (num / 100 == 9) {
                    s += "CM";
                    num = num % 100;
                    s += intToRoman(num);
                    return s;
                } else if ((num / 100) >= 5) {
                    s += "D";
                    num = num - 500;
                    while (num >= 100) {
                        s += "C";
                        num = num - 100;
                    }
                    s += intToRoman(num);
                    return s;
                } else {
                    while (num >= 100) {
                        s += "C";
                        num = num - 100;
                    }
                    s += intToRoman(num);
                    return s;
                }
            default:
                //如果num>=1000，即str.length>=4
                str = str.substring(0, str.length() - 3);//截取除百位、十位、个位以外的前n个字符
                for (int i = 0; i < Integer.parseInt(str); i++) {//即有多少个1000
                    s += "M";
                }
                s += intToRoman(num % 1000);
                return s;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        System.out.println("输入数字：");
        if (scanner.hasNextInt()) {
            n = scanner.nextInt();
        }
        scanner.close();
        System.out.println("罗马数字为：" + new Solution().intToRoman(n));
    }
}
