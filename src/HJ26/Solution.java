package HJ26;

import java.util.*;

public class Solution {
    /**
     * 插入排序
     *
     * @param str
     * @return
     */
    public static String paixu(String str) {
        StringBuilder sb = new StringBuilder();
        List<Map<Character, Integer>> list = new ArrayList<>();
        for (int index = 0; index < str.length(); index++) {
            Character a = str.charAt(index);
            if (!(a >= 'A' && a <= 'Z' || a >= 'a' && a <= 'z')) {
                Map<Character, Integer> map = new HashMap<>();
                map.put(a, index);
                list.add(map);
                continue;
            }
            Character acompare = a <= 'Z' ? a : (char) (a - 32);
            int i = 0;
            for (; i < sb.length(); i++) {
                Character b = sb.charAt(i);
                Character bcompare = b <= 'Z' ? b : (char) (b - 32);
                if (bcompare > acompare) {
                    sb.append(" ");
                    for (int j = sb.length() - 2; j >= i; j--) {
                        sb.setCharAt(j + 1, sb.charAt(j));
                    }
                    sb.setCharAt(i, a);
                    break;
                }
            }
            if (i == sb.length()) {
                sb.append(a);
            }
        }

        for (Map<Character, Integer> map : list) {
            Set<Character> characters = map.keySet();
            Iterator<Character> iterator = characters.iterator();
            while (iterator.hasNext()) {
                sb.append(" ");
                Character ch = iterator.next();
                int index = map.get(ch);
                for (int i = sb.length() - 2; i >= index; i--) {
                    sb.setCharAt(i + 1, sb.charAt(i));
                }
                sb.setCharAt(index, ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println('Z' - 'a');
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        scanner.close();
        System.out.println(paixu(str));
    }
}
