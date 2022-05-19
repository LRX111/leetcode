package leetcode_37;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Solution {
    public void solveSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9) return;
        char[][] boardclone = objectClone(board);
        Set<Integer>[] rowsCandidate = new Set[9];
        Set<Integer>[] colsCandidate = new Set[9];
        Set<Integer>[] subboxesCandidate = new Set[9];
        for (int i = 0; i < 9; i++) {
            rowsCandidate[i] = new HashSet<>();
            colsCandidate[i] = new HashSet<>();
            subboxesCandidate[i] = new HashSet<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 1; j <= 9; j++) {
                rowsCandidate[i].add(j);
                colsCandidate[i].add(j);
                subboxesCandidate[i].add(j);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int number = board[i][j] - '0';
                    rowsCandidate[i].remove(number);
                    colsCandidate[j].remove(number);
                    subboxesCandidate[(i / 3) * 3 + j / 3].remove(number);
                }
            }
        }
//        printBoard(board);
//        System.out.println("行候选：");
//        for (Set<Integer> set : rowsCandidate) {
//            System.out.println(set);
//        }
//        System.out.println("列候选：");
//        for (Set<Integer> set : colsCandidate) {
//            System.out.println(set);
//        }
//        System.out.println("子box候选：");
//        for (Set<Integer> set : subboxesCandidate) {
//            System.out.println(set);
//        }
//        System.out.println(try_solveSudoku(board, boardclone, 0));
        boolean flg = tryPlus_solveSudoku(board, boardclone, 0, rowsCandidate, colsCandidate, subboxesCandidate);
        System.out.println("我的答案：" + flg);
        printBoard(board);
    }

    /**
     * 第一次的方法
     *
     * @param board
     * @param boardclone
     * @param index
     * @return
     */
    private boolean try_solveSudoku(char[][] board, char[][] boardclone, int index) {
        if (index == 80) {
            printBoard(board);
            if (boardclone[8][8] != '.') {
                if (isvalid(board))
                    return true;
                else return false;
            }
            for (int i = 1; i <= 9; i++) {
                board[8][8] = (char) (i + '0');
                if (isvalid(board))
                    return true;
            }
            return false;
        }
        int row = index / 9;
        int col = index % 9;
        if (boardclone[row][col] != '.')
            return try_solveSudoku(board, boardclone, index + 1);
        else {
            for (int i = 1; i <= 9; i++) {
                board[row][col] = (char) (i + '0');
                if (try_solveSudoku(board, boardclone, index + 1) == true) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 第二次方法
     *
     * @param board
     * @param originboard
     * @param index
     * @param rowsCandidate
     * @return
     */
    private boolean tryPlus_solveSudoku(char[][] board, char[][] originboard, int index,
                                        Set<Integer>[] rowsCandidate,
                                        Set<Integer>[] colsCandidate,
                                        Set<Integer>[] subboxesCandidate) {
        if (index == 81) {
            printBoard(board);
            return isvalid(board);
        }
        int row = index / 9;
        int col = index % 9;
        if (originboard[row][col] != '.')
            return tryPlus_solveSudoku(board, originboard, index + 1, rowsCandidate, colsCandidate, subboxesCandidate);
        else {
            // 如果当前单元格表示空格,尝试填充数字
            // 候选数字为当前单元格处的行候选、列候选、子box候选的交集
            Set<Integer> JiaoJi=new HashSet<>();
            JiaoJi.addAll(rowsCandidate[row]);
            JiaoJi.retainAll(colsCandidate[col]);
            JiaoJi.retainAll(subboxesCandidate[(row/3)*3+col/3]);
            for (Integer num : JiaoJi) {
                board[row][col]=(char)(num+'0');
                rowsCandidate[row].remove(num);
                colsCandidate[col].remove(num);
                subboxesCandidate[(row/3)*3+col/3].remove(num);
                if (tryPlus_solveSudoku(board, originboard, index + 1,
                        rowsCandidate, colsCandidate, subboxesCandidate)) return true;
                else {
                    rowsCandidate[row].add(num);
                    colsCandidate[col].add(num);
                    subboxesCandidate[(row/3)*3+col/3].add(num);
                }
            }
            return false;
        }
    }


    public boolean isvalid(char[][] board) {
        //row[i][j]表示第i行j+1的出现次数
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][][] subbox = new int[3][3][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    int number = board[i][j] - '0' - 1;
                    row[i][number]++;
                    col[j][number]++;
                    subbox[i / 3][j / 3][number]++;
                    if (row[i][number] > 1 || col[j][number] > 1 || subbox[i / 3][j / 3][number] > 1)
                        return false;
                }
            }
        }
        return true;
    }

    private void printBoard(char[][] board) {
        System.out.print("[");
        for (int i = 0; i < board.length; i++) {
            if (i != 0) System.out.print(' ');
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j == board.length - 1) {
                    if (i == board.length - 1)
                        System.out.print("]");
                    else
                        System.out.print('\n');
                } else {
                    System.out.print(", ");
                }
            }
        }
        System.out.print('\n');
        System.out.println("---------------------------------------------");
    }

    private static <T> T objectClone(T obj) {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(obj);
            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bin);
            return (T) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Set<Integer> myset = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            myset.add(i);
        }
        Iterator<Integer> iterator = myset.iterator();
        Set<Integer> clone = objectClone(myset);
        while (iterator.hasNext()) {
            int num = iterator.next();
            System.out.println(num);
            iterator.remove();
//            myset.add(num);
        }
        System.out.println(myset);
        System.out.println(clone);

        System.out.println("************************************");
        Set<Integer>[] mysets = new Set[2];
        for (int i = 0; i < mysets.length; i++) {
            mysets[i] = new HashSet<>();
            for (int j = 0; j < 3; j++) {
                mysets[i].add(j);
            }
        }
        System.out.println("数组型集合:");
        for (Set<Integer> set : mysets) {
            System.out.println(set);
        }
        Set<Integer>[] clone1 = mysets.clone();
        Set<Integer>[] clone2 = objectClone(mysets);
        System.out.println("修改原数组型集合后的克隆数组型集合");
        mysets[0].remove(2);
        for (Set<Integer> set : clone1) {
            System.out.println(set);
        }
        System.out.println("修改原数组型集合后的序列化克隆数组型集合");
        for (Set<Integer> set : clone2) {
            System.out.println(set);
        }
//        for (Integer integer : myset) {
//            System.out.println(integer);
//            myset.remove(integer);
////            myset.add(integer);
//        }
//        for (int i = 0; i < myset.size(); i++) {
//            System.out.println(myset.);
//            myset.remove(integer);
//            myset.add(integer);
//        }
    }
}
