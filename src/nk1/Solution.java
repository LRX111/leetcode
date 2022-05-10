package nk1;


public class Solution {
    public ListNode ReverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode ln1 = head;
        ListNode head1 = head;
        ListNode ln2 = head.next;
        while (ln2!=null){
            ListNode ln3 = ln2.next;
            ln2.next = ln1;
            ln1 = ln2;
            ln2 = ln3;
            head1 = ln1;
        }
        head.next = null;
        return head1;
    }
}