package com.rabbit.fish.crawler;


import com.rabbit.fish.utils.httpclient.SimpleHttpParam;
import com.rabbit.fish.utils.httpclient.SimpleHttpResult;
import com.rabbit.fish.utils.httpclient.SimpleHttpUtils;

import org.apache.http.protocol.HTTP;

import java.io.File;
import java.util.Date;

/**
 * Created by lijianli on 2016/12/6.
 */
public class pkuInfo {

    private static Date now = new Date();
    private static long nowMills = now.getTime();
    private static SimpleHttpParam ufoHttp = new SimpleHttpParam("http://birthday.cmcm.com/portal/employee/list?beginNumber=1&endNumber=300&_=1487158166139");
    public static void main(String[] args) {
//        new Thread() {
//            public void run() {
//                while (true) {
                    try {

                        System.out.println("now:" + now + " now Millis:" + nowMills);
                        for (int j = 60; j < 61; j++) {
                            File picFile = new File("/data/pku/info.txt");
//                            try {
//                                if (!picFile.getParentFile().exists()) {
//                                    picFile.getParentFile().mkdirs();
//                                }
//                                if (!picFile.exists()) {
//                                    picFile.createNewFile();
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                            ufoHttp.setMethod("POST");
                            ufoHttp.addHeader("Cookie","JSESSIONID=6AF4E659A946BD78ECB5704F7538D243");
                            ufoHttp.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
                            ufoHttp.setConnectTimeout(60);
                            SimpleHttpResult request = SimpleHttpUtils.httpRequest(ufoHttp);
                            System.out.println(request.getStatusCode());
                            String result=request.getContent();
                            System.out.println(request.getExceptionMsg());
                            System.out.println(request.toString());
                            System.out.println(request.getContentType());
//                            String result = SsoClient.getInstance().postPku(
//                                    "https://portal.pku.edu.cn/portal2013/publicsearch/person/search.do", map);
//                            JSONObject jsonObject = JSON.parseObject(result);
//                            JSONArray jsonArray = jsonObject.getJSONArray("data");
//                            System.out.println(j + ":" + jsonArray.size());
//                            for (int i = 0; i < jsonArray.size(); i++) {
//                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                                System.out.println(jsonObject1.toString());
//                                try {
//                                    FileWriter fileWriter = new FileWriter(picFile, true);
//                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//                                    bufferedWriter.write(jsonObject1.toString());
//                                    bufferedWriter.close();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
                        }
                        System.out.println("DONE");
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//            }
//        }.start();
//    }
}