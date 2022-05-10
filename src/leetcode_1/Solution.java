package leetcode_1;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    //    public int[] twoSum(int[] nums, int target) {
//        int[] result = new int[2];
//        flag:
//        for (int i = 0; i < nums.length; ++i) {
//            for (int j = i + 1; j < nums.length; ++j) {
//                if ((nums[i] + nums[j]) == target) {
//                    result[0] = i;
//                    result[1] = j;
//                    break flag;
//                }
//            }
//        }
//        return result;
//    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-solution/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] t = {3, 2, 4};
        int[] result = s.twoSum(t, 6);
        System.out.print(result[0]);
        System.out.print(result[1]);
    }
}
