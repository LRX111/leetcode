package leetcode_135;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int candy(int[] ratings) {
        int result = 0;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candies[i] = candies[i - 1] + 1;
            }

        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i + 1] < ratings[i] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }
        for (int i = 0; i < candies.length; i++) {
            result += candies[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List a=new ArrayList();
    }
}