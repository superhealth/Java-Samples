package com.sh.sort;

import java.util.ArrayList;
import java.util.List;

public class Listsortor {

    public static void main(String[] args) {

        List<Integer> ints=new ArrayList<Integer>(){
            {
                add(5);
                add(6);
                add(7);
                add(9);
                add(10);
                add(6);
                add(9);
                add(4);
                add(1);
            }
        };

        System.out.println(getMax(ints));

    }

    public static int getMax(List<Integer> ints) {
        int max=0;
        for (int i = 0; i < ints.size(); i++) {
            for (int j = i+1; j < ints.size(); j++) {
                int temp=ints.get(i)-ints.get(j);
                System.out.println(ints.get(i)+"-"+ints.get(j)+"="+temp);
                if(max>temp) max=temp;
            }
        }
        return max;
    }


}
