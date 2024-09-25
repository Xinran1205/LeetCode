import java.util.ArrayDeque;
import java.util.Deque;

class MyStack {
    Deque<Integer> a;
    Deque<Integer> b;

    public MyStack() {
        a = new ArrayDeque<Integer>();
        b = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        a.offer(x);
        while (!b.isEmpty()){
            a.offer(b.poll());
        }
        Deque<Integer> temp = a;
        a = b;
        b = temp;
    }

    public int pop() {
        return b.poll();
    }

    public int top() {
        return b.peek();
    }

    public boolean empty() {
        return b.isEmpty();
    }

    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        System.out.println(obj.pop());
        System.out.println(obj.top());
        System.out.println(obj.empty());
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
