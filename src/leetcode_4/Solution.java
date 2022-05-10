package leetcode_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int mid = (nums1.length + nums2.length) / 2;
        int[] ans = new int[nums1.length + nums2.length];
        if (nums1.length == 0 && nums2.length == 0) return 0;
        else if (nums1.length == 0) ans = nums2;
        else if (nums2.length == 0) ans = nums1;
        else {
            while (true) {
                if ((i + j) > mid) {
                    break;
                } else {
                    if (j <= (nums2.length - 1) && i <= (nums1.length - 1) && (nums1[i] <= nums2[j])) {
                        ans[i + j] = nums1[i];
                        ++i;
                    } else if (i == nums1.length) {
                        ans[i + j] = nums2[j];
                        ++j;
                    } else if (i <= (nums1.length - 1) && j <= (nums2.length - 1) && (nums2[j] < nums1[i])) {
                        ans[i + j] = nums2[j];
                        ++j;
                    } else if (j == nums2.length) {
                        ans[i + j] = nums1[i];
                        ++i;
                    }
                }
            }
        }
        if ((nums1.length + nums2.length) % 2 == 0)
            return (double) (ans[mid] + ans[mid - 1]) / 2;
        else return ans[mid];
    }

    //找分割线
//    public double findMedianSortedArrays(int[] nums1, int[] nums2)

    public static void main(String[] args) {
        int n = 0, m = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("nums1长度:");
        if (scan.hasNextInt()) {
            n = scan.nextInt();
        }
        int[] nums1 = new int[n];
        System.out.println("nums1的值：");
        for (int i = 0; i < n; ++i) {
            if (scan.hasNextInt())
                nums1[i] = scan.nextInt();
        }
        System.out.println("nums2长度:");
        if (scan.hasNextInt()) {
            m = scan.nextInt();
        }
        int[] nums2 = new int[m];
        System.out.println("nums2的值：");
        for (int i = 0; i < m; ++i) {
            if (scan.hasNextInt())
                nums2[i] = scan.nextInt();
        }
        scan.close();
        Solution s = new Solution();
        System.out.println("两个正序数组的中位数是：" + s.findMedianSortedArrays(nums1, nums2));
//        System.out.println("两个正序数组的中位数是：" +
//                s.findMedianSortedArrays(new int[]{100001}, new int[]{100000}));
    }
}
