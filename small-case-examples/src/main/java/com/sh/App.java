package com.sh;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        BigDecimal rateOnlyEstimate = new BigDecimal("1.1");
        BigDecimal estimate = new BigDecimal("1.2");

        System.out.println(rateOnlyEstimate.compareTo(estimate));
        BigDecimal xrsBasePrice = rateOnlyEstimate.compareTo(estimate)==-1?rateOnlyEstimate:estimate;

//        System.out.println(xrsBasePrice);

//        System.out.println("000000000000000011111111111111111111111111111110".charAt(0));
//        //23:13
//        System.out.println('1' == "000000000000000011111111111111111111111111111110".charAt(23 * 2));
//        //23:45
//        System.out.println('1' == "000000000000000011111111111111111111111111111111".charAt(23 * 2+1));
//        // 7:58
//        System.out.println('1' == "000000000000000011111111111111111111111111111111".charAt(7 * 2+1));

        String openTime="100000000000111111111111111111111111111111111111";
        int opentStart = openTime.indexOf("1");
        int closeStart = openTime.lastIndexOf("1");

        if (opentStart == 47) {
            System.out.println("23:59");
        } else if (opentStart >= 0) {
            System.out.println("open:"+(opentStart / 2) + (opentStart % 2 == 0 ? ":00" : ":30"));
        }

        if (closeStart == 47) {
            System.out.println("23:59");
        } else if (closeStart >= 0) {
            System.out.println("open:"+((closeStart+1) / 2) + ((closeStart+1) % 2 == 0 ? ":00" : ":30"));
        }

        int hour=0;
        boolean isOpen = ('1' == (hour == 0 ? openTime.charAt(0)
                : openTime.charAt(hour * 2 - 1))
                || '1' == openTime.charAt(hour * 2));

        System.out.println(isOpen);

        List<String> list1=new ArrayList<>();
        list1.add("ccar");
        list1.add("cdar");
        list1.add("cfar");

        List<String> list2=new ArrayList<>();
        list2.add("ccar");
        list2.add("cdar");

        list2.retainAll(list1);

        System.out.println(list1);
        System.out.println(list2);
    }
}
