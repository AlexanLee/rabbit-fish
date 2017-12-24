package com.rabbit.fish.crawler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbit.fish.utils.httpclient.SimpleHttpParam;
import com.rabbit.fish.utils.httpclient.SimpleHttpResult;
import com.rabbit.fish.utils.httpclient.SimpleHttpUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author lijianli
 * @date 2016/12/6
 */
public class Toutiao {
    private static Date now = new Date();
    private static  final long nowMills = now.getTime();
    private static volatile int num=1;
    public void pull(long low,long high,String url){
        for (long i = low; i < high; i++) {
            String curTime = String.valueOf(nowMills);
            num=1;
            while (num > 0) {
                try {
                    System.out.println("now:" + now + " now Millis:" + nowMills);
                    System.out.println("cur time:" + curTime);
                    String tmpUrl = String.format(url,i,curTime);
                    System.out.println(tmpUrl);
                    SimpleHttpParam ufoHttp = new SimpleHttpParam(tmpUrl);
                    ufoHttp.setMethod("GET");
                    ufoHttp.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
                    ufoHttp.setConnectTimeout(60);
                    SimpleHttpResult request = SimpleHttpUtils.httpRequest(ufoHttp);
                    System.out.println(request.getStatusCode());
                    String result = request.getContent();
                    System.out.println(request.getExceptionMsg());
                    System.out.println(request.toString());
                    System.out.println(request.getContentType());
                    JSONObject jsonObject = JSON.parseObject(result);
                    curTime = parseCursor(jsonObject);
                    boolean hasMore = jsonObject.getJSONObject("data").getBoolean("has_more");
                    if (hasMore
                            ||(!hasMore
                                &&!Objects.equals(0L,jsonObject.getJSONObject("data").getLong("max_cursor")))){
                        try {
                            File picFile = new File(String.format("/data/toutiao/%s_info_%s.json", i, curTime));
                            try {
                                if (!picFile.getParentFile().exists()) {
                                    picFile.getParentFile().mkdirs();
                                }
                                if (!picFile.exists()) {
                                    picFile.createNewFile();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            FileWriter fileWriter = new FileWriter(picFile, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.write(jsonObject.toString());
                            bufferedWriter.close();
                            if(!hasMore){
                                num--;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        num--;
                    }
                    Thread.sleep(100);
                    System.out.println("DONE");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private String parseCursor(JSONObject result){
        String maxCursor=String.valueOf(nowMills);
        if(Objects.nonNull(result)
            &&StringUtils.isNotEmpty(result.toJSONString())){
            boolean hasMore = result.getJSONObject("data").getBoolean("has_more");
            System.out.println("has more:"+hasMore);
            if(hasMore){
                maxCursor =String.valueOf(result.getJSONObject("data").getLong("max_cursor")+1);
            }
        }
        System.out.println(maxCursor);
        return maxCursor;
    }
}