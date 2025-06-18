import java.util.*;
class Solution {
    public String simplifyPath(String path) {
        String[] parts = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for(int i=0;i<parts.length;i++){
            if(parts[i].equals("")||parts[i].equals(".")){
                continue;
            }else if(parts[i].equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else{
                stack.push(parts[i]);
            }
        }
        // 3) 如果栈空，返回根目录
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            // 之所以要 insert(0, …) 而不是 append，是因为栈里目录是「从最深处到根」的顺序，
            // 弹出的第一个其实是最里层的目录，比如你要最后得到 "/a/b/c"，栈顶先拿到的是 "c"，
            // 放到空字符串前面就成 "/c"；接着拿 "b"，再插到最前面，就变成 "/b/c"；
            // 最后拿 "a"，插到最前面就得到 "/a/b/c"。
            sb.insert(0, "/" + stack.pop());
        }
        return sb.toString();
    }
}