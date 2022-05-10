package leetcode_27;

import java.util.Arrays;

public class Solution {
    public int removeElement(int[] nums, int val) {
        int ans = -1;//插入位置的前一个位置
        int cur = 0;//扫描位置
        for (; cur < nums.length; ) {
            if (nums[cur] == val) {
                cur++;
            } else {
                nums[++ans] = nums[cur];
                cur++;
            }
        }
        return ans + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        int val = 3;
        Solution solution = new Solution();
        System.out.println(solution.removeElement(nums, val) + " " + Arrays.toString(nums));
    }
}
