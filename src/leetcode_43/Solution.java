package leetcode_43;

public class Solution {
    public String multiply(String num1, String num2) {
        StringBuilder ans = new StringBuilder();
        StringBuilder offsetStr = new StringBuilder();
        for (int i = num2.length() - 1; i >= 0; i--) {
            //从num2后往前选出每一个数字与num1相乘，并将结果添加到ans中
            int currentNum2 = num2.charAt(i) - '0';
            int jinWei = 0;
            StringBuilder subans = new StringBuilder();
            for (int j = num1.length() - 1; j >= 0; j--) {
                //将num1中每一个数字与currentNum2相乘，取乘积个位与上一个进位相加并保存，更新进位
                int currentNum1 = num1.charAt(j) - '0';
                int chengji = currentNum1 * currentNum2 + jinWei;
                int gewei = chengji % 10;
                subans.append(gewei);
                jinWei = chengji / 10;
            }
            if (jinWei != 0) subans.append(jinWei);
            //将ans中的已有结果与subans增加偏移量后相加
            subans.insert(0, offsetStr);
            strAdd(ans, subans);
            offsetStr.append('0');
        }
        while (ans.length() > 1 && ans.charAt(ans.length() - 1) == '0') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.reverse().toString();
    }

    private void strAdd(StringBuilder ans, StringBuilder subans) {
        int jinWei = 0;
        for (int i = 0; i < subans.length(); i++) {
            int a = subans.charAt(i) - '0';
            if (i < ans.length()) {
                int b = ans.charAt(i) - '0';
                int sum = a + b + jinWei;
                ans.setCharAt(i, (char) (sum % 10 + '0'));
                jinWei = sum / 10;
            } else {
                int sum = a + jinWei;
                ans.append((char) (sum % 10 + '0'));
                jinWei = sum / 10;
            }
        }
        if (jinWei != 0) ans.append((char) (jinWei + '0'));
    }

    public static void main(String[] args) {
        String num1 = "465468794869867";
        String num2 = "54657641365468";
        Solution solution = new Solution();
        System.out.println(solution.multiply(num1, num2));
    }
}
