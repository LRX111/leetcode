package nk76;

import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
//        while (!stack1.empty()) {
//            stack2.push(stack1.pop());
//        }
//        int ans = stack2.pop();
//        while (!stack2.empty()) {
//            stack1.push(stack2.pop());
//        }
//        return ans;
        if (stack1.empty() && stack2.empty()) {
            throw new RuntimeException("Queue is empty!");
        }
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        } else {
            return stack2.pop();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] input = {"PSH2", "PSH3", "POP", "PSH1", "POP", "POP"};
        for (String s : input) {
            if (s.matches("PSH\\d+")) {
                int number = Integer.parseInt(s.substring(3, s.length()));
                solution.push(number);
            } else {
                System.out.println(solution.pop());
            }
        }
    }
}
