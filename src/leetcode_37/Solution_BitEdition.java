package leetcode_37;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Solution_BitEdition {
    //rowsCandidate[i]为一个9位的整数，当第k位为1时表示（k+1）已经出现过，0表示没出现过
    private int[] rowsCandidate;
    private int[] colsCandidate;
    private int[] subboxesCandidate;
    private List<int[]> spaces;
    private boolean isvalid;

    public void solve(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) return;
        rowsCandidate = new int[9];
        colsCandidate = new int[9];
        subboxesCandidate = new int[9];
        spaces = new ArrayList<>();
        isvalid = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int num = board[i][j] - '0';
                    int temp = 1 << (num - 1);
                    rowsCandidate[i] |= temp;
                    colsCandidate[j] |= temp;
                    subboxesCandidate[(i / 3) * 3 + j / 3] |= temp;
                }
            }
        }
        //再遍历一遍空格，当此空格处的行候选或列侯选或子表候选仅剩一个值时，填充这个空格
        Iterator<int[]> iterator = spaces.iterator();
        while (iterator.hasNext()) {
            int[] space = iterator.next();
            int mask = ~(rowsCandidate[space[0]] |
                    colsCandidate[space[1]] |
                    subboxesCandidate[(space[0] / 3) * 3 + space[1] / 3]) & 0x1ff;
            if ((mask & (mask - 1)) == 0) {
                int num = Integer.bitCount(mask - 1) + 1;
                rowsCandidate[space[0]] |= mask;
                colsCandidate[space[1]] |= mask;
                subboxesCandidate[(space[0] / 3) * 3 + space[1] / 3] |= mask;
                board[space[0]][space[1]] = (char) (num + '0');
                iterator.remove();
            }
        }
        writeSolveSuduku(board, 0);
    }

    private void writeSolveSuduku(char[][] board, int step) {
        if (spaces == null) return;
        if (spaces.size() == step) {
            isvalid = true;
            return;
        }

        int[] space = spaces.get(step);
        int currentCandidate = ~(rowsCandidate[space[0]] | colsCandidate[space[1]] | subboxesCandidate[(space[0] / 3) * 3 + space[1] / 3]);
        for (int num = 0; num < 9 && isvalid == false; num++) {
            int temp = 1 << num;
            if ((currentCandidate & 1) == 1) {
                board[space[0]][space[1]] = (char) (num + 1 + '0');
                rowsCandidate[space[0]] |= temp;
                colsCandidate[space[1]] |= temp;
                subboxesCandidate[(space[0] / 3) * 3 + space[1] / 3] |= temp;
                writeSolveSuduku(board, step + 1);
                rowsCandidate[space[0]] ^= temp;
                colsCandidate[space[1]] ^= temp;
                subboxesCandidate[(space[0] / 3) * 3 + space[1] / 3] ^= temp;
            }
            currentCandidate = currentCandidate >>> 1;
        }
    }

    public static void main(String[] args) {
//        b & (−b) 得到 b 二进制表示中最低位的 1开始的数值
        int a = 0b000010000;
        System.out.println("a：" + Integer.toBinaryString(a));
        System.out.println("-a：" + Integer.toBinaryString(-a));
        System.out.println("a&(-a)：" + Integer.toBinaryString(a & (-a)));

//        如果b和b-1进行按位与运算后得到的值为0，说明b中只有一个二进制位为1
        System.out.println("**********************************");
        System.out.println("a：" + Integer.toBinaryString(a));
        System.out.println("a-1：" + Integer.toBinaryString(a - 1));
        System.out.println("a&(a-1)：" + Integer.toBinaryString(a & (a - 1)));

        System.out.println("************************************");
        System.out.println("~a：" + Integer.toBinaryString(~a));
        System.out.println("~a&(~a-1)：" + Integer.toBinaryString(~a & (~a - 1)));

        System.out.println("************************************");
        System.out.println("Integer.bitCount(a)：" + Integer.bitCount(a));
        System.out.println("Integer.bitCount(a-1)：" + Integer.bitCount(a - 1));
    }
}
