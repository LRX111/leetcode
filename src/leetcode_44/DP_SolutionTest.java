package leetcode_44;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DP_SolutionTest {
    private ReferenceAnswer reAns = new ReferenceAnswer();

    @Test
    public void testxinghao() {
        String s = "adceb";
        String p = "*a*b";
        DP_Solution solution = new DP_Solution();
        Assert.assertEquals(solution.isMatch(s, p), reAns.isMatch(s, p));
    }

    @Test
    public void testwenhao() {
        String s = "cb";
        String p = "?a";
        DP_Solution solution = new DP_Solution();
        Assert.assertEquals(solution.isMatch(s, p), reAns.isMatch(s, p));
    }

    @Test
    public void test1() {
        String s = "acdcb";
        String p = "a*c?b";
        DP_Solution solution = new DP_Solution();
        Assert.assertEquals(solution.isMatch(s, p), reAns.isMatch(s, p));
    }

    @Test
    public void test2() {
        String s = "aa";
        String p = "*";
        DP_Solution solution = new DP_Solution();
        Assert.assertEquals(solution.isMatch(s, p), reAns.isMatch(s, p));
    }

    @Test
    public void test3() {
        String s = "abaabaaaabbabbaaabaabababbaabaabbabaaaaabababbababaabbabaabbbbaabbbbbbbabaaabbaaaaabbaabbbaaaaabbbabb";
        String p = "ab*aaba**abbaaaa**b*b****aa***a*b**ba*a**ba*baaa*b*ab*";
        DP_Solution solution = new DP_Solution();
        Assert.assertEquals(solution.isMatch(s, p), reAns.isMatch(s, p));

    }

    @Test
    public void test4() {
        String s = "b";
        String p = "?";
        DP_Solution solution = new DP_Solution();
        Assert.assertEquals(solution.isMatch(s, p), reAns.isMatch(s, p));
    }

    @Test
    public void test5() {
        String s = "bbbaaabbaababbabbbaababaabaaabbaaaababababbbabaaababb";
        String p = "bab*bbb*b********aa*ab";
        DP_Solution solution = new DP_Solution();
        Assert.assertEquals(solution.isMatch(s, p), reAns.isMatch(s, p));
    }

    @Test
    public void test6() {
        String s = "";
        String p = "";
        DP_Solution solution = new DP_Solution();
        Assert.assertEquals(solution.isMatch(s, p), reAns.isMatch(s, p));
    }
}
