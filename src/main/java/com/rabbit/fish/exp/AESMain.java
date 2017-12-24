package com.rabbit.fish.exp;

/**
 * Created by lijianli on 2017/11/30.
 */
public class AESMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("hello");
        String src = "Hello,CryptWorld";
        String encrypted = AESUtil.encrypt(src);
        String decrypted = AESUtil.decrypt(encrypted);
        System.out.println("src: " + src);
        System.out.println("encrypted: " + encrypted);
        System.out.println("decrypted: " + decrypted);
    }
}
