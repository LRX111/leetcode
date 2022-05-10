package leetcode_2;

//  Definition for singly-linked list.
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

    public void appendByHead(int val) {
        if (this.next == null) {
            ListNode node = new ListNode(val, null);
            this.next = node;
        } else {
            ListNode t = this;
            do {
                t = t.next;
            } while (t.next != null);
            ListNode node = new ListNode(val, null);
            t.next = node;
        }
    }
}


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        ListNode head = l3;
        while ((l1 != null) || (l2 != null)) {
            if (l1 != null) {
                l3.val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                l3.val += l2.val;
                l2 = l2.next;
            }
            if ((l1 != null) || (l2 != null) || (l3.val >= 10)) {
                ListNode node = new ListNode(0);
                if (l3.val >= 10) {
                    l3.val = l3.val % 10;
                    node = new ListNode(1);
                }
                l3.next = node;
                l3 = l3.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode link1 = new ListNode(2, null);
        link1.appendByHead(4);
        link1.appendByHead(3);
        ListNode link2 = new ListNode(5, null);
        link2.appendByHead(6);
        link2.appendByHead(4);
        link2.appendByHead(6);
        ListNode t = link1;
        do {
            System.out.print(t.val + " ");
            t = t.next;
        } while (t != null);
        System.out.print("\n");
        t = link2;
        do {
            System.out.print(t.val + " ");
            t = t.next;
        } while (t != null);
        System.out.println("\n" + "**************************************************");
        ListNode link3 = new Solution().addTwoNumbers(link1, link2);
        t = link3;
        do {
            System.out.print(t.val + " ");
            t = t.next;
        } while (t != null);
    }
}