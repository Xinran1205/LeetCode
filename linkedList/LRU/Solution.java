import java.util.*;

class LRUCache {
    // 1. 首先这个node也要定义key，即使我们用不到，但是他属于node的一部分
    class Node{
        int value;
        int key;
        Node prev;
        Node next;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    // 2. 全局的属性，hashMap用来存储key和node的映射，capacity是最大容量，head和tail是虚拟节点
    Map<Integer,Node> hashMap;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        hashMap = new HashMap<Integer,Node>();

        head = new Node(0,0);
        tail = new Node(0,0);

        head.next = tail;
        tail.prev = head;
    }

    // 这个函数是用来插入一个新的node到head的后面
    private void addToHead(Node node){
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    // 这个是移动现有的node到head的后面，要先把node从原来的位置删除，然后再插入到head的后面
    private void moveToHead(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

        addToHead(node);
    }

    private Node removeTail(){
        Node remove = tail.prev;
        remove.prev.next = tail;
        tail.prev = remove.prev;

        remove.next = null;
        remove.prev = null;
        return remove;
    }

    public int get(int key) {
        if(hashMap.containsKey(key)){
            Node tmp = hashMap.get(key);
            moveToHead(tmp);
            return tmp.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(hashMap.containsKey(key)){
            Node tmp = hashMap.get(key);
            moveToHead(tmp);
            tmp.value = value;
        }else{
            Node newNode = new Node(key,value);
            hashMap.put(key,newNode);
            addToHead(newNode);
            if(hashMap.size()>capacity){
                Node removed = removeTail();
                hashMap.remove(removed.key);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */