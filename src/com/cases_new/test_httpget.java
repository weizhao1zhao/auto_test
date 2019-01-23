package com.cases_new;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.sourceforge.htmlunit.corejs.javascript.NativeJSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;


@Test
public class test_httpget {
    public  void httpcleit() throws IOException {
         CloseableHttpClient httpclient = HttpClients.createDefault();

         HttpGet httppost = new HttpGet("http://api.map.baidu.com/telematics/v3/weather?location=%E5%8C%97%E4%BA%AC&output=json&ak=W69oaDTCfuGwzNwmtVvgWfGH");

         CloseableHttpResponse c= httpclient.execute(httppost);
         String result = EntityUtils.toString(c.getEntity());// 返回json格式：
         System.out.println("result : "+result);
         JSONObject JO=JSON.parseObject(result);
         String s=JO.getString("status");
         System.out.println(s);
         Assert.assertTrue("240".equals(s));


//        //json 解析
//        String json = "{\"CityId\":18,\"CityName\":\"西安\",\"ProvinceId\":27,\"CityOrder\":1}";
//        JSONObject jo = JSON.parseObject(json);
//        System.out.println(jo.getString("ProvinceId"));
//        //json 数组解析
//        String json1 = "[{\"CityId\":18,\"CityName\":\"西安\",\"ProvinceId\":27,\"CityOrder\":1}]";
//        JSONArray JSA=JSON.parseArray(json1);
//        for (int i=0;i<JSA.size();i++){
//            JSONObject jo1 = JSA.getJSONObject(i);
//            System.out.println(jo1);
//
//        }
//        //json 字符数组解析
     //String s = "[1,2,4]";
     //Integer.parseInt()
//        JSONArray JA = JSON.parseArray(s);
//        for (int i=0;i<JA.size();i++){
//            System.out.println(JA.getString(i));
//        }
  }

}

