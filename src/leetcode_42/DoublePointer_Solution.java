package leetcode_42;

public class DoublePointer_Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxleftHeight = 0, maxrightHeight = 0;//左、右边界最大值只能增大不能减小
        int ans = 0;
        while (left < right) {
            maxleftHeight = maxleftHeight < height[left] ? height[left] : maxleftHeight;
            maxrightHeight = maxrightHeight < height[right] ? height[right] : maxrightHeight;
            if (height[left] < height[right]) {
                //left处的左边界最大值为maxleftHeight，且maxleftHeight一定小于未测定出的右边界最大值
                ans+=maxleftHeight-height[left];
                left++;
            }
            else {
                //right处的右边界最大值为maxrightHeight(不小于height[right])，未测出的左边界最大值只能大于等于height[right]
                ans+=maxrightHeight-height[right];
                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height={0,1,0,2,1,0,1,3,2,1,2,1};
        DoublePointer_Solution solution=new DoublePointer_Solution();
        System.out.println(solution.trap(height));
    }
}
