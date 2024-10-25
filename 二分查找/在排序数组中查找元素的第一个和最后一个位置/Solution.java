


class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length==0){
            return new int[]{-1,-1};
        }
        int i = 0;
        int j = nums.length;
        while(i<j){
            int mid = (j-i)/2+i;
            if(nums[mid]<target){
                i = mid+1;
            }else{
                j = mid;
            }
        }
        if(i==nums.length||nums[i]!=target){
            return new int[]{-1,-1};
        }
        int left = i;

        i = 0;
        j = nums.length-1;
        while(i<j){
            int mid = (j-i+1)/2+i;
            if(nums[mid]<=target){
                i = mid;
            }else{
                j = mid-1;
            }
        }
        int right = j;
        return new int[]{left,right};
    }
}