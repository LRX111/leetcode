package myCourses;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        next = null;
    }
}

public class reverseList {
    public static ListNode reverse(ListNode one, ListNode two) {
        if (two == null)
            return one;
        ListNode three = two.next;
        two.next = one;
        one = two;
        two = three;
        return reverse(one, two);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        ListNode head = reverse(null, makelist(nums));
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode makelist(int[] nums) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        for (int i = 0; i < nums.length; i++) {
            node.next = new ListNode(nums[i]);
            node=node.next;
        }
        return dummy.next;
    }
}
