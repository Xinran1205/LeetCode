

class Solution {
    public boolean canJump(int[] nums) {
        // 值是“最大长度”！
        // 下标加上里面的值等于新的下标，看新的下标能否大于nums.length-1
        int canJ = 0;
        for(int i=0;i<nums.length;i++){
            // 看这个位置是否能被跳到
            // 如果能被跳到
            if(i<=canJ){
                canJ = Math.max(canJ,i+nums[i]);
                // 看此时能不能跳到终点
                if(canJ>=nums.length-1){
                    return true;
                }
            }
        }
        return false;
    }
}