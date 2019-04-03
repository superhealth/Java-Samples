package com.sh.sort;

/**
 * 快速排序
 *
 * 它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行，以此达到整个数据变成有序序列
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int length = nums.length;

        quickSort(nums,0,length-1);
    }

    public void quickSort(T[] nums,int left,int right){
        if(left<right){
            //获取枢纽值，并将其放在当前待处理序列末尾
            selectKeyValue(nums, left, right);
            //枢纽值被放在序列末尾
            int keyPoint = right - 1;
            //左指针
            int i = left;
            //右指针
            int j = right - 1;

            while (true){
                while (less(nums[++i],nums[keyPoint])){

                }
                while (j>left && less(nums[keyPoint],nums[--j])){

                }
                if(i<j){
                    swap(nums,i,j);
                }else {
                    break;
                }
            }
            if(i<right){
                swap(nums,i,right-1);
            }

            quickSort(nums,left,i-1);
            quickSort(nums,i+1,right);
        }
    }

    /**
     *  枢纽元选取策略 三数中值分割法 还可以使用随机选取
     */
    public void selectKeyValue(T[] nums,int left,int right){
        int center=(left+right)/2;
        if(less(nums[center],nums[left])){
            swap(nums,center,left);
        }

        if(less(nums[right],nums[left])){
            swap(nums,right,left);
        }

        if(less(nums[right],nums[center])){
            swap(nums,right,center);
        }


        swap(nums,right-1,center);
    }
}
