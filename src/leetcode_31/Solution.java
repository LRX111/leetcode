package leetcode_31;

import java.util.Arrays;

public class Solution {
    public void nextPermutation(int[] nums) {
        boolean flg = true;
        for (int i = nums.length - 1; i - 1 >= 0; i--) {
            if (nums[i] <= nums[i - 1]) continue;
            else {
                flg = false;
                //从后往前看，第一次发现 nums[i-1] < nums[i]
                for (int j = nums.length - 1; j >= i; j--) {
                    //从末尾开始，寻找第一次比 nums[i-1]<的数 nums[j],然后交换
                    if (nums[i - 1] < nums[j]) {
                        int t = nums[i - 1];
                        nums[i - 1] = nums[j];
                        nums[j] = t;
                        break;
                    }
                }
                //将 nums[i]~nums[len-1] 从小到大排列（**nums[i]~nums[len-1]本身为倒序**）
//                int[] cutnums = new int[nums.length - i];
//                System.arraycopy(nums, i, cutnums, 0, cutnums.length);
//                Arrays.sort(cutnums);
                for (int left = i, right = nums.length - 1; left < right; left++, right--) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
//                System.arraycopy(cutnums, 0, nums, i, cutnums.length);
                break;
            }
        }
        if (flg == true) {
            for (int left = 0, right = nums.length - 1; left < right; left++, right--) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,4,2};
        Solution solution = new Solution();
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
