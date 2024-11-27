class Solution {
    public int jump(int[] nums) {
        if(nums.length==1){
            return 0;
        }
        int cur = 0;
        // next表示下一步能到达的最远位置
        int next = 0;
        int pace = 0;
        for(int i=0;i<nums.length;i++){
            next = Math.max(next,i+nums[i]);
            // 如果i==cur，说明已经到达了当前步数能到达的最远位置，需要更新能到达的最远位置并且步数加1
            if(i==cur){
                if(cur>=nums.length-1){
                    return pace;
                }else{
                    pace++;
                    cur = next;
                }
            }
        }
        return pace;
    }
}