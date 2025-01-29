
// 这个题目非常非常的聪明，利用了快慢指针的思想，找到了环的入口，然后找到了重复的数字，和那个环形链表的题目一样
// 难点在抽象
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];

        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // // 第二步：找到环的入口
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
