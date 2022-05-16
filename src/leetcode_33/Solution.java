package leetcode_33;

public class Solution {
    public int search(int[] nums, int target) {
        int i = 0;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        for (; i < nums.length; i++) {
            if (nums[i] == target) return i;
            if (i+1==nums.length-1){
                if (nums[i+1]==target)return i+1;
                else return -1;
            }
            else if (nums[i] > nums[i + 1]) break;
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
}
