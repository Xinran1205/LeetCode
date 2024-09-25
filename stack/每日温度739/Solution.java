import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // 简单易懂的方法是构造键值对放在栈中，
    // 也可以不构造，一种巧妙地方法是直接放下标
    // 这个方法的意思是遍历temperature数组，就先放进去，
    // 然后如果后续放的大于前面的，就开始计算差值，然后弹栈
    // 然后这是一个循环，因为这个还可能比之前其他的大，所以还要继续计算
    public int[] dailyTemperatures2(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int[] ret = new int[temperatures.length];
        for (int i=0;i<temperatures.length;i++){
            while(!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                ret[stack.peek()]=i-stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return ret;
    }

    // 无法暴力，超时!!
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ret = new int[temperatures.length];
        //双重for循环暴力
        for(int i=0;i<temperatures.length;i++){
            int val = temperatures[i];
            int next = 0;
            for (int j=i+1;j<temperatures.length;j++){
                int nextVal = temperatures[j];
                if(nextVal>val){
                    next++;
                    ret[i] = next;
                    break;
                }else{
                    next++;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ret = solution.dailyTemperatures(temperatures);
        for (int i=0;i<ret.length;i++){
            System.out.print(ret[i]+" ");
        }
        int[] ret2 = solution.dailyTemperatures2(temperatures);
        System.out.println();
        for (int i=0;i<ret2.length;i++){
            System.out.print(ret2[i]+" ");
        }
    }
}