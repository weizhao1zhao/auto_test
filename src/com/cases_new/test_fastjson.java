package com.cases_new;

import com.alibaba.fastjson.JSON;
import com.util.Jdbcto_xml;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class test_fastjson {
    //static org.apache.log4j.Logger log= org.apache.log4j.Logger.getLogger(test_fastjson.class.getName());
    @Test
    public void fastjson() throws Exception {
        //字符串转换成json
        Map<String,String> map=new HashMap();
        map.put("username","zhaowei");
        String json= JSON.toJSONString(map);
        System.out.println(json);
        String s="select email from cust_person_info where rownum<=10 and email is not null";
        Jdbcto_xml.WriterFileToXml(s);

    }
}

