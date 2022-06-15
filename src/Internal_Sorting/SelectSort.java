package Internal_Sorting;

import java.util.Arrays;

public class SelectSort {
    public void solution(int[] nums) {
        int Smallest = 0;
        for (int i = 0; i < nums.length; i++) {
            Smallest = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[Smallest] > nums[j])
                    Smallest = j;
            }
            int temp = nums[i];
            nums[i] = nums[Smallest];
            nums[Smallest] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {45, 34, 78, 12, 34, 32, 29, 64};
        SelectSort selectSort = new SelectSort();
        selectSort.solution(nums);
        System.out.println(Arrays.toString(nums));
    }
}
