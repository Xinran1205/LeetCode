



// 标准二分查找，这个比较骚，虽然难以理解，但是很骚，插入位置return i即可


class Solution {
    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length;   //注意
        // 这个写法就是左闭右开区间，所以不能等于，思考[1,1)即可，这时候退出循环，无意义
        while(i<j){            //注意
            int mid = (j-i)/2+i;
            if (nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){  //注意
                // 因为nums[mid]已经是小于target的了，所以他不属于搜索空间，所以i=mid+1
                i = mid+1;               //注意
            }else{
                j = mid;                 //注意
            }
        }
        return -1;
    }
}

// 搜索插入位置
class Solution2 {
    public int searchInsert(int[] nums, int target) {
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
        return i;
    }
}

// 这个是图片1的写法，可以参考他B站的视频
// 这个方法只需要调整返回的值和if(nums[mid]<target)的符号即可解决多种问题
class Solution3 {
    public int searchInsert(int[] nums, int target) {
        int i = -1;
        int j = nums.length;
        while(i+1!=j){
            int mid = (j-i)/2+i;
            if(nums[mid]<target){
                i = mid;
            }else{
                j = mid;
            }
        }
        return j;
    }
}