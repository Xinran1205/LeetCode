
// 这个解法非常好，没有额外空间
// 从nums1和nums2的末尾开始比较，大的放在nums1的末尾，然后依次向前移动

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] ret = new int[m+n];
        int i=m-1;
        int j=n-1;
        int index=nums1.length-1;
        while(i>=0&&j>=0){
            if(nums1[i]>nums2[j]){
                nums1[index] = nums1[i];
                index--;
                i--;
            }else{
                nums1[index] = nums2[j];
                index--;
                j--;
            }
        }
        while(i>=0){
            nums1[index] = nums1[i];
            index--;
            i--;
        }
        while(j>=0){
            nums1[index] = nums2[j];
            index--;
            j--;
        }
    }
}
