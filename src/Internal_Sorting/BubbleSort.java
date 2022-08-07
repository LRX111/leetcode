package Internal_Sorting;

import java.io.BufferedInputStream;
import java.util.Arrays;

public class BubbleSort {
    public void solution(int[] nums) {
        boolean noSwap = true;
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    noSwap = false;
                }
            }
            if (noSwap) break;
        }
    }

    public static void main(String[] args) {
        int[] nums = {45, 34};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.solution(nums);
        System.out.println(Arrays.toString(nums));
    }
}
