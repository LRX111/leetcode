package leetcode_39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        for (int i = candidates.length - 1; i >= 0; i--) {
            List<List<Integer>> partans=solveEachNum(candidates,target,i);
            if (partans!=null)
                ans.addAll(partans);
        }
        return ans;
    }

    /**
     * 仅返回由currentIndex处元素组成的答案
     * @param candidates
     * @param target
     * @param currentIndex
     * @return
     */
    private List<List<Integer>> solveEachNum(int[] candidates, int target, int currentIndex) {
        if (currentIndex < 0) return null;
        int sum = candidates[currentIndex];
        List<List<Integer>> thisans = new ArrayList<>();

        for (int count = 1; sum <= target; count++,sum+=candidates[currentIndex]) {
            if (sum == target) {
                List<Integer> subans = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    subans.add(candidates[currentIndex]);
                }
                thisans.add(subans);
                return thisans;
            } else {
                List<List<Integer>> partans=new ArrayList<>();
                for (int i = 0; i < currentIndex; i++) {
                    List<List<Integer>> temp = solveEachNum(candidates, target - sum, i);
                    if (temp!=null)
                        partans.addAll(temp);
                }
                if (partans != null) {
                    List<Integer> subans = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        subans.add(candidates[currentIndex]);
                    }
                    for (List<Integer> partan : partans) {
                        partan.addAll(subans);
                        thisans.add(partan);
                    }

                }
            }
        }
        //如果以当前currentIndex无法组成有效答案，返回null
        return thisans.size() == 0 ? null : thisans;
    }

    public static void main(String[] args) {
        int[] candidates = {3,5,8};
        int target = 11;
        Solution solution = new Solution();
        System.out.println(solution.combinationSum(candidates, target));
    }
}
