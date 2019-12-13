package com.sh.sort;

/**
 * 冒泡排序,稳定排序
 * 时间最好O(n),最坏O(n2),平均O(n2)
 * 空间O(1)
 */
public class BubbleSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (less(nums[j + 1], nums[j])) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    /**
     * 变量介入
     * @param nums
     */
    public void sort1(T[] nums) {
        int length = nums.length;
        boolean hasSorted = false;
        for (int i = 0; i < length - 1 && !hasSorted; i++) {
            hasSorted = true;
            for (int j = 0; j < length - 1 - i; j++) {
                if (less(nums[j + 1], nums[j])) {
                    hasSorted = false;
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int size = 200;
        BubbleSort<Integer> sort = new BubbleSort<>();
        sort.test("BubbleSort", size);
    }

    public void test(String sortName, int size) {
        Integer[] test1 = new Integer[size];
        Integer[] test2 = new Integer[size];
        Integer[] test3 = new Integer[size];
        Integer[] test4 = new Integer[size];
        // 正序
        for (int i = 0; i < size; i++) {
            test1[i] = i;
        }
        // 倒序
        for (int i = 0; i < size; i++) {
            test2[i] = size - i;
        }
        // 乱序,随机,基本无重复元素
        for (int i = 0; i < size; i++) {
            test3[i] = (int) (Math.random() * size);
        }
        // 大量重复元素
        for (int i = 0; i < size; i++) {
            test4[i] = i + 50 % 50;
        }

        BubbleSort<Integer> sort = new BubbleSort<>();

        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        sort.sort1(test1);
        endTime = System.nanoTime();
        System.out.println(sortName + "正序数列:" + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        sort.sort1(test2);
        endTime = System.nanoTime();
        System.out.println(sortName + "倒序数列:" + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        sort.sort1(test3);
        endTime = System.nanoTime();
        System.out.println(sortName + "随机数列:" + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        sort.sort1(test4);
        endTime = System.nanoTime();
        System.out.println(sortName + "周期数列:" + (endTime - startTime) + "ns");

        System.out.println();
    }
}
