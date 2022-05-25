package leetcode_268;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int missingNumber(int[] nums) {
        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i]) == nums.length || i == zeroIndex) continue;
//            int t=nums[Math.abs(nums[i])];
            if (nums[Math.abs(nums[i])] == 0) {
                //如果要标记的位置为0，将0改为-1，同时标记0位置
                //如果0位置不是0，则要么nums[0]的位置已经标记过，要么为nums[0]==nums.length
                //同时下次检测时跳过当前的Math.abs(nums[i])下标，因为已经标记过
                zeroIndex = Math.abs(nums[i]);
                nums[zeroIndex] = -1;//标记当前位置
                nums[0] = -1;//标记0位置
            } else nums[Math.abs(nums[i])] = -Math.abs(nums[Math.abs(nums[i])]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) return i;
        }
        return nums.length;
    }

    public int hashMapSolution(int[] nums) {
        Set<Integer> map = new HashSet<>();
        for (int num : nums) {
            map.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!map.contains(i)) return i;
        }
        return nums.length;
    }

    /**
     * 按位异或运算⊕满足交换律和结合律，且对任意整数x都满足x⊕x=0和x⊕0=x
     *
     * @param nums
     * @return
     */
    public int yihuoSolution(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 0; i < nums.length + 1; i++) {
            xor ^= i;
        }
        return xor;
    }

    /**
     * 直接用赢得的加和减去实际的加和
     *
     * @param nums
     * @return
     */
    public int mathSolution(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return (nums.length * (nums.length + 1) >> 1) - sum;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 0};
        Solution solution = new Solution();
//        System.out.println(solution.missingNumber(nums));
//        System.out.println(solution.hashMapSolution(nums));
//        System.out.println(solution.yihuoSolution(nums));
        System.out.println(solution.mathSolution(nums));
    }
}
