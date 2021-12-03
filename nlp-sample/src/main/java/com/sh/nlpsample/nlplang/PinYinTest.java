package com.sh.nlpsample.nlplang;


import org.nlpcn.commons.lang.pinyin.Pinyin;

public class PinYinTest {

    public static void main(String[] args) {

        System.out.println("pinyin:"+Pinyin.pinyin("平安银行"));
        System.out.println("pinyin:"+Pinyin.multiplePinyin("蔚来"));

        System.out.println("multiplePinyin:"+Pinyin.multiplePinyin("蔚来"));

        System.out.println("multiplePinyin:"+Pinyin.multiplePinyin("平安银行"));

        System.out.println("MultipTonePinyin:"+Pinyin.MultipTonePinyin("平安银行"));
        System.out.println("multipleFirstChar:"+Pinyin.MultipTonePinyin("安信证券"));


        System.out.println("multiplePinyin:"+Pinyin.multiplePinyin("单"));
        System.out.println("multiplePinyin:"+Pinyin.multiplePinyin("缪论"));

    }
}
