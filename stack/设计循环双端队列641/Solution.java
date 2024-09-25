// 假如题目没有双端，还是可以直接用以下代码，没有双端就意味着比如说queue，只能从头部删除，尾部插入

// 方法1. 用数组实现
class Solution {
    // front和rear很重要，capacity+1也很重要
    // 因为需要判断是空还是满了的条件
    // front==rear为空，rear代表下一个后续插入的位置，如果（rear+1）%capacity==front，代表插满了
    int[] deque;
    int front,rear,capacity;

    public Solution(int k) {
        deque = new int[k+1];
        front = 0;
        rear = 0;
        capacity = k+1;
    }

    public boolean insertFront(int value) {
        if (isFull()){
            return false;
        }
        int index = (front-1+capacity)%capacity;
        deque[index] = value;
        front = index;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()){
            return false;
        }
        deque[rear] = value;
        rear = (rear+1)%capacity;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()){
            return false;
        }
        front = (front+1)%capacity;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()){
            return false;
        }
        rear = (rear-1+capacity)%capacity;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return deque[front];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return deque[(rear-1+capacity)%capacity];
    }

    public boolean isEmpty() {
        if (front==rear){
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if ((rear+1)%capacity == front){
            return true;
        }
        return false;
    }
}

// 方法2，这个用来练手的，链表实现。
class MyCircularDeque {

    private class List{
        int val;
        List prev, next;
        List(int val){
            this.val = val;
        }
    }

    private List head, tail;
    private int capacity;
    private int size;

    public MyCircularDeque(int k) {
        capacity = k;
        size = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()){
            return false;
        }
        List newNode = new List(value);
        if (size==0){
            head = tail = newNode;
        }else{
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()){
            return false;
        }
        List newNode = new List(value);
        if (size==0){
            head = tail = newNode;
        }else{
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()){
            return false;
        }
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()){
            return false;
        }
        tail = tail.prev;
        if (tail!=null){
            tail.next = null;
        }
        size--;
        return true;
    }

    public int getFront() {
        if (isEmpty()){
            return -1;
        }
        return head.val;
    }

    public int getRear() {
        if (isEmpty()){
            return -1;
        }
        return tail.val;
    }

    public boolean isEmpty() {
        if (size==0){
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if(size==capacity){
            return true;
        }
        return false;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
