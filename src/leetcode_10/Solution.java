package leetcode_10;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int successFront = 0;
        int successEnd = -1;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                //当s[i]==p[j]时，i++,j++
                if (successEnd < successFront) {
                    //当s的匹配子串未开始时,记录匹配子串的开始
                    successFront = i;
                    successEnd = i;
                } else {
                    //当s的匹配子串开始了时,只更改匹配子串的结束
                    successEnd = i;
                }
                i++;
                j++;
            } else {
                //当s[i]！=p[j]&&p[j]!=.时，情况有①s[i]！=p[j]且p[j]!=.且p[j]!=*；②s[i]！=p[j]&&p[j]!=.且p[j]==*
                //考虑p[j+1]是否等于*的情况
                if (p.charAt(j) != '*') {
                    //当 ①s[i]！=p[j]且p[j]!=.且p[j]!=*
                    if (j + 1 == p.length()) {
                        //若j已是p的最后元素，且p[j]!=*，且s[i]！=p[j]
                        //匹配失败,重新匹配
                        i++;
                        j = 0;
                        successFront = i;
                        successEnd = -1;
                    } else if (p.charAt(j + 1) != '*') {
                        //若j不是p的最后一个元素，且p[j]!=*，且p[j+1]！=*，且s[i]！=p[j]
                        //即可判定，到s[i]处为止，无法与p匹配
                        j = 0;
                        i++;
                        successFront = i;
                        successEnd = -1;
                    } else if (p.charAt(j + 1) == '*') {
                        //若j不是p的最后一个元素，且p[j]!=*，且p[j+1]==*，且s[i]！=p[j]
                        //因为p[j+1]==*，因而允许p[j]与s[i]处不匹配
                        //至于是否应继续判断至s[i]为止的子串是否与p匹配，应判断s[i]是否属于p[j]为0个字符的情况
                        //①属于：到s[i-1]为止，都与p匹配，s[i]与p[j+2]匹配
                        //②不属于：到s[i-1]为止，都与p匹配,s[i]与p[j+2]不匹配
                        //p[j+2]不存在，若到s[i-1]为止，都与p匹配，则到s[i-1]为止已与p匹配完毕
                        //p[j+2]不存在，若不存在到s[i-1]为止，都与p匹配，则从i开始重新与p开始对比
                        if (j + 2 < p.length()) {
                            //若p[j+2]存在
                            if (s.charAt(i) == p.charAt(j + 2) || p.charAt(j + 2) == '.') {
                                //若s[i]与p[j+2]匹配
                                if (successEnd < successFront) {
                                    //当s的匹配子串未开始时,记录匹配子串的开始
                                    successFront = i;
                                    successEnd = i;
                                } else {
                                    //当s的匹配子串开始了时,只更改匹配子串的结束
                                    successEnd = i;
                                }
                                i++;
                                j = j + 2 + 1;
                            } else {
                                //若s[i]与p[j+2]不匹配
                                //此时若p[j+2]允许为0，则继续看j+4是否与s[i]匹配
                                //若p[j+2]不允许为0，则p[0]开始与s[i]匹配
                                if (j + 3 < p.length()) {
                                    if (p.charAt(j + 3) == '*') {
                                        //p[j+2]允许为0
                                        j = j + 4;
                                    } else {
                                        //p[j+2]不允许为0
                                        if (j == 0) {
                                            i++;
                                            j = 0;
                                            successFront = i;
                                            successEnd = -1;
                                        } else {
                                            j = 0;
                                            successFront = i;
                                            successEnd = -1;
                                        }

                                    }
                                } else {
                                    if (j == 0) {
                                        i++;
                                        j = 0;
                                        successFront = i;
                                        successEnd = -1;
                                    } else {
                                        j = 0;
                                    }
                                }

                            }
                        } else {
                            //若p[j+2]不存在
                            if (successEnd >= successFront) {
                                //若存在到s[i-1]为止，都与p匹配
                                System.out.println(s.substring(successFront, successEnd + 1));
                                if ((successEnd + 1 - successFront) == s.length()) return true;
                                else return false;
//                                return true;
                            } else {
                                //若不存在到s[i-1]为止，都与p匹配，从p[0]开始与s[i]匹配
                                //如果j已经为0，则从s[i+1]开始与p[0]匹配
                                if (j == 0) {
                                    i++;
                                    successFront = i;
                                    successEnd = -1;
                                } else {
                                    i++;
                                    j = 0;
                                    successFront = i;
                                    successEnd = -1;
                                }


                            }
                        }
                    }
                } else {
                    //当 ②s[i]！=p[j]&&p[j]!=.且p[j]==*
                    //因为p[j]==*，所以p[j-1]==.或p[j-1]==s[i-1]（即进入此分支的条件为至p[j]为止存在s与之匹配的子串）
                    //此时若p[j+1]不存在，则判断已经存在的匹配子串能延长多少
                    //若p[j+1]存在，则判断已经存在的匹配子串能延长的极致，此时再比较p[j+1]
                    if (j + 1 >= p.length()) {
                        //若p[j+1]不存在
                        while (i < s.length()) {
                            if (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                                successEnd = i;
                                i++;
                            } else {
                                System.out.println(s.substring(successFront, successEnd + 1));
                                if ((successEnd + 1 - successFront) == s.length()) return true;
                                else return false;
//                                return true;
                            }
                        }
                        //若跳出循环，说明匹配到了s结束
                        successEnd = i;
                        System.out.println(s.substring(successFront, successEnd));
                        if ((successEnd - successFront) == s.length()) return true;
                        else return false;
//                        return true;
                    } else {
                        //若p[j+1]存在
                        while (i < s.length()) {
                            if (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                                successEnd = i;
                                i++;
                            } else {
                                j++;
                                break;
                            }
                        }
                        //若因break跳出循环，说明p*的匹配已到极致，而p*后还有元素要匹配，此时i，j指向s和p中要批匹配的位置
                        if (i < s.length()) {
                        }
                        //若因while条件跳出循环，说明直至s结束都与p*处匹配，但p*后还有要求
                        //此时若p*后元素要求至少匹配一次，则匹配失败；
                        //若p*后元素允许为匹配0次，则匹配成功
                        else if (j + 2 >= p.length()) {
                            //若p*后元素要求至少匹配一次
                            if (p.charAt(j + 1) == p.charAt(j - 1) || p.charAt(j + 1) == '.' || p.charAt(j + 1) == s.charAt(i - 1)) {
                                System.out.println(s.substring(successFront, successEnd + 1));
                                if ((successEnd + 1 - successFront) == s.length()) return true;
                                else return false;
//                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            //p*后元素有可能允许匹配0次
                            if (p.charAt(j + 2) == '*') {
                                //p*后元素允许匹配0次
                                System.out.println(s.substring(successFront, successEnd + 1));
                                if ((successEnd + 1 - successFront) == s.length()) return true;
                                else return false;
//                                return true;
                            } else {
                                //直至s[i]处的匹配不满足p，又因i>s.length()，故匹配失败
                                return false;
                            }
                        }
                    }
                }
            }
        }
        //如果跳出大while循环，说明i >= s.length() || j >= p.length()
        //如果是j >= p.length()，说明匹配的已经成功
        //如果是j < p.length()&&i >= s.length(),则根据j后的元素个数是否允许为0判断是否匹配失败
        if (j >= p.length()) {
            System.out.println(s.substring(successFront, successEnd + 1));
            if ((successEnd + 1 - successFront) == s.length()) return true;
            else return false;
//            return true;
        } else {
            if (p.charAt(j) == '*') {
                //说明最后匹配的是p[j-1]处
                //因为s[s.length-1]与p[j-1]匹配,故此时再看j-1后元素是否允许为0判断
                int jj = j;
                while (jj + 2 < p.length()) {
                    if (p.charAt(jj + 2) == '*') {
                        //允许为零,继续判断
                        jj = jj + 2;
                    } else {
                        //不允许为0，匹配失败
                        return false;
                    }
                }
                //此时只会因循环而跳出，因而p[j]==*，且jj+2>=p.length
                if (jj + 1 < p.length()) {
                    //p[jj+1]存在，不允许为0
                    //此时判断p[jj+1]是否与p[j]匹配
                    if (p.charAt(jj + 1) == '.' || p.charAt(jj + 1) == p.charAt(j - 1)) {
                        System.out.println(s.substring(successFront, successEnd + 1));
                        if ((successEnd + 1 - successFront) == s.length()) return true;
                        else return false;
                    } else return false;
                } else {
                    //p[jj+1]不存在
                    System.out.println(s.substring(successFront, successEnd + 1));
                    if ((successEnd + 1 - successFront) == s.length()) return true;
                    else return false;
                }
            } else {
                //若p[j]!='*'
                //说明p[j]处无法与此时超出范围的s[i]处匹配
                //因而检测从p[j]处开始的所有匹配是否都允许为0，是则匹配成功，否则立即失败
                int jj = j + 1;
                while (jj < p.length()) {
                    if (p.charAt(jj) == '*') {
                        jj = jj + 2;
                    } else {
                        return false;
                    }
                }
                if (jj - 1 == p.length()) {
                    System.out.println(s.substring(successFront, successEnd + 1));
                    if ((successEnd + 1 - successFront) == s.length()) return true;
                    else return false;
                } else return false;
            }

        }
    }

    public boolean re(String s, String p) {
        Pattern pattern=Pattern.compile(p);
        Matcher matcher=pattern.matcher(s);
        return matcher.matches();
//        return false;
    }

    public static void main(String[] args) {
        String s = new String();
        String p = new String();
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入字符串s:");
        if (scanner.hasNextLine()) {
            s = scanner.nextLine();
        }

        System.out.println("输入匹配模式p(保证每次出现字符 * 时，前面都匹配到有效的字符):");
        System.out.println("\'.\'表示匹配单个任意字符，\'*\'表示匹配[0,∞]个前一个字符");
        if (scanner.hasNextLine()) {
            p = scanner.nextLine();
        }
        scanner.close();
//        System.out.println(s);
//        System.out.println(p);
        System.out.println("字符串的模式匹配结果为：" + new Solution().isMatch(s, p));
        System.out.println("Java正则表达式匹配的结果为：" + new Solution().re(s, p));
    }
}
