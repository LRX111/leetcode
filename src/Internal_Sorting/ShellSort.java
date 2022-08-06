package Internal_Sorting;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShellSort {
    /**
     * 增量序列为[1，3，9，……，3^k]的希尔排序
     *
     * @param nums
     */
    public void solution(int[] nums) {
        int delta = nums.length - (nums.length % 3);
        for (; delta > 0; delta /= 3) {
            for (int i = 0; i < delta; i++) {
                subSort(nums, i, delta);
            }
        }
        if (delta != 1) {
            subSort(nums, 0, 1);
        }
    }

    private void subSort(int[] nums, int start, int delta) {
        for (int i = start + delta; i < nums.length; i += delta) {
            int temp = nums[i];
            int j = i - delta;
            while (j >= 0 && temp < nums[j]) {
                nums[j + delta] = nums[j];
                j -= delta;
            }
            nums[j + delta] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {45, 34, 23, 78, 12, 34, 32, 29, 64};
        ShellSort ss = new ShellSort();
        ss.solution(nums);
        System.out.println(Arrays.toString(nums));
    }
}
