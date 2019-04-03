package com.sh.sort;

/**
 * 插入排序,稳定排序
 * 平均情况下插入排序需要 ~N2/4 比较以及 ~N2/4 次交换；
 * 最坏的情况下需要 ~N2/2 比较以及 ~N2/2 次交换，最坏的情况是数组是倒序的；
 * 最好的情况下需要 N-1 次比较和 0 次交换，最好的情况就是数组已经有序了
 * 空间O(1)
 */
public class InsertSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            //对前一次已排好的序列进行插入
            for (int j = i ; j >0 && less(nums[j], nums[j-1]); j--) {
                    swap(nums, j, j-1);
            }

        }
    }
}
