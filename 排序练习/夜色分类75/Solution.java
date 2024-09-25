

//这个方法就是遇到0，换到前面，要注意left不可能是2，因为如果是2，那么就会被换到后面，所以index可以直接++
//遇到2的话，换到后面，这个时候要注意，这个数可能是0，所以index不能++
class Solution {
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int index = 0;
        while(index<=right){
            if(nums[index]==0){
                int tmp = nums[index];
                nums[index] = nums[left];
                nums[left] = tmp;
                left++;
                index++;
            }else if(nums[index]==2){
                int tmp = nums[index];
                nums[index] = nums[right];
                nums[right] = tmp;
                right--;
            }else{
                index++;
            }
        }
    }
}