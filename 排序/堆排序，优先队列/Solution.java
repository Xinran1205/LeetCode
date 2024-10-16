// 1.实现堆排序
// 2.利用堆排序实现优先队列！

class Solution1 {
    public int[] sortArray(int[] nums) {
        // 这个代码可以大小可以传nums.length-1，也可以传大小nums.length
        // 取决于边界如何判断
        for(int i = nums.length/2-1; i>=0; i--){
            adjustHeap(nums, i, nums.length-1);
        }
        // 直接原地交换，最大的放到最后
        // 因为上面已经构造了一遍了，第一个已经是最大的了
        // 所以先交换，并且从倒数第二个开始构造堆
        int len = nums.length-2;
        for(int i = nums.length-1; i>0; i--) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            adjustHeap(nums, 0, len);
            len--;
        }
        return nums;
    }

    public void adjustHeap(int[] nums,int index,int len){
        int largestIndex = index;
        if(2*index+1<=len&&nums[2*index+1]>nums[largestIndex]){
            largestIndex = 2*index+1;
        }
        if(2*index+2<=len&&nums[2*index+2]>nums[largestIndex]){
            largestIndex = 2*index+2;
        }
        if(largestIndex!=index){
            //交换
            int tmp = nums[index];
            nums[index] = nums[largestIndex];
            nums[largestIndex] = tmp;
            adjustHeap(nums,largestIndex,len);
        }
    }
}

class PriorityQueue {
    int[] heap;
    int size;
    int capacity;

    PriorityQueue(int capacity){
        this.capacity = capacity;
        size = 0;
        heap = new int[capacity];
    }

    // 插入元素
    public boolean insert(int val){
        if(size>=capacity){
            return false;
        }
        heap[size] = val;
        //上浮
        swim(size);
        size++;
        return true;
    }

    //返回最大的元素，不删除
    public int getMax(){
        if (size == 0) {
            throw new IllegalStateException("优先队列为空");
        }
        return heap[0];
    }

    //返回最大的元素，并删除
    public int getDeleteMax(){
        if (size == 0) {
            throw new IllegalStateException("优先队列为空");
        }
        int max = heap[0];
        heap[0] = heap[size-1];
        size--;
        adjustHeap(0);
        return max;
    }

    public void swim(int index){
        if(index<1){
            return;
        }
        int parent = (index-1)/2;
        while(index>0&&heap[parent]<heap[index]){
            int tmp = heap[index];
            heap[index] = heap[parent];
            heap[parent] = tmp;
            index = parent;
            parent = (parent-1)/2;
        }
    }

    public void adjustHeap(int index){
        int largestIndex = index;
        int left = 2*index+1;
        int right = 2*index+2;
        if(left<size&&heap[left]>heap[largestIndex]){
            largestIndex = left;
        }
        if(right<size&&heap[right]>heap[largestIndex]){
            largestIndex = right;
        }
        if(largestIndex!=index){
            int tmp = heap[largestIndex];
            heap[largestIndex] = heap[index];
            heap[index] = tmp;
            adjustHeap(largestIndex);
        }
    }
}

//test priority queue
class TestPriorityQueue{
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(10);
        priorityQueue.insert(1);
        priorityQueue.insert(2);
        priorityQueue.insert(3);
        priorityQueue.insert(4);
        priorityQueue.insert(5);
        priorityQueue.insert(6);
        priorityQueue.insert(7);
        priorityQueue.insert(8);
        priorityQueue.insert(9);
        priorityQueue.insert(10);
        System.out.println(priorityQueue.getMax());
        System.out.println(priorityQueue.getDeleteMax());
        System.out.println(priorityQueue.getMax());
    }
}