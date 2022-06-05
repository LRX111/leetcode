package Re;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegexLearningTest {
    @Test
    public void testmatch() {
        String patten = "^\\d{3,4}\\-[1-9]\\d{5,7}";
        RegexLearning regexLearning = new RegexLearning();
        Assert.assertTrue(regexLearning.match(patten, "010-123456"));
        Assert.assertTrue(regexLearning.match(patten, "010-12345678"));
        Assert.assertTrue(regexLearning.match(patten, "0123-123456"));
        Assert.assertTrue(regexLearning.match(patten, "0123-12345678"));
        Assert.assertTrue(regexLearning.match(patten, "123-12345678"));

        Assert.assertFalse(regexLearning.match(patten, "123-0123456"));
        Assert.assertFalse(regexLearning.match(patten, "010#12345678"));
        Assert.assertFalse(regexLearning.match(patten, "010X12345678"));
        Assert.assertFalse(regexLearning.match(patten, "01-12345678"));
        Assert.assertFalse(regexLearning.match(patten, "01234-12345678"));
        Assert.assertFalse(regexLearning.match(patten, "01A-12345678"));
        Assert.assertFalse(regexLearning.match(patten, "0123-12345"));
        Assert.assertFalse(regexLearning.match(patten, "0123-12345X"));
        Assert.assertFalse(regexLearning.match(patten, "0123-123456789"));
        Assert.assertFalse(regexLearning.match(patten, "0123-12345678X"));
    }

    @Test
    public void testcatchGroupStrings() {
        RegexLearning regexLearning = new RegexLearning();
        String pattern = "(\\D*)(\\d+)(.*)";
        String content = "This order was placed for QT3000! OK?";
        String[] ans = {"This order was placed for QT3000! OK?",
                "This order was placed for QT",
                "3000",
                "! OK?"};
        Assert.assertEquals(regexLearning.catchGroupStrings(pattern, content), ans);
    }

    @Test
    public void testsplitString() {
        RegexLearning regexLearning = new RegexLearning();
        String pattern = "\\s+";
        String content = "java php  python";
        String[] ans = {"java", "php", "python"};
        Assert.assertEquals(regexLearning.splitString(pattern, content), ans);
    }

    @Test
    public void testsearchSubStrings() {
        RegexLearning regexLearning = new RegexLearning();
        String pattern = "the|The";
        String content = "The quick brown fox jump over the lazy dog.";
//        List<String> ans = new ArrayList<>() {
//            {
//                add("1");
//            }
//        };
        List<String> ans = Arrays.asList("The", "the");
        Assert.assertEquals(regexLearning.searchSubStrings(pattern, content), ans);
        pattern = "\\w+";
        ans = Arrays.asList("The", "quick", "brown", "fox", "jump", "over", "the", "lazy", "dog");
        Assert.assertEquals(regexLearning.searchSubStrings(pattern, content), ans);
    }

    @Test
    public void testreplace() {
        RegexLearning regexLearning = new RegexLearning();
        String pattern = "\\s+";
        String content = "The   quick   brown  fox  jump  over  the  lazy  dog.";
        String ans = "The quick brown fox jump over the lazy dog.";
        Assert.assertEquals(regexLearning.replace(pattern, content, " "), ans);
        pattern = "(\\w+)\\s*";
        ans = "<b>The</b> <b>quick</b> <b>brown</b> <b>fox</b> <b>jump</b> <b>over</b> <b>the</b> " +
                "<b>lazy</b> <b>dog</b> .";
        Assert.assertEquals(regexLearning.replace(pattern, content, "<b>$1</b> "), ans);
    }
}
