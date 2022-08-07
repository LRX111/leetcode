package Internal_Sorting;

import java.util.Arrays;

public class CountSort {
    public void solution(int[] nums, int min, int max) {
        int[] count = new int[max - min + 1];
        int[] tempArray = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] - min]++;
            tempArray[i] = nums[i];
        }
        //再处理一遍count数组，使得count[i]表示数（i+min）


    }

    public static void main(String[] args) {
        int[] nums = {7, 3, 8, 9, 6, 1, 8, 1, 2, -1};
        CountSort countSort = new CountSort();
        countSort.solution(nums, -1, 9);
        System.out.println(Arrays.toString(nums));
    }
}
