package com.rabbit.fish.exp;

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
    }
}
