package leetcode_9;

import java.util.Scanner;

public class Solution {
    public boolean isPalindrome(int x) {
        int copy_x = x;
        int reverse_x = 0;
        int current = 0;//current表示当前的个位
        while (x != 0) {
            current = x % 10;
            x = x / 10;
            if (current >= 0) {
                //如果越界，说明不是回文
                if ((Integer.MAX_VALUE - current) / 10 < reverse_x) {
                    return false;
                }
            } else {
                //如果越界，说明不是回文
//                if ((Integer.MIN_VALUE - current) / 10 > reverse_x) {
//                    return false;
//                }
                //负数都不是回文
                return false;
            }
            reverse_x = reverse_x * 10 + current;
        }
        if (reverse_x == copy_x) return true;
        else return false;

    }

    public static void main(String[] args) {
        System.out.println("输入整数：");
        int x = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            x = scanner.nextInt();
            break;
        }
        scanner.close();
        System.out.println("整数是否为回文整数：" + new Solution().isPalindrome(x));
    }
}
