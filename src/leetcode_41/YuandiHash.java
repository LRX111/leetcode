package leetcode_41;

public class YuandiHash {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 1) return nums[0] == 1 ? 2 : 1;
        boolean flg = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1)
                flg = true;
            if (nums[i] <= 0 || nums[i] > nums.length)
                nums[i] = 1;
        }
        if (flg == false) return 1;
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }
        int ans = 0;
        for (; ans < nums.length; ans++) {
            if (nums[ans] > 0) return ans + 1;
        }
        return ans+1;
    }

    public static void main(String[] args) {
        YuandiHash solution = new YuandiHash();
//        System.out.println(solution.firstMissingPositive(nums));
        int[] nums = new int[]{1};
        System.out.println(solution.firstMissingPositive(nums));
    }
}
