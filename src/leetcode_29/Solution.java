package leetcode_29;

import java.util.Scanner;

public class Solution {
    public int divide(int dividend, int divisor) {
        boolean flg = ((dividend < 0) && (divisor < 0)) || ((dividend > 0) && (divisor > 0)) ? true : false;
        int count = 0;
        int sum = 0;
        if (dividend >= 0) {
            if (divisor > 0) {
                while (dividend > sum) {
                    if (sum > dividend - divisor) break;
                    count++;
                    sum += divisor;
                }
            } else {
                while (dividend > sum) {
                    if (sum > dividend + divisor) break;
                    count++;
                    sum -= divisor;
                }
            }
        } else {
            //divident<0时
            if (divisor > 0) {
                while (dividend < sum) {
                    if (sum < dividend + divisor) break;
                    count++;
                    sum -= divisor;
                }
            } else {
                //divident<0;divisor<0时
                while (dividend < sum) {
                    if ((count > Integer.MAX_VALUE - 1) || (sum < dividend - divisor)) break;
                    count++;
                    sum += divisor;
                }
            }
        }
        return flg ? count : 0 - count;
    }

    public int chaoDeDaAn(int dividend, int divisor) {
        boolean fu = (((dividend >>> 31) ^ (divisor >>> 31)) == 1);
        if (dividend > 0) dividend = -dividend;
        if (divisor > 0) divisor = -divisor;
        int mod = divisor;
        int minn = dividend >> 1;//被除数的一半
        int now = -1;
        while (mod >= minn && mod >= (Integer.MIN_VALUE >> 1)) {//当mod>=被除数的一半且mod>=整型最小值的一半时
            mod <<= 1;//mod×2
            now <<= 1;
        }
        int ans = 0;
        //此时mod有可能绝对值大于被除数绝对值的一半
        //也有可能mod绝对值大于整型最小绝对值的一半（此时说明被除数绝对值>=2^31）
        //但此时一定mod绝对值小于被除数绝对值
        while (dividend <= divisor) {//如果被除数小于等于除数（即被除数绝对值大于等于除数绝对值（初始必然））
            while (mod < dividend) {
                //即被除数不能减mod时
                mod >>= 1;//mod除2
                now >>= 1;
            }
            while (dividend <= mod) {
                //即被除数可以减mod时
                dividend -= mod;
                ans -= now;
            }
        }
        if (ans == -2147483648 && !fu) return 2147483647;
        return fu ? -ans : ans;
    }

    /**
     * 我写的移位思路
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int myYiWeiFangshi(int dividend, int divisor) {
        boolean ansfuhao = (dividend >>> 31 ^ divisor >>> 31) == 0 ? true : false;//正数为true，负数为false
        if (dividend >= 0) dividend = 0 - dividend;
        if (divisor > 0) divisor = 0 - divisor;
        int mod = divisor;
        int count = -1;
        //找到最接近被除数的那个数
        while (mod >= (dividend >> 1)) {
            mod = mod << 1;
            count = count << 1;
        }
        int ans = 0;
        //如果被除数小于除数
        while (dividend <= divisor) {
            while (dividend > mod) {
                mod = mod >> 1;
                count = count >> 1;
            }
            while (dividend <= mod) {
                dividend -= mod;
                ans += count;
            }
        }
        if ((ans == -2147483648) && (ansfuhao == true)) ans++;
        return ansfuhao ? 0 - ans : ans;
    }

    /**
     * 传说中的二分查找法
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/divide-two-integers/solution/liang-shu-xiang-chu-by-leetcode-solution-5hic/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int BS_method(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }

        // 一般情况，使用二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        int left = 1, right = Integer.MAX_VALUE, ans = 0;
        while (left <= right) {
            // 注意溢出，并且不能使用除法
            int mid = left + ((right - left) >> 1);
            boolean check = quickAdd(divisor, mid, dividend);
            System.out.println("divisor:"+divisor+" mid:"+mid+" dividend:"+dividend+" check:"+check);
            if (check) {//如果z小了，即y*z => x
                ans = mid;
                // 注意溢出
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {//如果z大了，即y*z < x
                right = mid - 1;
            }
        }
        return rev ? -ans : ans;
    }

    /**
     * 快速乘发法
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/divide-two-integers/solution/liang-shu-xiang-chu-by-leetcode-solution-5hic/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param y 除数
     * @param z 商
     * @param x 被除数
     * @return
     */
    private boolean quickAdd(int y, int z, int x) {
        // x 和 y 是负数，z 是正数
        // 需要判断 z * y >= x 是否成立
        int result = 0, add = y;//add是啥意思？？？？？
        while (z != 0) {
            if ((z & 1) != 0) {//如果z是奇数
                // 需要保证 result + add >= x
                if (result < x - add) {//初始情况为 0 < x-y,即x<y，此时 x/y >0, z>=0,所以z*y>x
                    return false;
                }
                result += add;
            }
            if (z != 1) {
                // 需要保证 add + add >= x
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            // 不能使用除法
            z >>= 1;
        }
        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入被除数：");
        int dividend = scanner.nextInt();
        System.out.println("输入除数：");
        int divisor = scanner.nextInt();
        scanner.close();
        System.out.println("普通除法" + (dividend / divisor));
        System.out.println("我的暴力：" + solution.divide(dividend, divisor));
        System.out.println("抄的答案：" + solution.chaoDeDaAn(dividend, divisor));
        System.out.println("我写的移位思路：" + solution.myYiWeiFangshi(dividend, divisor));
        System.out.println("官解二分查找法：" + solution.BS_method(dividend, divisor));

        System.out.println("测试它的快速乘函数是干撒的？？？？？？：");
        int mid=250;
        System.out.println(solution.quickAdd(divisor,mid,dividend));
        System.out.println("-------------------------------------------------------------------");
        System.out.println("divisor 的二进制：" + Integer.toBinaryString(divisor));
        int t = (divisor << 29);
        System.out.println("(divisor<<29) = " + t + " 二进制表示为：" + Integer.toBinaryString(t));
        t = divisor >> 1;
        System.out.println("divisor>>1 = " + t + " 二进制表示为：" + Integer.toBinaryString(t));
        t = divisor >>> 1;
        System.out.println("divisor>>>1 = " + t + " 二进制表示为：" + Integer.toBinaryString(t));
        t = ~divisor;
        System.out.println("~divisor = " + t + " 二进制表示为：" + Integer.toBinaryString(t));
        System.out.println(Integer.MAX_VALUE);
        t = divisor >>> 31;
        System.out.println("divisor>>>31 = " + t);
        t = divisor & 1;
        System.out.println("divisor&1 = " + t);
    }
}
