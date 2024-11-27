class Solution {
    public int candy(int[] ratings) {
        // 先初始化每个孩子一个糖果
        int[] candyCount = new int[ratings.length];
        for(int i=0;i<candyCount.length;i++){
            candyCount[i] = 1;
        }
        //一遍从左到右
        for(int i=1;i<ratings.length;i++){
            if(ratings[i]>ratings[i-1]){
                candyCount[i] = candyCount[i-1]+1;
            }
        }

        //一遍从右到左，这里必须要从右往左，这才能利用到前面一次遍历的结果
        for(int i=ratings.length-2;i>=0;i--){
            if(ratings[i]>ratings[i+1]){
                if(i==0){
                    candyCount[i] = candyCount[i+1]+1;
                }else{
                    // 这里要注意，这里要取最大值，因为这里的糖果数是取两次遍历的最大值
                    candyCount[i] = Math.max(candyCount[i+1]+1,candyCount[i]);
                }
            }
        }
        // 统计总糖果数
        int sum = 0;
        for(int i=0;i<candyCount.length;i++){
            sum += candyCount[i];
        }
        return sum;
    }
}