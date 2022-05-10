package leetcode_14;

import java.util.Scanner;

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        int pointer = 0;
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        for (int i = 1; pointer<strs[0].length(); i++) {
            if (strs[i].length()>pointer){
                if (strs[0].charAt(pointer)==strs[i].charAt(pointer)){
                    //如果当前字符串的poniter处字符与0号字符串的相应位置相等
                    //则应i++匹配pointer处字符
                    if (i==strs.length-1){
                        //如果匹配到了最后一个字符串
                        i=0;
                        pointer++;
                    }
                }
                else {
                    //如果当前字符串的pointer处字符与0号字符串的相应位置不等
                    //最长公共前缀只可能为s[0]的0~pointer-1子串
                    if (pointer>0){
                        return strs[0].substring(0,pointer);
                    }
                    else return "";
                }
            }
            else {
                //如果pointer大于字符串长度
                //最长公共前缀只可能为s[0]的0~pointer-1子串
                if (pointer>0){
                    return strs[0].substring(0,pointer);
                }
                else return "";
            }

        }
        //当s[0]都扫描完了还都匹配，则s[0]为最长公共子串
        return strs[0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入字符串数组长度：");
        int n = 0;
        if (scanner.hasNextInt()) {
            n = scanner.nextInt();
        }
        System.out.println("输入字符串数组：");
        String[] slist = new String[n];
        for (int i = 0; i < n; i++) {
            if (scanner.hasNext())
                slist[i] = scanner.next();
        }
        scanner.close();
        System.out.println("字符串数组的最长公共前缀为：" + new Solution().longestCommonPrefix(slist));
        /*
        3
        flower
        flow
        flight
        */
    }
}
