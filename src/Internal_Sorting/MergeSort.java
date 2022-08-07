package Internal_Sorting;

import java.util.Arrays;

public class MergeSort {
    public void solution(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) >> 1;
        solution(nums, left, mid);
        solution(nums, mid + 1, right);
        //将left~mid和mid+1~right两段合并
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] copy_nums = new int[right - left + 1];
        for (int i = 0; i < copy_nums.length; i++) {
            copy_nums[i] = nums[left + i];
        }
        int index1 = 0;
        int index2 = mid - left + 1;
        int midBoarder = index2;
        int index = left;
        while (index1 < midBoarder && index2 < copy_nums.length) {
            if (copy_nums[index1] <= copy_nums[index2]) {
                nums[index++] = copy_nums[index1++];
            } else {
                nums[index++] = copy_nums[index2++];
            }
        }
        if (index1 == midBoarder) {
            for (int i = index2; i < copy_nums.length; i++) {
                nums[index++] = copy_nums[i];
            }
        } else {
            for (int i = index1; i < midBoarder; i++) {
                nums[index++] = copy_nums[i];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {25, 34, 45, 32, 78, 12, 34, 64, 5, 48, 78, 78, 7, 45, 8, 5};
        MergeSort mergeSort = new MergeSort();
        mergeSort.solution(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
