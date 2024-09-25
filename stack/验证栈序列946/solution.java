import java.util.Stack;
import java.util.ArrayDeque;
import java.util.Deque;

class solution {
    // 这个方法是正向思维，非常好！！！
    // 用一个栈模拟，就放pushed元素，如果pushed等于popped，就把拿出去
    // 最后遍历完如果栈为空就是OK，否则为false
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i=0,j=0;i<pushed.length;i++){
            stack.push(pushed[i]);
            while (!stack.isEmpty()&&stack.peek()==popped[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    // 这个方法是逆向思维，是我自己写的，是先研究popped，但是感觉有点多此一举
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> helpStack = new Stack<Integer>();
        //遍历popped
        for(int i=0,j=0;i<popped.length;i++){
            int val = popped[i];
            //看看val是否在stack中
            if (helpStack.contains(val)){
                if(helpStack.peek()!=val){
                    return false;
                }else{
                    helpStack.pop();
                }
            }else{
                // 循环遍历pushed，把val之前的全部放进去
                while(j<pushed.length){
                    helpStack.push(pushed[j]);
                    if(pushed[j]==val){
                        j++;
                        break;
                    }
                    j++;
                }
                helpStack.pop();
            }
        }
        return true;
    }

    public static void main(String[] args) {
        solution s = new solution();
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};
        System.out.println(s.validateStackSequences(pushed, popped));
    }
}