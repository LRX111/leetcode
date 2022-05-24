package leetcode_40;

import java.util.*;

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> myset = new HashSet<>();
        Arrays.sort(candidates);
        for (int i = 0; i < candidates.length; i++) {
            if (myset.contains(candidates[i])) continue;
            myset.add(candidates[i]);
            if (candidates[i] == target) {
                List<Integer> temp = new ArrayList<>();
                temp.add(candidates[i]);
                ans.add(temp);
            } else if (candidates[i] < target) {
                List<List<Integer>> subans = solvesubans(candidates, target - candidates[i], i + 1);
                if (subans != null) {
                    for (List<Integer> suban : subans) {
                        suban.add(candidates[i]);
                    }
                    ans.addAll(subans);
                }
            }
        }
        return ans;
    }

    private List<List<Integer>> solvesubans(int[] candidates, int target, int current) {
        if (current > candidates.length) return null;
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> myset = new HashSet<>();
        for (int i = current; i < candidates.length; i++) {
            if (myset.contains(candidates[i])) continue;
            myset.add(candidates[i]);
            if (target == candidates[i]) {
                List<Integer> temp = new ArrayList<>();
                temp.add(candidates[i]);
                ans.add(temp);
            } else if (candidates[i] < target) {
                List<List<Integer>> subans = solvesubans(candidates, target - candidates[i], i + 1);
                if (subans != null) {
                    for (List<Integer> suban : subans) {
                        suban.add(candidates[i]);
                    }
                    ans.addAll(subans);
                }
            }
        }
        return ans.size() == 0 ? null : ans;
    }

    public static void main(String[] args) {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        Solution solution = new Solution();
        System.out.println(solution.combinationSum2(candidates, target));
    }
}
