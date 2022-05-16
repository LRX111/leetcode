package leetcode_33;

public class Solution {
    public int search(int[] nums, int target) {
        int i = 0;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        for (; i < nums.length; i++) {
            if (nums[i] == target) return i;
            if (i + 1 == nums.length - 1) {
                if (nums[i + 1] == target) return i + 1;
                else return -1;
            } else if (nums[i] > nums[i + 1]) break;
        }
        if (++i < nums.length) {
            int left = i;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) return mid;
                else if (nums[mid] < target) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }

    public int binaryS_search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (nums.length == 1) return target == nums[0] ? 0 : -1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target == nums[mid]) return mid;
            if (left == mid) {
                //此时left==mid==right-1
                if (target == nums[right]) return right;
                else return -1;
            } else if (nums[left] <= nums[mid - 1] && target >= nums[left] && target <= nums[mid - 1]) {
                return binarySearch(nums, left, mid - 1, target);
            } else if (nums[mid + 1] <= nums[right] && target <= nums[right] && target >= nums[mid + 1]) {
                return binarySearch(nums, mid + 1, right, target);
            } else if (nums[left] > nums[mid - 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int binarySearch(int[] nums, int began, int end, int target) {
        while (began <= end) {
            int mid = (began + end) >> 1;
            if (target == nums[mid]) return mid;
            else if (nums[mid] > target) end = mid - 1;
            else began = mid + 1;
        }
        return -1;
    }
}
