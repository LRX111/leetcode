package leetcode_34;

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        if (nums == null || nums.length == 0) return ans;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                int i;
                for (i = mid - 1; i >= 0; i--) {
                    if (nums[i] != target) {
                        ans[0] = i + 1;
                        break;
                    }
                }
                if (i == -1) ans[0] = 0;
                for (i = mid + 1; i < nums.length; i++) {
                    if (nums[i] != target) {
                        ans[1] = i - 1;
                        break;
                    }
                }
                if (i == nums.length) ans[1] = nums.length - 1;
                return ans;
            } else if (target > nums[mid]) left = mid + 1;
            else right = mid - 1;
        }
        return ans;
    }
}
