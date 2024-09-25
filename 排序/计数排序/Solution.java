class Solution {
    public int[] sortArray(int[] nums) {
        // 计数排序
        int max = getMax(nums);
        int[] bucket = new int[max+1];
        for(int i=0;i<nums.length;i++){
            bucket[nums[i]] = bucket[nums[i]] + 1;
        }
        int j = 0;
        for(int i=0;i<bucket.length;i++){
            while(bucket[i]>0){
                nums[j] = i;
                bucket[i] = bucket[i] - 1;
                j++;
            }
        }
        return nums;
    }

    public int getMax(int[] nums){
        int max = nums[0];
        for(int i=0;i<nums.length;i++){
            if(nums[i]>max){
                max = nums[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {5,2,3,1};
        int[] res = solution.sortArray(nums);
        for(int i=0;i<res.length;i++){
            System.out.print(res[i] + " ");
        }
    }
}
