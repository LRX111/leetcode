package leetcode_1011;

public class Solution {
    int maxinweights = 0;

    public int shipWithinDays(int[] weights, int days) {
        int maxWei = sum(weights);
        int minWei = maxinweights;
        while (minWei < maxWei) {
            int midWei = (minWei + maxWei) >> 1;
            if (depart(weights, midWei) <= days) {
                maxWei = midWei;
            } else {
                minWei = midWei+1;
            }
        }
        return minWei;
    }

    private int sum(int[] weights) {
        int result = 0;
        for (int i = 0; i < weights.length; i++) {
            if (maxinweights < weights[i]) {
                maxinweights = weights[i];
            }
            result += weights[i];
        }
        return result;
    }

    private int depart(int[] weights, int val) {
        int count = 0;
        int sum = weights[0];
        for (int i = 1; i < weights.length; i++) {
            sum += weights[i];
            if (sum > val) {
                count++;
                sum = weights[i];
            }
        }
        return count + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] weights = {3,2,2,4,1,4};
        int days = 3;
        int res = solution.shipWithinDays(weights, days);
       /*  System.out.println(solution.depart(weights, 6)); */
        System.out.println(res);
    }
}
