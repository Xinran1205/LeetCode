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
    public void quickSort(int[] nums, int left, int right){
        if(left<=right){
            int pivotIndex = left + (int)(Math.random() * (right - left + 1));
            int tmpe = nums[pivotIndex];
            nums[pivotIndex] = nums[left];
            nums[left] = tmpe;
            int pivot = nums[left];
            int i = left;
            int j = right;
            // 这里等于i或者i+1都可以
            // 思考一下三色旗就懂了，这个pivot就是1，假设我们定义left是pivot，那其实等于第一个直接处理过了
            // 直接从第二个即可
            int index = i;
            while(index<=j){
                if(nums[index]==pivot){
                    index++;
                }else if(nums[index]<pivot){
                    int tmp = nums[index];
                    nums[index] = nums[i];
                    nums[i] = tmp;
                    index++;
                    i++;
                }else{
                    int tmp = nums[index];
                    nums[index] = nums[j];
                    nums[j] = tmp;
                    j--;
                }
            }
            quickSort(nums, j + 1, right);
            quickSort(nums, left, i - 1);
        }
    }
}