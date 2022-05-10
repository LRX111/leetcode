package leetcode_24;

import java.util.List;

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
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        else if ((head.next == null)) return head;
        //至少有两个节点时
        ListNode one = head;
        ListNode two = head.next;
        ListNode three = two.next;
        head = two;
        while (true) {
            //three为空
            if (three == null) {
                one.next = three;
                two.next = one;
                break;
            }
            //three.next为空
            if (three.next == null) {
                one.next = three;
                two.next = one;
                break;
            } else {
                //three.next不为空
                one.next = three.next;
                two.next = one;
                one = three;
                two = three.next;
                three = two.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = makeList(new int[]{1});
        Solution solution = new Solution();
        System.out.println(solution.swapPairs(head));
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
