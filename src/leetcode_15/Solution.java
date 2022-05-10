package leetcode_15;

import java.util.*;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) return ans;
        //暴力O(n^3)
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                for (int k = j + 1; k < nums.length; k++) {
//                    if (i != j && i != k && j != k) {
//                        if ((nums[i] + nums[j] + nums[k]) == 0) {
//                            List<Integer> triple = new ArrayList<>();
//                            triple.add(nums[i]);
//                            triple.add(nums[j]);
//                            triple.add(nums[k]);
//                            maopao(triple);
//                            if (ans.contains(triple) != true) {
//                                ans.add(triple);
//                            }
//                        }
//                    }
//                }
//            }
//        }
        //好像是O(n^2)
        //符合目标值相反数的两数之和的下标列表
        List<List<Integer>> twoSumList;
        List<Integer> zuhe;
        for (int i = 2; i < nums.length; i++) {
            twoSumList = twoSum1(nums, i, 0 - nums[i]);
            Iterator<List<Integer>> it = twoSumList.iterator();
            while (it.hasNext()) {
                zuhe = it.next();
                zuhe.add(nums[i]);
                maopao(zuhe);
                if (ans.contains(zuhe) != true)
                    ans.add(zuhe);
            }
        }
        return ans;
    }

    private List<List<Integer>> twoSum1(int[] nums, int endIndex, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < endIndex; i++) {
            if (set.contains(target - nums[i])) {
                List<Integer> zuhe = new ArrayList<>();
                zuhe.add(target - nums[i]);
                zuhe.add(nums[i]);
                if (ans.contains(zuhe) != true)
                    ans.add(zuhe);
            }
            set.add(nums[i]);
        }
        return ans;
    }

    private List<List<Integer>> twoSum(int[] nums, int endIndex, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> indexes;//用于存放map的Value的临时变量
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < endIndex; i++) {
            if (map.containsKey(target - nums[i])) {
                //如果map存在数x，使x+nums[i]==target，则将x的每一个下标与i组合存入ans(x的下标不能等于i)
                indexes = map.get(target - nums[i]);
                Iterator<Integer> iterator = indexes.iterator();
                while (iterator.hasNext()) {
                    List<Integer> zuhe = new ArrayList<>();
                    zuhe.add(iterator.next());
                    zuhe.add(i);
                    ans.add(zuhe);
                }
            }
            //后将nums[i]与下表i存入map
            if (map.containsKey(nums[i])) {
                indexes = map.get(nums[i]);
                indexes.add(i);
                map.put(nums[i], indexes);
            } else {
                indexes = new ArrayList<>();
                indexes.add(i);
                map.put(nums[i], indexes);
            }
        }
        return ans;
    }

    private void maopao(List<Integer> list) {
        Integer t = 0;
        boolean fl = true;
        for (int j = list.size(); j > 0; j--) {
            for (int i = 1; i < j; i++) {
                if (list.get(i - 1) > list.get(i)) {
                    t = list.get(i - 1);
                    list.set(i - 1, list.get(i));
                    list.set(i, t);
                    fl = false;
                } else fl = true;
            }
            if (fl == true) break;
        }
    }

    public static void main(String[] args) {
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入整数个数：");
        if (scanner.hasNextInt()) {
            n = scanner.nextInt();
        }
        int[] nums = new int[n];
        System.out.println("输入整数数组：");
        for (int i = 0; i < n; i++) {
            if (scanner.hasNextInt())
                nums[i] = scanner.nextInt();
        }
        scanner.close();
        System.out.println("所有和为0的不重复三元组为：" + new Solution().threeSum(nums));
        System.out.println("所有和为0的两数之和的下标为：" + new Solution().twoSum(nums, nums.length, 0));
        System.out.println("所有和为0的两数之和的数为：" + new Solution().twoSum1(nums, nums.length, 0));
        /*
        6
        -1 0 1 2 -1 -4
         */
    }

}
