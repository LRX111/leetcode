package leetcode_25;


import java.util.LinkedList;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        boolean flg = true;
        ListNode dummy = new ListNode();
        dummy.next = head;
        for (int i = 0; i < k; i++) {
            if (head == null) {
                flg = false;
                break;
            }
            head = head.next;
        }
        if (flg == false) return dummy.next;
        //else的情况
        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode temp = dummy.next;
        ListNode front;
        for (int i = 0; i < k; i++) {
            stack.offerFirst(temp);
            front = temp;
            temp = temp.next;
            front.next = null;
        }
        temp = dummy;
        for (int i = 0; i < k; i++) {
            temp.next = stack.pollFirst();
            temp = temp.next;
        }
        //循环结束的temp指向第k个节点,head为k+1个节点
        temp.next = reverseKGroup(head, k);
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = makeList(new int[]{1, 2, 3, 4, 5});
        System.out.println(solution.reverseKGroup(head, 4));
    }

    private static ListNode makeList(int[] nums) {
        ListNode dummy = new ListNode();
        ListNode node = dummy;
        for (int i = 0; i < nums.length; i++) {
            node.next = new ListNode(nums[i]);
            node = node.next;
        }
        return dummy.next;
    }
}
