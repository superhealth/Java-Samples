package com.sh.sort;

import java.util.Arrays;

public class MainSortTest {

    public static void main(String[] args) {
        int size = 200;
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
            test4[i] = i + i%50;
        }

        //冒泡
//        testSort(new BubbleSort<Integer>(),test1,test2,test3,test4);
        //选择排序
//        testSort(new SelectSort<Integer>(),test1,test2,test3,test4);
        //插入排序
//        testSort(new InsertSort<Integer>(),test1,test2,test3,test4);
        //快速排序
        testSort(new QuickSort<Integer>(),test1,test2,test3,test4);

    }

    public static void testSort(Sort sort,  Integer[] test1,Integer[] test2,Integer[] test3,Integer[] test4) {

        System.out.println(Arrays.toString(test4));
        String sortName =sort.getClass().getSimpleName();
        System.out.println( "----------  |  -------  |   ------- ");
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        sort.sort(test1);
        endTime = System.nanoTime();
        System.out.println(sortName + "  |  正序数列  |  " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        sort.sort(test2);
        endTime = System.nanoTime();
        System.out.println(sortName + "  |  倒序数列  |  " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        sort.sort(test3);
        endTime = System.nanoTime();
        System.out.println(sortName + "  |  随机数列  |  " + (endTime - startTime) + "ns");

        startTime = System.nanoTime();
        sort.sort(test4);
        endTime = System.nanoTime();
        System.out.println(sortName + "  |  周期数列  |  " + (endTime - startTime) + "ns");

        System.out.println(Arrays.toString(test4));
    }
}
