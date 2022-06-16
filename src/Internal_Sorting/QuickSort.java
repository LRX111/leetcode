package Internal_Sorting;

import java.util.Arrays;

public class QuickSort {
    /**
     * 选出轴值，把小于轴值的数放在左边，大于轴值的数放在右边，对左右两边的序列进行递归
     *
     * @param nums
     * @param left
     * @param right
     */
    public void solution(int[] nums, int left, int right) {
        if (left >= right) return;
        int pivot = selectPivot(left, right);
        int temp = nums[pivot];
        nums[pivot] = nums[right];
        nums[right] = temp;
        pivot = partition(nums, left, right);
        solution(nums, left, pivot - 1);
        solution(nums, pivot + 1, right);
    }

    private int selectPivot(int left, int right) {
        return (left + right) >> 1;
    }

    private int partition(int[] nums, int left, int right) {
        int pivotVal = nums[right];
        while (left < right) {
            while (nums[left] <= pivotVal && left < right) {
                left++;
            }
            if (left < right) {
                nums[right] = nums[left];
                right--;
            }
            while (nums[right] >= pivotVal && left < right) {
                right--;
            }
            if (left < right) {
                nums[left] = nums[right];
                left++;
            }
        }
        nums[left] = pivotVal;
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {25, 34, 45, 32, 34, 12, 29, 64, 5, 7, 87, 8, 787, 8, 54, 5};
        QuickSort quickSort = new QuickSort();
        quickSort.solution(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
