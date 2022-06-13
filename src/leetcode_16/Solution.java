package leetcode_16;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    /**
     * 错误解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int ans = 0;
        int targetcp = target;
        int[] ans_index = new int[3];
        boolean gap = false;
        Arrays.sort(nums);
        /**
         * 第一个数
         */
        int left = 0;
        int right = nums.length - 1;
        int mid;
        if (target < nums[left]) {
            ans += nums[left];
            ans_index[0] = left;
            removeE(nums, left);
        } else if (target >= nums[right]) {
            ans += nums[right];
            ans_index[0] += right;
            removeE(nums, right);
        } else {
            while (left + 1 < right) {
                mid = (left + right) / 2;
                if (target == nums[mid]) {
                    ans_index[0] = mid;
                    gap = true;
                    ans += nums[mid];
                    removeE(nums, mid);
                    break;
                } else if (target < nums[mid]) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            if (gap != true) {
                ans += Math.abs(target - nums[left]) < Math.abs(target - nums[right]) ? nums[left] : nums[right];
                ans_index[0] += Math.abs(target - nums[left]) < Math.abs(target - nums[right]) ? left : right;
                removeE(nums, ans_index[0]);
            }
        }

        /**
         * 第二个数
         */
        target = targetcp - ans;
        left = 0;
        right = nums.length - 2;
        gap = false;
        if (target < nums[left]) {
            ans += nums[left];
            ans_index[1] = left;
            removeE(nums, left);
        } else if (target >= nums[right]) {
            ans += nums[right];
            ans_index[1] += right;
            removeE(nums, right);
        } else {
            while (left + 1 < right) {
                mid = (left + right) / 2;
                if (target == nums[mid]) {
                    ans_index[1] = mid;
                    gap = true;
                    ans += nums[mid];
                    removeE(nums, mid);
                    break;
                } else if (target < nums[mid]) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            if (gap != true) {
                ans += Math.abs(target - nums[left]) < Math.abs(target - nums[right]) ? nums[left] : nums[right];
                ans_index[1] += Math.abs(target - nums[left]) < Math.abs(target - nums[right]) ? left : right;
                removeE(nums, ans_index[1]);
            }
        }

        /**
         * 第三个数
         */
        target = targetcp - ans;
        left = 0;
        right = nums.length - 3;
        gap = false;
        if (target < nums[left]) {
            ans += nums[left];
            ans_index[2] = left;
            removeE(nums, left);
        } else if (target >= nums[right]) {
            ans += nums[right];
            ans_index[2] += right;
            removeE(nums, right);
        } else {
            while (left + 1 < right) {
                mid = (left + right) / 2;
                if (target == nums[mid]) {
                    ans_index[2] = mid;
                    gap = true;
                    ans += nums[mid];
                    removeE(nums, mid);
                    break;
                } else if (target < nums[mid]) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            if (gap != true) {
                ans += Math.abs(target - nums[left]) < Math.abs(target - nums[right]) ? nums[left] : nums[right];
                ans_index[2] += Math.abs(target - nums[left]) < Math.abs(target - nums[right]) ? left : right;
//            removeE(nums, ans_index[2]);
            }
        }


        return ans;
    }

    private void removeE(int[] nums, int index) {
        for (int i = index + 1; i < nums.length; i++) {
            nums[i - 1] = nums[i];
        }
    }

    /**
     * 暴力解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int baoli_threeSumClosest(int[] nums, int target) {
        int ans = 0;
        int mingap = Integer.MAX_VALUE;
        int sum;
        int gap;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    sum = nums[i] + nums[j] + nums[k];
                    gap = Math.abs(target - sum);
                    if (gap < mingap) {
                        mingap = gap;
                        ans = sum;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 双指针法
     *
     * @param nums
     * @param target
     * @return
     */
    public int doubleIndex_threeSumClosest(int[] nums, int target) {
        int ans = 0;
        int sum;
        int gap;
        int mingap = Integer.MAX_VALUE;
        int left;
        int right;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                sum = nums[i] + nums[left] + nums[right];
                gap = Math.abs(target - sum);
                if (gap == 0) return sum;
                else if (gap < mingap) {
                    mingap = gap;
                    ans = sum;
                }
                if (sum > target) {
                    right = right - 1;
                } else if (sum < target) {
                    left = left + 1;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入整数数组的个数：");
        int n = 0;
        if (sc.hasNextInt()) {
            n = sc.nextInt();
        }
        System.out.println("输入整数数组：");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println("输入目标值：");
        int target = 0;
        if (sc.hasNextInt()) {
            target = sc.nextInt();
        }
        sc.close();
        System.out.println("数组中与target最接近的三个数之和为：" + new Solution().threeSumClosest(nums, target));
        /**
         * 4
         * -1 2 1 -4
         * 1
         */
    }
}
