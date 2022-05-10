package leetcode_17;

import java.io.StringReader;
import java.util.*;

public class Solution {
    /**
     * 自己瞎想的广度优先遍历方法
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> ans;
        List<Character> chList;
        Map<Integer, List<Character>> num2char = new HashMap<>();
        int count;
        int num = 2;
        for (int i = 'a'; num <= 9; ) {
            if (num == 7) {
                chList = new ArrayList<>(4);
                for (count = 0; count < 4; count++) {
                    chList.add((char) i);
                    i++;
                }
                num2char.put(num, chList);
            } else if (num == 9) {
                chList = new ArrayList<>(4);
                for (count = 0; count < 4; count++) {

                    chList.add((char) i);
                    i++;
                }
                num2char.put(num, chList);
            } else {
                chList = new ArrayList<>(3);
                for (count = 0; count < 3; count++) {

                    chList.add((char) i);
                    i++;
                }
                num2char.put(num, chList);
            }
            num++;
        }
//        System.out.println(num2char);
        ans = new LinkedList<>();
        if (digits.length() == 0) return ans;
        else {
            chList = num2char.get(Integer.parseInt(String.valueOf(digits.charAt(0))));
//            System.out.println(Integer.parseInt(String.valueOf(digits.charAt(0))));
            for (Character character : chList) {
                ans.add(character.toString());
            }
        }
        for (int i = 1; i < digits.length(); i++) {
            int ans_size = ans.size();
            chList = num2char.get(Integer.parseInt(String.valueOf(digits.charAt(i))));
            for (int j = 0; j < ans_size; j++) {

                for (Character character : chList) {
                    String s = ans.get(0);
                    s += character;
                    ans.add(s);
                }
                ((LinkedList<String>) ans).removeFirst();
            }
        }
        return ans;
    }

    /**
     * 所谓“当题目中出现 ‘所有组合’ 等类似字眼时，我们第一感觉就要想到用回溯。”
     * 深度优先遍历方法
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations_dps(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) return ans;
        String[] num2character = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dps(digits, new StringBuilder(), ans, num2character);
        return ans;
    }

    private void dps(String digits, StringBuilder path, List<String> ans, String[] num2character) {
        if (path.length() == digits.length()) {
            ans.add(path.toString());
            return;
        }
        int current_num = digits.charAt(path.length()) - '0';
        for (int i = 0; i < num2character[current_num].length(); i++) {
            path.append(num2character[current_num].charAt(i));
            dps(digits, path, ans, num2character);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = "";
        System.out.println("输入2~9内的数字：");
        if (sc.hasNext()) {
            digits = sc.next();
        }
        sc.close();
        System.out.println("所有能表示的字母组合：" + new Solution().letterCombinations(digits));
        System.out.println("dps方法：" + new Solution().letterCombinations_dps(digits));

    }
}
