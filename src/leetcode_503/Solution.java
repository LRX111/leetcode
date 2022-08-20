package leetcode_503;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Deque<Integer> stack = new LinkedList<>();
        int index = 2 * nums.length - 1;
        while (index >= 0) {
            //栈顶元素就是第一个比nums[index]大的元素
            while (!stack.isEmpty() && stack.peek() <= nums[index % nums.length]) {
                stack.pop();
            }
            res[index % nums.length] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[index % nums.length]);
            index--;
        }
        return res;
    }
}
