package leetcode_34;

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
    public void test_searchRange() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        Assert.assertEquals(solution.Real_searchRange(nums, target), new int[]{3, 4});
    }
}
