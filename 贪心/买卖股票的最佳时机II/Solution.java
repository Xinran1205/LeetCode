class Solution {

    // 这句话太神了，在纸上面画一下就懂了！
    // 可以把股票的价格波动画出来，单调上升就是盈利，题解就是每一段单调上升的总和

    public int maxProfit(int[] prices) {
        // 封神，p[3] - p[0] 等于p[3]-p[2]+p[2]-p[1]+p[1]-p[0]
        // 所以计算每天的差值（每天利润），不保留负数，保留正数

        // 例如 7 1 5 10 3 6 4
        // 差值为 -6 4 5 -7 3 -2
        // 答案就是4+5+3=12，其实4+5就代表在第一天买入，第三天卖出（10-1=9）
        int sum = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]-prices[i-1]>0){
                sum+=prices[i]-prices[i-1];
            }
        }
        return sum;
    }
}