package com.sh;

import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
//        BigDecimal rateOnlyEstimate = new BigDecimal("1.1");
//        BigDecimal estimate = new BigDecimal("1.2");
//
//        System.out.println(rateOnlyEstimate.compareTo(estimate));
//        BigDecimal xrsBasePrice = rateOnlyEstimate.compareTo(estimate)==-1?rateOnlyEstimate:estimate;

//        System.out.println(xrsBasePrice);

//        System.out.println("000000000000000011111111111111111111111111111110".charAt(0));
//        //23:13
//        System.out.println('1' == "000000000000000011111111111111111111111111111110".charAt(23 * 2));
//        //23:45
//        System.out.println('1' == "000000000000000011111111111111111111111111111111".charAt(23 * 2+1));
//        // 7:58
//        System.out.println('1' == "000000000000000011111111111111111111111111111111".charAt(7 * 2+1));
//
//        String openTime="100000000000111111111111111111111111111111111111";
//        int opentStart = openTime.indexOf("1");
//        int closeStart = openTime.lastIndexOf("1");
//
//        if (opentStart == 47) {
//            System.out.println("23:59");
//        } else if (opentStart >= 0) {
//            System.out.println("open:"+(opentStart / 2) + (opentStart % 2 == 0 ? ":00" : ":30"));
//        }
//
//        if (closeStart == 47) {
//            System.out.println("23:59");
//        } else if (closeStart >= 0) {
//            System.out.println("open:"+((closeStart+1) / 2) + ((closeStart+1) % 2 == 0 ? ":00" : ":30"));
//        }
//
//        int hour=0;
//        boolean isOpen = ('1' == (hour == 0 ? openTime.charAt(0)
//                : openTime.charAt(hour * 2 - 1))
//                || '1' == openTime.charAt(hour * 2));
//
//        System.out.println(isOpen);
//
//        List<String> list1=new ArrayList<>();
//        list1.add("ccar");
//        list1.add("cdar");
//        list1.add("cfar");
//
//        List<String> list2=new ArrayList<>();
//        list2.add("ccar");
//        list2.add("cdar");
//
//        list2.retainAll(list1);
//d
//        System.out.println(list1);
//        System.out.println(list2);


        String json="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<OTA_PingRQ xmlns=\"http://www.opentravel.org/OTA/2003/05\"\n" +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "xsi:schemaLocation=\"http://www.opentravel.org/OTA/2003/05 OTA_PingRQ.xsd\"> <EchoData>Test Ping</EchoData>\n" +
                "</OTA_PingRQ>";
        try {

            byte[] _byte=compress(json,"UTF-8");

            System.out.println(new String(_byte,"UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 压缩字符串
     * @param str
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static byte[] compress(String str,String charset) throws IOException, UnsupportedEncodingException {
        Assert.notNull(str, " null compress error ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        try{
            gzip.write(str.getBytes(charset));
            gzip.close();
            return  out.toByteArray();
        }catch(Exception e){
            throw new IOException(e);
        }finally{

        }
    }

}
