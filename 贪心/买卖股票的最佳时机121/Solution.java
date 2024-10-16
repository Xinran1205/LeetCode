// 一次遍历，维护一个最低的价格，然后对于每一天，都考虑抛出能赚多少钱
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int max = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]<=minPrice){
                minPrice = prices[i];
            }else{
                int p = prices[i] - minPrice;
                if(p>max){
                    max = p;
                }
            }
        }
        return max;
    }
}