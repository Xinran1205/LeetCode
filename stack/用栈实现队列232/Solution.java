import java.util.Stack;


// 这个思想很重要
// peek()方法：无论是用作队列还是栈，peek()方法总是返回队列的前端或栈顶元素，
// 因为在Deque的实现中，队列的前端（头部）和栈顶都被视为是同一位置。
class Solution {
    Stack<Integer> a;
    Stack<Integer> b;

    public Solution() {
        a = new Stack<Integer>();
        b = new Stack<Integer>();
    }

    public void push(int x) {
        a.push(x);
    }

    public int pop() {
        if(b.isEmpty()){
            while(!a.isEmpty()){
                b.push(a.pop());
            }
        }
        return b.pop();
    }

    public int peek() {
        if(b.isEmpty()){
            while(!a.isEmpty()){
                b.push(a.pop());
            }
        }
        return b.peek();
    }

    public boolean empty() {
        return a.isEmpty()&&b.isEmpty();
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        System.out.println(obj.pop());
        System.out.println(obj.peek());
        System.out.println(obj.empty());
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */