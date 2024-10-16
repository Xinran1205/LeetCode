//修改2是最终正确代码
// 参考讲解，代码随想录：https://www.bilibili.com/video/BV17M411b7NS/?spm_id_from=333.337.search-card.all.click&vd_source=dcab574a8cf2a1191171255ffb4d513a


// 这个代码没有考虑单调平坡，比如122224，只返回2。
class Solution {
    public int wiggleMaxLength(int[] nums) {
        // 单调坡保留峰值！！！！其他全部删去（不需要真删）
        // ret值遇到摆动++，遇到坡不++
        // 如何判断是不是摆动，pre小于cur，并且cur大于next

        // 默认最右边一个摆动
        // 开头延生一个平坡，这两个步骤可以处理首尾边界条件
        int ret = 1;
        for(int i=0;i<nums.length-1;i++){
            int pre = 0;
            if(i==0){
                pre = nums[i];
            }else {
                pre = nums[i - 1];
            }
            int cur = nums[i];
            int next = nums[i+1];
            if(cur>=pre&&cur>next ||
                    cur<=pre&&cur<next){
                ret++;
            }
        }
        return ret;
    }
}

// 修改1，先把上面代码改成下面这个格式
class Solution2 {
    public int wiggleMaxLength(int[] nums) {
        int ret = 1;
        int predif = 0;
        int curdif;
        for(int i=0;i<nums.length-1;i++){
            curdif = nums[i]-nums[i+1];
            // 注意这里判断条件和上面有区别，因为正负关系
            if(predif>=0&&curdif<0 ||
                    predif<=0&&curdif>0){
                ret++;
            }
            // 每次更新predif为curdif
            predif = curdif;
        }
        return ret;
    }
}


//修改2，这是最终代码
class Solution3 {
    public int wiggleMaxLength(int[] nums) {
        // 单调坡保留峰值！！！！其他全部删去（不需要真删）
        // ret值遇到摆动++，遇到坡不++
        // 如何判断是不是摆动，pre小于cur，并且cur大于next

        // 默认最右边一个摆动
        // 开头延生一个平坡，这两个步骤可以处理首尾边界条件
        int ret = 1;
        int predif = 0;
        int curdif = 0;
        for(int i=0;i<nums.length-1;i++){
            curdif = nums[i]-nums[i+1];
            if(predif>=0&&curdif<0 ||
                    predif<=0&&curdif>0){
                ret++;
                predif = curdif;
            }
        }
        return ret;
    }
}

// 动态规划，贼短，但是没怎么看懂，图能看懂，式子看不懂
class Solution4 {
    public int wiggleMaxLength(int[] nums) {
        int up = 1;
        int down = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                up = down+1;
            }else if(nums[i]<nums[i-1]){
                down = up+1;
            }
        }
        return Math.max(up,down);
    }
}