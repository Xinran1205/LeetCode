// 基数排序

//如何处理负数，可以把负数和正数分开，然后分别排序，负数取绝对值排序，然后倒过来，最后合并
//如何处理小数，找到最大的小数位数，然后乘以10的这个位数次方，然后排序

class Solution {
    public int[] sortArray(int[] nums) {
        int digit = getDigit(nums);
        int[] output = new int[nums.length];
        int exp = 1;
        for(int i=0;i<digit;i++){
            //使用计数排序
            int[] count = new int[10];
            for(int j=0;j<nums.length;j++){
                int val = nums[j];
                int num = (val/exp)%10;
                count[num] = count[num]+1;
            }
            // 把count数组变成count[1]等于4代表：为1的数最后一个排在第四位。
            for(int k=1;k<10;k++){
                count[k] = count[k]+count[k-1];
            }
            for(int q=nums.length-1;q>=0;q--){
                int val = nums[q];
                int num = (val/exp)%10;
                output[count[num]-1] = val;
                count[num]--;
            }
            exp*=10;
        }
        return output;
    }
    public int getDigit(int[] nums){
        //getmax
        int max = nums[0];
        for(int i=0;i<nums.length;i++){
            if(nums[i]>max){
                max = nums[i];
            }
        }
        int digit = 0;
        while(max>0){
            max = max/10;
            digit++;
        }
        return digit;
    }
}