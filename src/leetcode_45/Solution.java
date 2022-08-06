package leetcode_45;

class Solution {
    public int jump(int[] nums) {
        int index=0;//起跳下标
        int count=0;
        int step=nums[0];//最大步长
        int max_index=1;//最大跳跃次数的下标
        while (index<nums.length){

        }
        for (int i = 1; i <= step; i++) {
            if (nums[max_index]<nums[i+index]){
                max_index=i+index;
            }
        }
        step=nums[max_index];
        index=max_index++;
        count++;
        return count;
    }

    public static void main(String[] args) {
        int[] nums={2,3,1,1,4};
        Solution solution=new Solution();
        System.out.println(solution.jump(nums));
    }
}