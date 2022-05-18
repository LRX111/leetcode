package leetcode_36;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character> rowSet;
        Set<Character>[] colSet = new Set[9];
        for (int i = 0; i < 9; i++) {
            colSet[i] = new HashSet<>();
        }
        Set<Character>[] nineSet = new Set[3];
        for (int row = 0; row < board.length; row++) {
            if (row % 3 == 0) {
                nineSet[0] = new HashSet<>();
                nineSet[1] = new HashSet<>();
                nineSet[2] = new HashSet<>();
            }
            rowSet = new HashSet<>();
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == '.') continue;
                if (rowSet.contains(board[row][col])) return false;
                if (colSet[col].contains(board[row][col])) return false;
                if (nineSet[col / 3].contains(board[row][col])) return false;
                rowSet.add(board[row][col]);
                colSet[col].add(board[row][col]);
                nineSet[col / 3].add(board[row][col]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        double a = -3.0;
        double b = 2.5;
        System.out.println("Double.MAX_VALUE：" + Double.MAX_VALUE);
        System.out.println("Double.MIN_VALUE：" + Double.MIN_VALUE);
        System.out.println("Double.NEGATIVE_INFINITY：" + Double.NEGATIVE_INFINITY);
        System.out.println("Double.MIN_NORMAL:" + Double.MIN_NORMAL);
        String stra = Double.toHexString(a);
        System.out.println("Double a(" + a + ")" + " 的16进制表示为：" + stra);
        HexString2Double(stra);
        String strb = Double.toHexString(b);
        System.out.println("Double b(" + b + ")" + " 的16进制表示为：" + strb);
        HexString2Double(strb);
        String stra_b = Double.toHexString(a - b);
        System.out.println("Double a-b(" + (a - b) + ")" + " 的16进制表示为：" + stra_b);
        HexString2Double(stra_b);
    }

    private static void HexString2Double(String HexString) {
        Character[] HexCharacter = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String[] BinaryString = {
                "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000",
                "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
        Map<Character, String> Hex2BinMap = new HashMap<>();
        for (int i = 0; i < HexCharacter.length; i++) {
            Hex2BinMap.put(HexCharacter[i], BinaryString[i]);
        }
        String[] one = HexString.split("0x");
        String[] two = one[1].split("p");
        String Sign = one[0];
        String Mantissa = two[0];
        String Exponant = two[1];
        String BinStr = Mantissa.substring(0, 2);
        for (int i = 2; i < Mantissa.length(); i++) {
            BinStr += Hex2BinMap.get(Mantissa.charAt(i));
        }
//        BinStr+="*2^"+Exponant;
        System.out.println("二进制表示为：" + Sign + BinStr + "*2^" + Exponant);
        Double decimalValue = 1.0;
        Double temp = 1.0;
        for (int i = 2; i < BinStr.length(); i++) {
            temp /= 2;
            if (BinStr.charAt(i) == '1') {
                decimalValue += temp;
            }
        }
        System.out.println("十进制表示为：" + Sign + decimalValue + "*2^" + Exponant);
    }
}
