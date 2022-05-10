package leetcode_18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 自己琢磨的方法
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int new_target;
        int left;
        int right;
        for (int i = 0; i < nums.length; i++) {
            if ((i != 0) && (nums[i] == nums[i - 1])) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if ((j != i + 1) && (nums[j] == nums[j - 1])) continue;
                new_target = target - nums[i] - nums[j];
                left = j + 1;
                right = nums.length - 1;
                while (left < right) {
                    if ((left != j + 1) && (nums[left] == nums[left - 1])) {
                        left++;
                        continue;
                    }
                    if ((right != nums.length - 1) && (nums[right] == nums[right + 1])) {
                        right--;
                        continue;
                    }
                    if (nums[left] + nums[right] == new_target) {
                        List<Integer> current_ans = new ArrayList<>();
                        current_ans.add(nums[i]);
                        current_ans.add(nums[j]);
                        current_ans.add(nums[left]);
                        current_ans.add(nums[right]);
//                        if (!ans.contains(current_ans))
                        ans.add(current_ans);
                        right--;
                        left++;
                    } else if (nums[left] + nums[right] < new_target) left++;
                    else right--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().fourSum(new int[]{2, 2, 2, 2, 2}, 8));
    }
}
