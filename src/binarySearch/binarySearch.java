package binarySearch;

import java.util.Scanner;

public class binarySearch {
    public static int theBinarySearch(int[] a, int x) {
        int left = 0, right = a.length - 1;
        int mid = (left + right) / 2;
        while (right >= left) {
            if (a[mid] == x) return mid;
            else if (a[mid] > x) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else {
                left = mid + 1;
                mid = (left + right) / 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = -1;
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        System.out.println("在数组{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}中查找元素x：");
        if (scan.hasNextInt()) {
            x = scan.nextInt();
        }
        scan.close();
        System.out.println("x的元素下标是：" + theBinarySearch(a, x));

    }
}
