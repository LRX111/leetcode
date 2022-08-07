package Internal_Sorting;

import java.util.Arrays;

public class DirectInsert {
    public void solution(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int t = nums[i];
            int j = i - 1;
            while (j >= 0 && t < nums[j]) {
                nums[j + 1] = nums[j--];
            }
            nums[j + 1] = t;
        }
    }

    public static void main(String[] args) {
        int[] n = {45, 34, 78, 12, 34, 32, 29, 64};
        DirectInsert directInsert = new DirectInsert();
        directInsert.solution(n);
        System.out.println(Arrays.toString(n));
    }
}
