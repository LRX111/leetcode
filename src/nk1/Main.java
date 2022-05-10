package nk1;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next=l2;
        l2.next=l3;
        ListNode t= s.ReverseList(l1);
        while (t!=null){
            System.out.print(t.val);
            t = t.next;
        }
    }
}
