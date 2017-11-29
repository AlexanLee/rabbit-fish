package com.rabbit.fish.crawler;

import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by lijianli on 2017/2/15.
 */
public class Test {

    public static void main(String[] args){
        Locale[] locales=Locale.getAvailableLocales();
        for(int i=0;i<locales.length;i++){
            System.out.println(
                    "country:"+locales[i].getDisplayCountry()
                            +"\tlanguage:"+locales[i].getLanguage());
            for(int j=0;j<5;j++){
                if(j==1){
                    System.out.println("1\n");
                    continue;
                }
            }
        }

        int i=10;
        Integer integer=null;
        Integer integer1=null;
        Integer it=10;
        String it1="10";

        System.out.println(Objects.equals(it1,integer1));

        System.out.println(System.getProperty("java.io.tmpdir", "/tmp"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(System.getProperty("user.dir"));

        try {
            System.out.println(java.net.InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        double a=0.24;
        double b=0.35;
        System.out.println(a*b);
    }
}
