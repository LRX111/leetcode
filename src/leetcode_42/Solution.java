package leetcode_42;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        List<int[]> AOTypeCouples = new LinkedList<>();
        while (left < height.length - 2) {
            while (left < right - 1) {
                //探索以left开始有无凹形结构，若有则记录并left定位至right继续枚举并跳出，若无则将left向后移动
                if (isAOType(height, left, right)) {
                    //如果是凹型结构，则记录这个结构,并将left指向right,并重置right
                    AOTypeCouples.add(new int[]{left, right});
                    left = right;
                    break;
                } else {
                    //如果不是凹形结构，则将右指针-1枚举下一个
                    right--;
                }
            }
            //若left没被重定位过
            if (left != right) left++;
            right = height.length - 1;
        }
        for (int[] aoTypeCouple : AOTypeCouples) {
            ans += AreaOfAOType(height, aoTypeCouple[0], aoTypeCouple[1]);
        }
        return ans;
    }

    private boolean isAOType(int[] height, int left, int right) {
        if (left + 1 == right) return false;
        int maxlen = Math.min(height[left], height[right]);
        for (int i = left + 1; i < right; i++) {
            if (height[i] >= maxlen) return false;
        }
        return true;
    }

    private int AreaOfAOType(int[] height, int left, int right) {
        int len = right - left - 1;
        int hei = Math.min(height[left], height[right]);
        int subblock = 0;
        for (int i = left + 1; i < right; i++) {
            if (height[i] <= hei)
                subblock += height[i];
            else
                len--;
        }
        return len * hei - subblock;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Solution solution = new Solution();
        System.out.println(solution.trap(height));
//        int[] height2={0,1,0,2,1,0,1,3,2,1,2,1};
//        System.out.println(solution.trap(height2));
    }
}
