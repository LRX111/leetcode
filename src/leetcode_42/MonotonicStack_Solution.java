package leetcode_42;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MonotonicStack_Solution {
    public int trap(int[] height) {
        int ans = 0;
        List<Integer> stack = new LinkedList<>();//栈顶元素代表盛水平台的索引
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty()) {
                ((LinkedList<Integer>) stack).push(i);
            } else {
                //如果平台低于右边界,则当前有边界也许能盛水
                while (!stack.isEmpty() && height[((LinkedList<Integer>) stack).getFirst()] < height[i]) {
                    int top = ((LinkedList<Integer>) stack).pop();
                    //如果左边界高于平台高度
                    if (!stack.isEmpty() && height[((LinkedList<Integer>) stack).getFirst()] > height[top]) {
                        int leftSide = ((LinkedList<Integer>) stack).getFirst();
                        ans += (i - leftSide - 1) * (Math.min(height[leftSide], height[i]) - height[top]);
                    }
                    //否则仅有左边边界高度等于平台高度，或栈为空
                }
                ((LinkedList<Integer>) stack).push(i);
            }
        }
        return ans;
    }

    /**
     * 官方写法的单调栈方法
     *
     * @param height
     * @return
     */
    public int OfficalAns_trap(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = {4, 2, 0, 3, 2, 5};
        MonotonicStack_Solution solution = new MonotonicStack_Solution();
        System.out.println(solution.trap(height));
    }
}
