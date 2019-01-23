package com.cases_new;

import com.business.business_login;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Iterator;
@Listeners({com.util.AssertionListener.class})
public class test_xml extends TestBase{
    Document doc = null;
    private  String data;
    @Test
    public void xml() throws Exception {
        SAXReader reader = new SAXReader();
        //解析的xml文档
        Document doc = reader.read(new File("username.xml"));
        Element rootElt = doc.getRootElement(); // 获取根节点
        System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称

//        List<Element> childElements = rootElt.elements();
//        for (Element child : childElements) {
//
//            //已知属性名情况下
//            System.out.println(child.getData());
//
//        }

        Iterator it = rootElt.elementIterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            data=element.getText();
            business_login login=new business_login(driver);
            Thread.sleep(1000);
            login.login_portal(data,"1111aaaa");
            System.out.println("email: " + data);
        }



    }

    @Override
    public void init() throws Exception {

    }
}

