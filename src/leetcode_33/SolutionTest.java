package leetcode_33;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SolutionTest {
    Solution solution;

    @BeforeTest
    private void init() {
        solution = new Solution();
    }

    @Test
    public void test1_binaryS_search() {
        int[] nums = {1, 3};
        int target = 3;
        Assert.assertEquals(solution.binaryS_search(nums, target), 1);
    }

    @Test
    public void test2_binaryS_search() {
        int[] nums = {3, 1};
        int target = 1;
        Assert.assertEquals(solution.binaryS_search(nums, target), 1);
    }

    @Test
    public void test3_binaryS_search() {
        int[] nums = {3, 1};
        int target = 3;
        Assert.assertEquals(solution.binaryS_search(nums, target), 0);
    }

    @Test
    public void test4_binaryS_search() {
        int[] nums = {1, 3};
        int target = 0;
        Assert.assertEquals(solution.binaryS_search(nums, target), -1);
    }

    @Test
    public void test5_binaryS_search() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        Assert.assertEquals(solution.binaryS_search(nums, target), 4);
    }

    @Test
    public void test6_binaryS_search() {
        int[] nums = {1, 3, 5};
        int target = 5;
        Assert.assertEquals(solution.binaryS_search(nums, target), 2);
    }
}
