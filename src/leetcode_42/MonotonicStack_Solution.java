package leetcode_42;

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

    public static void main(String[] args) {
        int[] height = {4, 2, 0, 3, 2, 5};
        MonotonicStack_Solution solution = new MonotonicStack_Solution();
        System.out.println(solution.trap(height));
    }
}
