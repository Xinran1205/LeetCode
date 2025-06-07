import java.util.*;

class Solution {

    // 如果我们将三条边进行升序排序，使它们满足 a≤b≤c，那么 a+c>b 和 b+c>a 使一定成立的，我们只需要保证 a+b>c。

    // 因此，我们可以将数组 nums 进行升序排序，随后使用二重循环枚举 a 和 b。设 a=nums[i],b=nums[j]，为了防止重复统计答案，我们需要保证 i<j。

    // 剩余的边 c 需要满足 c<nums[i]+nums[j]，我们可以在 [j+1,n−1] 的下标范围内使用二分查找（其中 n 是数组 nums 的长度），找出最大的满足 nums[k]<nums[i]+nums[j] 的下标 k，这样一来，在 [j+1,k] 范围内的下标都可以作为边 c 的下标，我们将该范围的长度 k−j 累加入答案。

    public int triangleNumber(int[] nums) {
        int ret = 0;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            for(int j=i+1;j<nums.length-1;j++){
                int left = j+1;
                int right = nums.length-1;
                int k = j;
                while(left<=right){
                    int mid = (right-left)/2+left;
                    if(nums[mid]<nums[i]+nums[j]){
                        k = mid;
                        left = mid+1;
                    }else{
                        right = mid-1;
                    }
                }
                ret+=k-j;
            }
        }
        return ret;
    }
}