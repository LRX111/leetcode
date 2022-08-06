package leetcode_141;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String str = String.valueOf(val);
        return "val:" + str + " " + super.toString();
    }
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> myset = new HashSet<>();
        while (head != null) {
            if (myset.contains(head))
                return true;
            myset.add(head);
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] list = new int[n];
        for (int i = 0; i < list.length; i++) {
            list[i] = scanner.nextInt();
        }
        int pos = scanner.nextInt();
        scanner.close();
        Solution solution = new Solution();
        boolean result = solution.hasCycle(makeList(list, pos));
        System.out.println(result);
    }

    private static ListNode makeList(int[] list, int pos) {
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        ListNode flagNode = null;
        for (int i = 0; i < list.length; i++) {
            node.next = new ListNode(list[i]);
            node = node.next;
            if (i == pos)
                flagNode = node;
        }
        node.next = flagNode;
        return dummy.next;
    }

}