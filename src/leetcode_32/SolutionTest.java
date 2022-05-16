package leetcode_32;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SolutionTest {
    private Solution solution;

    @BeforeTest
    public void init() {
        solution = new Solution();
    }

    @Test
    public void test_longestValidParentheses() {
        String str = "))))())()()(()";
        Assert.assertEquals(solution.dp_longestValidParentheses(str), 4);
    }

    @Test
    public void test2_longestValidParentheses() {
        String str = "(()";
        Assert.assertEquals(solution.dp_longestValidParentheses(str), 2);
    }

}
