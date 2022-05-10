package leetcode_23;

import java.util.*;

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
    /**
     * 两两合并版本
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length <= 0) return null;
        else if (lists.length == 1) return lists[0];
        ListNode node = lists[0];
        for (int i = 1; i < lists.length; i++) {
            node = doubleMerge(node, lists[i]);
        }
        return node;
    }

    private ListNode doubleMerge(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode();
        ListNode node = dummy;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                node.next = node1;
                node1 = node1.next;
                node = node.next;
            } else {
                node.next = node2;
                node2 = node2.next;
                node = node.next;
            }
        }
        if (node1 == null)
            node.next = node2;
        else node.next = node1;
        return dummy.next;
    }

    /**
     * 优先级队列版本
     *
     * @param lists
     * @return
     */
    public ListNode PriorityQ_mergeKLists(ListNode[] lists) {
        Comparator<ListNode> comparator = new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        };

        PriorityQueue<ListNode> queue = new PriorityQueue(comparator);
        for (ListNode list : lists) {
            ListNode node = list;
            ListNode t;
            while (node != null) {
                queue.offer(node);
                t = node;
                node = node.next;
                t.next = null;
            }
        }
        ListNode dummy = new ListNode();
        ListNode node = dummy;
        while (!queue.isEmpty()) {
            node.next = queue.poll();
            node = node.next;
        }
        return dummy.next;
    }

    /**
     * 分治合并版本
     *
     * @param lists
     * @return
     */
    public ListNode FZHB_mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        else if (lists.length == 1) return lists[0];
        else return FZHB(lists, 0, lists.length);
    }

    private ListNode FZHB(ListNode[] lists, int began, int end) {
        if (end - began == 1) return lists[began];
        else if (end - began == 2) {
            return doubleMerge(lists[began], lists[end - 1]);
        } else {
            int mid = (began + end) / 2;
            ListNode one = FZHB(lists, began, mid);
            ListNode two = FZHB(lists, mid, end);
            return doubleMerge(one, two);
        }
    }

    public static void main(String[] args) {
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("已有的升序链表数：");
        n = scanner.nextInt();
        ListNode[] lists = new ListNode[n];
        for (int i = 0; i < n; i++) {
            System.out.println("输入升序链表" + (i + 1) + "：(以任意字符结束)");
            List<Integer> t = new ArrayList<>();
            while (scanner.hasNext()) {
                if (scanner.hasNextInt())
                    t.add(scanner.nextInt());
                else {
                    scanner.next();
                    break;
                }
            }
            lists[i] = makeList(t);
        }
        scanner.close();
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                System.out.println(lists[i].toString());
            else System.out.println("list[" + i + "]为空。");
        }
        //lists = [[1,4,5],[1,3,4],[2,6]]
        /*
        3
        1 4 5 s
        1 3 4 s
        2 6 s
        */
        System.out.println("------------------------------------------------");
        Solution solution = new Solution();
//        System.out.println("两两合并版本："+printLink(solution.mergeKLists(lists)));
//        System.out.println("优先级队列版本：" + printLink(solution.PriorityQ_mergeKLists(lists)));
        System.out.println("分治合并版本：" + printLink(solution.FZHB_mergeKLists(lists)));
    }

    private static String printLink(ListNode head) {
        String ans = "";
        ans += "[";
        while (head != null) {
            ans += head.val;
            if (head.next != null) ans += ", ";
            head = head.next;
        }
        ans += "]";
        return ans;
    }

    private static ListNode makeList(List<Integer> nums) {
        ListNode dummy = new ListNode();
        ListNode front = dummy;
        for (int i = 0; i < nums.size(); i++) {
            ListNode node = new ListNode(nums.get(i));
            front.next = node;
            front = front.next;
        }
        return dummy.next;
    }
}
/*
2
-2 -1 -1 -1 s
s
*/