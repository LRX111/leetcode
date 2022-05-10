package leetcode_6;

import java.util.Scanner;

public class Solution {
    public String convert(String s, int numRows) {
        StringBuilder ans = new StringBuilder();
        if (numRows == 1) return s;
        for (int i = 0; i < numRows; i++) {
            if (i < s.length()) {
                ans.append(s.charAt(i));
                int A = i;
                int B = 2 * ((numRows - 1) - i) + A;
                int C = 2 * (numRows - 1) + A;
                while (true) {
                    if (B < s.length()) {
                        if (B != A && B != C)
                            ans.append(s.charAt(B));
                    } else break;
                    if (C < s.length()) {
                        ans.append(s.charAt(C));
                    } else break;
                    A = C;
                    B = 2 * ((numRows - 1) - i) + A;
                    C = 2 * (numRows - 1) + A;
                }
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "";
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入用于Z字形变换的字符串：");
        if (scanner.hasNextLine()) {
            s = scanner.nextLine();
        }
        System.out.println("输入行数：");
//        遇到hasNext()时，Scanner也会阻塞，等待你输入，等你输入后返回true。
//        查看jdkapi，你会发现该方法当Scanner缓存区中有值可读时，会返回true，
//        若没有，会一直阻塞等待你输入。
        while (scanner.hasNextInt()) {
            n = scanner.nextInt();
            break;
        }
//        System.out.println(s + n);
//        String ans=new Solution().convert(s,n);
        scanner.close();
        System.out.println("Z字变换后从左往右逐行读取为：" + new Solution().convert(s, n));
    }
}
