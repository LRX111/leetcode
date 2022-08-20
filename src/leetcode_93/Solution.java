package leetcode_93;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

class Solution {
    private List<String> result = new ArrayList();
    private List<String> path = new LinkedList<String>();
    private int start = 0;

    private boolean isValid(String s) {
        return true;
    }

    private void tryAddress(String s) {
        if (path.size() == 4) {
            String substring = s.substring(start, s.length() - 1);
            if (isValid(substring)) {
                StringBuilder sb = new StringBuilder();
                for (String s1 : path) {
                    sb.append(s1 + ".");
                }
                sb.append(substring);
                result.add(sb.toString());
            }
            return;
        }
        for (int i = 1; i <= 3; i++) {
            String substring = s.substring(start, start + i);
            if (isValid(substring)) {
                path.add(substring);
                start += i;
                tryAddress(s);
                ((LinkedList<String>) path).removeLast();
                start -= i;
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {

    }
}