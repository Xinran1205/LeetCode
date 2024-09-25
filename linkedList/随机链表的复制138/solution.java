
import java.util.HashMap;
//138 随机链表的复制

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class solution {
    public Node copyRandomList(Node head) {
        // 第一遍遍历head，遍历的同时拷贝，拷贝完再放入hash表中
        Node firstIter =  head;
        Node DummyNode =  new Node(0);
        Node newList = DummyNode;
        // 根据老Node返回新Node
        HashMap<Node, Node> map = new HashMap<>();
        while (firstIter!=null){
            Node newNode = new Node(firstIter.val);
            newList.next = newNode;
            newList = newList.next;
            map.put(firstIter,newNode);
            firstIter = firstIter.next;
        }
        Node NewListIter = DummyNode.next;
        // 第二遍遍历,对head的每位，拿到他的random，然后操作新的链表
        while (head!=null){
            NewListIter.random = map.get(head.random);
            NewListIter = NewListIter.next;
            head = head.next;
        }
        return DummyNode.next;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        head.next = node2;
        head.random = node3;
        node2.next = node3;
        node2.random = head;
        node3.random = node2;
        solution s = new solution();
        Node res = s.copyRandomList(head);
        while (res!=null){
            System.out.println(res.val);
            res = res.next;
        }
    }
}
