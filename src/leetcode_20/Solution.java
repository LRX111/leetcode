package leetcode_20;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {
    public boolean isValid(String s) {
//        boolean ans = false;
        List<Character> stack = new LinkedList<>();
//        Map<Character, Character> kuohao_map = new HashMap<>();
//        kuohao_map.put(')', '(');
//        kuohao_map.put(']', '[');
//        kuohao_map.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                ((LinkedList<Character>) stack).addFirst(')');
            } else if (s.charAt(i) == '[') {
                ((LinkedList<Character>) stack).addFirst(']');
            } else if (s.charAt(i) == '{') {
                ((LinkedList<Character>) stack).addFirst('}');
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) return false;
                else if (((LinkedList<Character>) stack).removeFirst() != ')')
                    return false;
            } else if (s.charAt(i) == ']') {
                if (stack.isEmpty()) return false;
                else if (((LinkedList<Character>) stack).removeFirst() != ']')
                    return false;
            } else if (s.charAt(i) == '}') {
                if (stack.isEmpty()) return false;
                else if (((LinkedList<Character>) stack).removeFirst() != '}')
                    return false;
            }
        }
        if (stack.isEmpty()) return true;
        else return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("([)]"));
    }
}
