package leetcode_35;

public class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target)
                right = mid - 1;
            else left = mid + 1;
        }
        if (target == nums[left]) return left;
        else if (target < nums[left]) return left;
        else return left + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, 5, 6};
        int target = 0;
        System.out.println(solution.searchInsert(nums, target));
    }
}
