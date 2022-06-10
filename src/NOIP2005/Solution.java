package NOIP2005;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    public static int mySol(int n, int k) {
        int modd = (int) Math.pow(10, k);
        int res = n % modd;
        int nPow = (n * n) % modd;
        int count = 1;
        Set<Integer> set = new HashSet<>();
        set.add(nPow);
        while (nPow != res) {
            nPow = (nPow * n) % modd;
            if (set.contains(nPow)) return -1;
            count++;
        }
        return count;
    }

    /**
     * 考虑使每一位循环的乘数因子，直到第k位
     *
     * @param n
     * @param k
     * @return
     */
    public static String xunhuanjie(String n, int k) {
        String[] init_ans = {"1", "1", "4", "4", "2", "1", "1", "4", "4", "2"};
        String ans = init_ans[n.charAt(n.length() - 1) - '0'];
        if (k == 1) return ans;
        int modd = (int) Math.pow(10, k);
        String yinzi = n;
        for (int i = 1; i < Integer.parseInt(ans); i++) {
            yinzi = bigIntMultipl(n, yinzi, k);
        }
        for (int i = 2; i <= k; i++) {
            String curryinzi = yinzi;
            int count = 1;
            String npow = bigIntMultipl(n, curryinzi, i);
            char res = n.length() - i >= 0 ? n.charAt(n.length() - i) : '0';
            char npow_char = npow.length() - i >= 0 ? npow.charAt(npow.length() - i) : '0';
            Set<Character> set = new HashSet<>();
            set.add(npow_char);
            while (res != npow_char) {
                curryinzi = bigIntMultipl(curryinzi, yinzi, k);
                npow = bigIntMultipl(n, curryinzi, i);
                npow_char = npow.length() - i >= 0 ? npow.charAt(npow.length() - i) : '0';
                if (set.contains(npow_char)) return "-1";
                set.add(npow_char);
                count++;
            }
            ans =bigIntMultipl(ans,Integer.toString(count),Integer.MAX_VALUE);
            yinzi = curryinzi;
        }
        return ans;
    }

    public static String bigIntMultipl(String a, String b, int modd) {
        StringBuilder ans = new StringBuilder();
        int bindex = b.length() - 1;
        int padding = 0;
        for (int i = 0; i < modd && bindex >= 0; i++, bindex--) {
            //加
            int b_num = b.charAt(bindex) - '0';
            int aindex = a.length() - 1;
            StringBuilder subans = new StringBuilder();
            int jinwei = 0;
            for (int j = 0; j < modd && aindex >= 0; j++, aindex--) {
                //乘
                int a_num = a.charAt(aindex) - '0';
                int res = a_num * b_num + jinwei;
                subans.append(res % 10);
                jinwei = res / 10;
            }
            if (jinwei != 0) subans.append(jinwei);
            //将ans与subans有偏移量地相加到ans中
            int jinWei = 0;
            int x = 0;
            for (; x + padding < ans.length() && x < subans.length(); x++) {
                int num1 = ans.charAt(x + padding) - '0';
                int num2 = subans.charAt(x) - '0';
                int sum = num1 + num2 + jinWei;
                jinWei = sum / 10;
                ans.setCharAt(x + padding, (char) (sum % 10 + '0'));
            }
            if (x < subans.length()) {
                //此时一定有 x + padding >= ans.length()
                for (int j = x; j < subans.length(); j++) {
                    int num = subans.charAt(j) - '0';
                    int sum = num + jinWei;
                    ans.append(sum % 10);
                    jinWei = sum / 10;
                }
                if (jinWei > 0) ans.append(jinWei);
            } else {
                //此时 x + padding 与 ans.length()的关系不确定，但x = subans.length()
                //若 x + padding < ans.length()，则应取出ans.charAt(x+padding)与jinWei相加
                //若 x + padding = ans.length()，则应将jinWei添加在ans之后
                if (x + padding < ans.length()) {
                    for (int j = x + padding; j < ans.length(); j++) {
                        int num = ans.charAt(j) - '0';
                        int sum = jinWei + num;
                        ans.setCharAt(j, (char) (sum % 10 + '0'));
                        jinWei = sum / 10;
                    }
                    if (jinWei > 0) ans.append(jinWei);
                } else {
                    if (jinWei > 0) {
                        ans.append(jinWei);
                    }
                }
            }
            padding++;
        }
        StringBuilder t = new StringBuilder(ans.substring(0, Math.min(modd, ans.length())));
        return t.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String n = scanner.next();
            int k = scanner.nextInt();
//            System.out.println(mySol(n, k));
//            System.out.println(Integer.MAX_VALUE);
//            System.out.println(n + " " + k);
            System.out.println(xunhuanjie(n, k));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
