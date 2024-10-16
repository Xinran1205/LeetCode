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