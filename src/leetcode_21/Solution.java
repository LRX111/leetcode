package leetcode_21;


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
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        //node指向当前要插入节点的前一个节点
        ListNode node = dummy;
        if (list1 == null && list2 == null) return null;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
                node = node.next;
            } else {
                node.next = list2;
                list2 = list2.next;
                node = node.next;
            }
        }
        if (list1 == null) {
            node.next = list2;
        } else if (list2 == null) {
            node.next = list1;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list1 = solution.makelink(new int[]{});
        ListNode list2 = solution.makelink(new int[]{1, 3, 4});
        ListNode anshead = solution.mergeTwoLists(list1, list2);
        solution.print_list(anshead);
//        System.out.println(list1.toString());
    }

    private ListNode makelink(int[] nums) {
        ListNode dummy = new ListNode();
        ListNode front = dummy;
        for (int i = 0; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            front.next = node;
            front = node;
        }
        return dummy.next;
    }

    private void print_list(ListNode head) {
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