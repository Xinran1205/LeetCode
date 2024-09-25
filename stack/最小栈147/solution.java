import java.util.Stack;

// 这个代码需要注意的是，java == 和 equals 的区别！！！
// == 比较的是对象，equals比较的才是值。所以这里要用equals。==A和B肯定不想同。==是比较对象地址
// 补充：因为stack的pop()返回的其实是个泛型对象，从Integer可以看出
// 注意基本数据类型和其封装类的区别，如：char和Character，boolean和Boolean

class MinStack {
    Stack<Integer> a,b;
    /** initialize your data structure here. */
    public MinStack() {
        a = new Stack<>();
        b = new Stack<>();
    }

    public void push(int x) {
        a.push(x);
        if (b.isEmpty()||x<=b.peek()){
            b.push(x);
        }
    }

    public void pop() {
        if (a.peek().equals(b.peek())){
            a.pop();
            b.pop();
        }else{
            a.pop();
        }
    }

    public int top() {
        return a.peek();
    }

    public int getMin() {
        return b.peek();
    }
}

public class solution {
    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
