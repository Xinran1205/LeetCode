//首先快速排序
//选择pivot一共有三种，第一种最孬，选择最左边的。
// 第二种随机选，然后换到最左边

// 第三种使用3指针，这个和荷兰国旗完全一样（三个颜色，大于等于小于）！！！
// 这个方法对有大量重复元素的情况非常有效！

// 同时，要知道退化成插入排序

import java.util.Random;

class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    public void quickSort(int[] nums,int left,int right){
        if (left>=right){
            return;
        }

        Random random = new Random();
        int pivotIndex = random.nextInt(right-left+1)+left;
        int pivot = nums[pivotIndex];
        nums[pivotIndex] = nums[left];
        nums[left] = pivot;

        int index = left;
        int ls = left;
        int gt = right;
        while(index<=gt){
            if(nums[index]<pivot){
                int tmp = nums[index];
                nums[index] = nums[ls];
                nums[ls] = tmp;
                ls++;
                index++;
            }else if(nums[index]==pivot){
                index++;
            }else{
                int tmp = nums[index];
                nums[index] = nums[gt];
                nums[gt] = tmp;
                gt--;
            }
        }

        quickSort(nums,left,ls-1);
        quickSort(nums,gt,right);
    }
}

