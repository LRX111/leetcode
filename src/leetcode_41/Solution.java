package leetcode_41;

import Internal_Sorting.MaxHeap;

public class Solution {
    private int modcount = 0;

    public int firstMissingPositive(int[] nums) {
        if (nums.length == 1) return nums[0] == 1 ? 2 : 1;
        makeheap(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int top = gettop(nums);
            if (top <= 0) continue;
            else {
                if (top==count)continue;
                else if (top==count+1)count++;
                else return count+1;
            }
        }
        return count == 0 ? 1 : count + 1;
    }

    private void makeheap(int[] nums) {
        int lastIndex = (nums.length - 2) >>> 1;
        while (lastIndex >= 0) {
            int temp = nums[lastIndex];
            int pa = lastIndex;
            int left = (lastIndex << 1) + 1;
            while (left < nums.length) {
                if (left + 1 < nums.length && nums[left] > nums[left + 1])
                    left++;
                if (temp > nums[left]) {
                    nums[pa] = nums[left];
                    pa = left;
                    left = (pa << 1) + 1;
                } else break;
            }
            nums[pa] = temp;
            lastIndex--;
        }
    }

    private int gettop(int[] nums) {
        int ans = nums[0];
        modcount++;
        if (nums.length > 1) {
            int temp = nums[0] = nums[nums.length - modcount];
//            nums[nums.length - modcount]=ans;
            int parent = 0;
            int left = 1;
            while (left < nums.length - modcount) {
                if (left + 1 < nums.length - modcount && nums[left] > nums[left + 1])
                    left++;
                if (temp > nums[left]) {
                    nums[parent] = nums[left];
                    parent = left;
                    left = (parent << 1) + 1;
                } else break;
            }
            nums[parent] = temp;
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] nums = {0,2,2,1,1};
        Solution solution = new Solution();
//        System.out.println(solution.firstMissingPositive(nums));
        int[] nums = new int[]{7, 8, 9, 11, 12};
        System.out.println(solution.firstMissingPositive(nums));
    }
}
