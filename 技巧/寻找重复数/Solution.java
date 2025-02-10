
// 这个题目非常非常的聪明，利用了快慢指针的思想，找到了环的入口，然后找到了重复的数字，和那个环形链表的题目一样
// 难点在抽象
class Solution {
    public int findDuplicate(int[] nums) {
        // 阶段 1：找相遇点
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];           // 慢指针走一步
            fast = nums[nums[fast]];       // 快指针走两步
        } while (slow != fast);

        // 阶段 2：找环的入口
        slow = 0;  // 重置慢指针到起点
        while (slow != fast) {
            slow = nums[slow];  // 两指针都走一步
            fast = nums[fast];
        }
        return slow;  // 相遇点就是重复的数字
    }
}
