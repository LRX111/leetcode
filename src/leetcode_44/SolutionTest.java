package leetcode_44;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SolutionTest {
    @Test
    public void testxinghao(){
        String s = "adceb";
        String p = "*a*b";
        Solution solution=new Solution();
        Assert.assertEquals(solution.isMatch(s,p),true);
    }
    @Test
    public void testwenhao(){
        String s = "cb";
        String p = "?a";
        Solution solution=new Solution();
        Assert.assertEquals(solution.isMatch(s,p),false);
    }
    @Test
    public void test1(){
        String s = "acdcb";
        String p = "a*c?b";
        Solution solution=new Solution();
        Assert.assertEquals(solution.isMatch(s,p),false);
    }
    @Test
    public void test2(){
        String s = "aa";
        String p = "*";
        Solution solution=new Solution();
        Assert.assertEquals(solution.isMatch(s,p),true);
    }
    @Test
    public void test3(){
        String s="abaabaaaabbabbaaabaabababbaabaabbabaaaaabababbababaabbabaabbbbaabbbbbbbabaaabbaaaaabbaabbbaaaaabbbabb";
        String p="ab*aaba**abbaaaa**b*b****aa***a*b**ba*a**ba*baaa*b*ab*";
        Solution solution=new Solution();
        Assert.assertEquals(solution.isMatch(s,p),false);

    }
    @Test
    public void test4(){
        String s="b";
        String p="?";
        Solution solution=new Solution();
        Assert.assertEquals(solution.isMatch(s,p),true);
    }
    @Test
    public void test5(){
        String s="bbbaaabbaababbabbbaababaabaaabbaaaababababbbabaaababb";
        String p="bab*bbb*b********aa*ab";
        Solution solution=new Solution();
        Assert.assertEquals(solution.isMatch(s,p),false);
    }
    @Test
    public void test6(){
        String s="";
        String p="";
        Solution solution=new Solution();
        Assert.assertEquals(solution.isMatch(s,p),true);
    }
}
