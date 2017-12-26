package com.rabbit.fish.exp;

import java.math.BigInteger;
import java.net.URL;

/**
 * Created by lijianli on 2017/10/14.
 */
public class Exp {

    public static void main(String []args){
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
        BigInteger a= BigInteger.valueOf(Integer.MAX_VALUE);
//        System.out.println(a.multiply(a).multiply(a).multiply(a).multiply(a).pow(1000000));
        System.out.println(Long.MAX_VALUE);
        System.out.println(BigInteger.valueOf(10).pow(100000000));
    }
}
