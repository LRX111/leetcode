package leetcode_26;

import java.util.Arrays;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int ans = nums.length;
        for (int i = 0; i < ans; i++) {
            int j = i + 1;
            for (; j < ans; ) {//第二层循环用于寻找相同元素
                if (nums[i] == nums[j]) j++;
                else break;
            }
            //j要么==nums.length，要么nums[i]!=nums[j]
            moveRemainElement(nums, i + 1, j, ans);
            ans = ans - (j - (i + 1));
        }
        return ans;
    }

    /**
     * @param nums
     * @param began 起点
     * @param from  需要挪动的起始坐标
     * @param to    需要挪动的终点坐标+1
     */
    private void moveRemainElement(int[] nums, int began, int from, int to) {
        for (; from > began && from < to; began++, from++) {
            nums[began] = nums[from];
        }
    }

    /**
     * 双指针法
     *
     * @param nums
     * @return
     */
    public int doublePoint(int[] nums) {
        if (nums.length==0)return 0;
        int ans = 0;//当前插入位置
        int bijiaoPoint = 0;
        int fastPoint = 1;
        for (; fastPoint < nums.length; ) {
            if (nums[bijiaoPoint] == nums[fastPoint]) fastPoint++;
            else {
                nums[ans] = nums[fastPoint - 1];
                ans++;
                bijiaoPoint = fastPoint;
                fastPoint++;
            }
        }
        nums[ans] = nums[fastPoint - 1];
        return ans + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
//        int ans = solution.removeDuplicates(nums);
        int ans = solution.doublePoint(nums);
        System.out.println(Arrays.toString(nums) + " " + ans);

    }
}
