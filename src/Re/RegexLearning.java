package Re;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexLearning {
    public boolean match(String regex, String str) {
        return str.matches(regex);
    }

    public String[] catchGroupStrings(String regex, String str) {
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(str);
        String[] ans = new String[m.groupCount() + 1];
        if (m.matches()) {
            for (int i = 0; i < ans.length; i++) {
                ans[i] = m.group(i);
            }
        }
        return ans;
    }

    public String[] splitString(String regex, String str) {
        return str.split(regex);
    }

    public List<String> searchSubStrings(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        List<String> ans = new ArrayList<>();
        while (matcher.find()) {
            ans.add(str.substring(matcher.start(), matcher.end()));
        }
        return ans;
    }

    public String replace(String regex, String content, String replaceStr) {
        return content.replaceAll(regex, replaceStr);
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+(\\d+)");
        Matcher matcher = pattern.matcher("ads1008 BX45 C8 78 FG546D");
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
        }
    }
}
