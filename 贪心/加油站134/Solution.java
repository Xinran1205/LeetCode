class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 要理解这一句话，如果从i可以到达j，但是到达不了j+1，
        // 那么i到j之间的点都到达不了j+1
        // 原因，因为假如从i可以到达i+1，之前余的油量一定是大于等于0的。如果从i+1开始，油量只有当前的。

        //思路，暴力！遍历点（不需要每个，跳过i和j之间的点），对每个点看他最远能到的

        int i = 0;
        while(i<gas.length){
            int j = 0;
            int sumOil = 0;
            int costOil = 0;
            while(j<gas.length){
                int Index = (i+j)%gas.length;
                sumOil = sumOil + gas[Index];
                costOil = costOil + cost[Index];
                if(sumOil<costOil){
                    break;
                }
                j++;
            }
            if(j==gas.length){
                return i;
            }else{
                i = i + j + 1;
            }
        }
        return -1;
    }
}

// 究极封神优化代码！
class Solution2 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 换一个思路，关注每个站净增（gas-cost）（对于每个站点剩余的油）

        // 累加如果curSum为负，说明不管从中间哪都到不了这一站，所以从这一站的下一站开始

        // 这个写法只需要一遍遍历

        // totalSum我们可以直接把所有站点的净增加起来，如果小于0，说明总的净增小于0，无法走完全程
        // 总油耗平衡：如果你在一条环路上行驶，整个旅程的油量总和必须至少等于总的消耗量。换句话说，你车里所有油的总和（gas[i]的总和）
        // 必须至少等于你在整个旅程中消耗的油量（cost[i]的总和）。如果总油量小于总消耗量，那么没有足够的油完成整个旅程。

        int curSum = 0;
        int totalSum = 0;
        int ret = 0;
        for(int i=0;i<gas.length;i++){
            curSum+=gas[i]-cost[i];
            totalSum+=gas[i]-cost[i];
            if(curSum<0){
                ret = i+1;
                curSum = 0;
            }
        }
        if(totalSum<0){
            return -1;
        }else{
            return ret;
        }
    }
}