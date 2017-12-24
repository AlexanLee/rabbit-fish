package com.rabbit.fish.crawler;

import com.rabbit.fish.utils.httpclient.SimpleHttpParam;
import com.rabbit.fish.utils.httpclient.SimpleHttpResult;
import com.rabbit.fish.utils.httpclient.SimpleHttpUtils;

import org.apache.http.protocol.HTTP;

import java.util.Date;

/**
 *
 * @author lijianli
 * @date 2017/3/1
 */
public class ToutiaoDongtai {

    private static Date now = new Date();
    private static long nowMills = now.getTime();
    private static SimpleHttpParam ufoHttp =
            new SimpleHttpParam("http://toutiao.com/dongtai/8087016327/");

    public static void main(String[] args){
        System.out.println("now :"+nowMills);
        ufoHttp.setMethod("GET");
        ufoHttp.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        ufoHttp.setConnectTimeout(60);
        SimpleHttpResult request = SimpleHttpUtils.httpRequest(ufoHttp);
        System.out.println(request.getStatusCode());
        System.out.println(request.getContent());
        System.out.println("end :"+System.currentTimeMillis());

    }
}
