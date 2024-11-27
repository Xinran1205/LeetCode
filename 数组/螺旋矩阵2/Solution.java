class Solution {
    //保持左闭右开，把最后一个节点作为下一条边处理
    public int[][] generateMatrix(int n) {
        //这个返回值很有意思，其实就定义一个空的，然后遍历就是往里面填值
        //最后返回这个值即可
        int offset = 1;
        int[][] ret = new int[n][n];
        int num = 1, tar = n * n;
        // 这个offset控制不难，反正只要保证左闭右开就可以
        // 这个while循环终止条件要注意，偶数没关系，奇数要单独处理中心
        while(num<tar){
            int i;
            int j;
            //左到右
            for(j=offset-1;j<n-offset;j++){
                ret[offset-1][j] = num;
                num++;
            }
            //上到下
            for(i=offset-1;i<n-offset;i++){
                ret[i][j] = num;
                num++;
            }
            // 此时i和j的值都是n-offset
            for(;j>offset-1;j--){
                ret[i][j] = num;
                num++;
            }
            for(;i>offset-1;i--){
                ret[i][j] = num;
                num++;
            }
            offset++;
        }
        // 填充中心位置！
        if(n % 2 != 0){
            ret[offset-1][offset-1] = num;
        }
        return ret;
    }
}

// 方法2，我觉得更好理解！！！！
// 左闭右闭！！！！
class Solution2 {
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];

        int top = 0;
        int bottom = n-1;
        int left = 0;
        int right = n-1;

        int sum = 1;
        int target = n*n;
        while(sum<target+1){
            int i;
            int j;
            for(j=left;j<=right;j++){
                ret[top][j] = sum;
                sum++;
            }
            top++;
            for(i=top;i<=bottom;i++){
                ret[i][right] = sum;
                sum++;
            }
            right--;
            for(j=right;j>=left;j--){
                ret[bottom][j] = sum;
                sum++;
            }
            bottom--;
            for(i=bottom;i>=top;i--){
                ret[i][left] = sum;
                sum++;
            }
            left++;
        }
        return ret;
    }
}