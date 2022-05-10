package leetcode_19;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        //倒数第n个等于正数第len-n+1个,其下标是len-n
        List<ListNode> anslist = new ArrayList<>();
        int len = 0;
        while (node != null) {
            len++;
            anslist.add(node);
            node = node.next;
        }
        if (len - n - 1 >= 0) {//如果要删除节点的前一个节点存在
            ListNode front = anslist.get(len - n - 1);
            front.next = anslist.get(len - n).next;
        } else {//如果要删除节点的前一个节点不存在
            head = head.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1,2,5,4,8,7,9};
        ListNode head = new ListNode();
        ListNode pronode = head;
        for (int i : input) {
            ListNode node = new ListNode(i);
            pronode.next = node;
            pronode = pronode.next;
        }
        head = head.next;
        Solution solution = new Solution();
        head = solution.removeNthFromEnd(head, 1);
        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(", ");
            }
            head = head.next;
        }
        System.out.print("]");

    }
}
