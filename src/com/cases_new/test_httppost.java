package com.cases_new;

import com.config.GetTestProperties;
import com.config.StaticProvider;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;


public class test_httppost {
    static org.apache.log4j.Logger log= org.apache.log4j.Logger.getLogger(test_httppost.class.getName());

    @Test(dataProvider = "dp", dataProviderClass = StaticProvider.class)
    public void post(Map<String,String> data) throws IOException, DocumentException {
        //随机字符串
        String str1="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        String merOrderNum= RandomStringUtils.random(10,str1);

        //md5加密
        String str="version=[1.0]"+"signType=[1]"+"tranCode=[AB01]" +
                "merchantID=[0000000363]"+"merOrderNum=["+merOrderNum+"]"+"tranDateTime=[20180907153838]"+
                "merAcct=[0000000002000000061]" +
                "VerficationCode=[11111aaaaa]";

        String signValue= DigestUtils.md5Hex(str);
        System.out.println(signValue);
        //建立httpclient连接
        CloseableHttpClient httpclient = HttpClients.createDefault();

       // String url="https://gatewaygray.gopay.com.cn/Trans/WebClientAction.do";
        String url=  GetTestProperties.getPostUrl();
        log.info(url);
        //建立HttpPost对象
        HttpPost httpPost=new HttpPost(url);
        //字符串转换为集合
        String arr[]={"VerficationCode","version","tranCode","merchantID","merAcct","tranDateTime","tranIP","charset","signType"};
        List list= Arrays.asList(arr);
        //建立一个NameValuePair集合，用于存储欲传送的参数
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        //遍历集合拿到key
        for (int i = 0; i <list.size() ; i++) {
            String key= (String) list.get(i);
            String value=data.get(key);
            params.add(new BasicNameValuePair(key,value));
        }
//        params.add(new BasicNameValuePair("VerficationCode","11111aaaaa"));
//        params.add(new BasicNameValuePair("version","1.0"));
//        params.add(new BasicNameValuePair("tranCode","AB01"));
//        params.add(new BasicNameValuePair("merchantID","0000000363"));
         params.add(new BasicNameValuePair("merOrderNum",merOrderNum));
//        params.add(new BasicNameValuePair("merAcct","0000000002000000061"));
//        params.add(new BasicNameValuePair("tranDateTime","20180907153838"));
//        params.add(new BasicNameValuePair("tranIP","210.13.252.2"));
//        params.add(new BasicNameValuePair("signType","1"));
//        params.add(new BasicNameValuePair("charset","1"));
         params.add(new BasicNameValuePair("signValue",signValue));
        //参数集合传入到一个UrlEncodedFormEntity中并设置编码
        httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
        //发送Post,并返回一个HttpResponse对象
        CloseableHttpResponse c= httpclient.execute(httpPost);
        //使用响应对象获取响应实体
        HttpEntity entity = c.getEntity();
        //将响应实体转为字符串
        String result = EntityUtils.toString(entity);
        log.info(result);
        //这里用返回的响应结果result作为参数获得 Document 对象
        Document document = DocumentHelper.parseText(result);
        org.dom4j.Element rootElement = document.getRootElement();
        //获取断言结果的值
        String respCode = rootElement.element("respCode").getText();
        String respDesc = rootElement.element("respDesc").getText();
        log.info(respCode);
        //进行断言
        Assert.assertTrue("0000".equals(respCode)&&"响应成功".equals(respDesc));

    }

}
