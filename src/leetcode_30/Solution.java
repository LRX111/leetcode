package leetcode_30;

import java.io.*;
import java.util.*;

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new LinkedList<>();
        List<String> subStrList = new ArrayList<>();
        LinkedList<String> wordslist = new LinkedList<>(Arrays.asList(words));
        genSubStrList(subStrList, wordslist, "");
//        System.out.println(subStrList);
        List<Integer> firstPosition;
        for (String s1 : subStrList) {
            firstPosition = kmp(s, s1);
            if (firstPosition != null) {
                for (Integer integer : firstPosition) {
                    if (!ans.contains(integer))
                        ((LinkedList<Integer>) ans).offer(integer);
                }
            }
        }
        return ans;
    }

    private void genSubStrList(List<String> subStrList, LinkedList<String> wordslist, String path) {
        if (wordslist.size() == 1) {
            String t = wordslist.peekFirst();
            if (!subStrList.contains(path + t))
                subStrList.add(path + t);
            return;
        }
        for (int i = 0; i < wordslist.size(); i++) {
            String temp = wordslist.pollFirst();
            genSubStrList(subStrList, wordslist, path + temp);
            wordslist.offerLast(temp);
        }
    }

    private List<Integer> kmp(String s, String substr) {
        if (s.length() < substr.length()) return null;
        int[] next = getNext(substr);
        if (next == null) return null;
        int s_p = 0;
        int sub_p = 0;
        List<Integer> ans = new LinkedList<>();
        while (s_p < s.length() && sub_p < substr.length()) {
            if (s.charAt(s_p) == substr.charAt(sub_p)) {
                s_p++;
                sub_p++;
            } else if (sub_p == 0) s_p++;
            else {
                sub_p = next[sub_p];
            }
        }
        if (sub_p == substr.length()) {
            ans.add(s_p - sub_p);
            List<Integer> t = kmp(s.substring(s_p - sub_p + 1), substr);
            if (t != null) {
                for (int i = 0; i < t.size(); i++) {
                    t.set(i, t.get(i) + s_p - sub_p + 1);
                }
                ans.addAll(t);
            }
            return ans;
        } else return null;
//        return sub_p == substr.length() ? s_p - sub_p : -1;
    }

    private int[] getNext(String substr) {
        if (substr.length() == 0) return null;
        int[] next = new int[substr.length()];
        next[0] = -1;
        if (substr.length() == 1) return next;
        next[1] = 0;
        if (substr.length() == 2) return next;
        int cn = 0;//初始代表next[1] = 0中的0;
        for (int i = 2; i < substr.length(); ) {
            if (substr.charAt(i - 1) == substr.charAt(cn)) next[i++] = ++cn;
            else if (cn == 0) next[i++] = 0;
            else cn = next[cn];
        }
        return next;
    }

    /**
     * 滑动窗口写法
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> huadongchaungkou(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int wordLen = words[0].length();
        int windowLen = words.length * wordLen;
        Map<String, List<Integer>> words_map = new HashMap<>();
        List<Integer> wordIndexList;
        for (int i = 0; i < words.length; i++) {
            if (words_map.containsKey(words[i]))
                wordIndexList = words_map.get(words[i]);
            else wordIndexList = new ArrayList<>();
            wordIndexList.add(i);
            words_map.put(words[i], wordIndexList);
        }
        String window, word;
        for (int left = 0; left + windowLen - 1 < s.length(); left++) {
            window = s.substring(left, left + windowLen);
            Map<String, List<Integer>> copy_words_map = copySerializableObject((HashMap) words_map);
            for (int i = 0; i < window.length(); i += wordLen) {
                word = window.substring(i, i + wordLen);
                if (copy_words_map.containsKey(word) && copy_words_map.get(word).size() >= 1) {
                    if (copy_words_map.get(word).size() == 1) copy_words_map.remove(word);
                    else copy_words_map.get(word).remove(0);
                } else break;
            }
            if (copy_words_map.size() == 0) {
                ans.add(left);
            }
        }
        return ans;
    }

    private <T extends Serializable> T copySerializableObject(T obj) {
        T copy_obj;
        try {
            //1、创建ByteArrayOutputStream，将数据可以转换成字节
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            //2、创建ObjectOutputStream，关联创建ByteArrayOutputStream
            ObjectOutputStream out = new ObjectOutputStream(bout);
            //3.使用ObjectOutputStream的writeObject()方法，读取要复制的对象
            out.writeObject(obj);
            //4.使用ByteArrayInputStream读取ByteArrayOutputStream的转换的对象字节数据
            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            //5、创建ObjectInputStream读取对象字节数据，创建新的对象
            ObjectInputStream in = new ObjectInputStream(bin);
            copy_obj = (T) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return copy_obj;
    }

    /**
     * 滑动窗口不用克隆
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> huadongchaungkou2(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || words == null || s.length() == 0 || words.length == 0) return ans;
        int wordLen = words[0].length();
        int windowLen = words.length * wordLen;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int left = 0; left + windowLen - 1 < s.length(); left++) {
            int count = 0;
            Map<String, Integer> currentMap = new HashMap<>();
            String window = s.substring(left, left + windowLen);
            for (int i = 0; i < window.length(); i += wordLen) {
                String word = window.substring(i, i + wordLen);
                if (map.containsKey(word) && currentMap.getOrDefault(word, 0) < map.get(word)) {
                    count++;
                    currentMap.put(word, currentMap.getOrDefault(word, 0) + 1);
                } else break;
            }
            if (count == words.length) ans.add(left);
        }
        return ans;
    }

    /**
     * 滑动窗口优化方法
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> huadongchuangkouPlus(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || words == null || s.length() == 0 || words.length == 0 || words[0].length() == 0) return ans;
        int wordLen = words[0].length();
        int windowLen = words.length * wordLen;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int began = 0; began < wordLen; began++) {
            for (int left = began; left + windowLen - 1 < s.length(); ) {
                Map<String, Integer> map2 = new HashMap<>();
                int count = 0;
                String window = s.substring(left, left + windowLen);
                int index = 0;
                String currentWord = "";
                for (; index < windowLen; index += wordLen) {
                    currentWord = window.substring(index, index + wordLen);
                    if (map.containsKey(currentWord) &&
                            map2.getOrDefault(currentWord, 0) < map.get(currentWord)) {
                        count++;
                        map2.put(currentWord, map2.getOrDefault(currentWord, 0) + 1);
                    } else break;
                }
                if (count == words.length) {//里层循环完美执行完时
                    ans.add(left);
                    left += wordLen;
                } else if (!map.containsKey(currentWord)) {//里层循环因map.containsKey(currentWord)跳出时
                    left += index + wordLen;
                } else {//里层循环因map2.getOrDefault(currentWord, 0) < map.get(currentWord)跳出时
                    for (int i = 0; i < windowLen; i += wordLen) {
                        if (window.substring(i, i + wordLen).equals(currentWord)) {
                            left += i + wordLen;
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 别人的写法
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> bierendexiefa(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //将所有移动分成 wordLen 类情况
        for (int j = 0; j < wordLen; j++) {
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0; //记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
            //每次移动一个单词长度
            for (int i = j; i < s.length() - wordNum * wordLen + 1; i = i + wordLen) {
                boolean hasRemoved = false; //防止情况三移除后，情况一继续移除
                while (num < wordNum) {
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        //出现情况三，遇到了符合的单词，但是次数超了
                        if (hasWords.get(word) > allWords.get(word)) {
                            // hasWords.put(word, value);
                            hasRemoved = true;
                            int removeNum = 0;
                            //一直移除单词，直到次数符合了
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1; //加 1 是因为我们把当前单词加入到了 HashMap 2 中
                            i = i + (removeNum - 1) * wordLen; //这里依旧是考虑到了最外层的 for 循环，看情况二的解释
                            break;
                        }
                        //出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                        //只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                        //然后刚好就移动到了单词后边）
                    } else {
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == wordNum) {
                    res.add(i);
                }
                //出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num = num - 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{"word", "good", "best", "good"};
        Solution solution = new Solution();
//        System.out.println(solution.findSubstring(s, words));
//        System.out.println(solution.huadongchaungkou(s, words));
//        System.out.println(solution.huadongchaungkou2(s, words));
        System.out.println(solution.bierendexiefa(s, words));
    }
}
