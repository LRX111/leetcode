package leetcode_11;

import java.util.Scanner;

public class Solution {
    public int maxArea(int[] height) {
        int maxvolum = 0;
        int volum = 0;
        int line1=0;
        int ling2=0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                volum = (j - i) * Math.min(height[i], height[j]);
                if (volum > maxvolum) {
                    maxvolum = volum;
                    line1=i;
                    ling2=j;
                }
            }
        }
        System.out.println(line1+" "+ling2);
        return maxvolum;
    }

    //两个指针
    public int doublePoint(int[] height){
        int maxvolum=0;
        int volum=0;
        int i=0;
        int j=height.length-1;
        while (j>i){
            volum=(j-i)*Math.min(height[i],height[j]);
            if (volum>maxvolum){
                maxvolum=volum;
            }
            if (height[i]<=height[j]){
                i++;
            }else {
                j--;
            }
        }
        return maxvolum;
    }

    public static void main(String[] args) {
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入垂线数组长度(大于等于2)：");
        if (scanner.hasNextInt()) {
            n = scanner.nextInt();
        }
        int[] height = new int[n];
        System.out.println("输入垂线数组（大于等于0）：");
        // 1 8 6 2 5 4 8 3 7
        for (int i = 0; i < n; i++) {
            if (scanner.hasNextInt()) {
                height[i] = scanner.nextInt();
            }
        }
        scanner.close();
        System.out.println("存在的垂线组成的容器中可以存储的最大水量为：" + new Solution().maxArea(height));
        System.out.println("两指针法计算的最大水量为：" + new Solution().doublePoint(height));
    }
}
