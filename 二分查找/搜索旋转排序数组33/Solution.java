class Solution {
    public int search(int[] nums, int target) {
        // 先找到pivot，这里就用二分就可以，但是想法其实有点绕
        // 参考这道题：寻找旋转排序数组中的最小值
        int i = 0;
        int j = nums.length-1;
        while(i<j){
            int mid = (j-i)/2+i;
            if(nums[mid]<nums[nums.length-1]){
                j = mid;
            }else{
                i = mid + 1;
            }
        }
        // 此时i是pivot
        // 看在左边还是右边
        int left = 0;
        int right = i;
        if(target<=nums[nums.length-1]){
            left = i;
            right = nums.length;
        }

        while(left<right){
            int mid = (right-left)/2+left;
            if (nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return -1;
    }
}


// 方法2，直接二分
class Solution2 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = (right-left)/2+left;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>=nums[left]){
                //代表左半部分有序
                if(target>=nums[left]&&target<nums[mid]){
                    // target在左半部分
                    right = mid-1;
                }else{
                    //在右半部分
                    left = mid+1;
                }
                // 必定有一侧是有序的
            }else{
                // 代表右半部分有序
                if(target<=nums[right]&&target>nums[mid]){
                    // target在右半部分
                    left = mid+1;
                }else{
                    //在左半部分
                    right = mid;
                }
            }
        }
        return -1;
    }
}