package com.util;


import com.config.GetTestProperties;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.sql.*;
import org.dom4j.io.XMLWriter;


//java读取数据库生成xml
public class Jdbcto_xml {
    public static String dbusername;
    public static String dbpassword;
    public static String dbtype;
    public static String dburl;
    public static Connection conn = null;
    public static DatabaseService ds = new DatabaseService();

    public static String WriterFileToXml(String sq) throws Exception {
        dbusername = GetTestProperties.getdbusername();
        dbpassword = GetTestProperties.getdbpassword();
        dbtype = GetTestProperties.getdbtype();
        dburl = GetTestProperties.getdburl();
        conn = ds.connectDBDriver(dbtype, dbusername, dbpassword, dburl);

        //创建数据库连接
        conn = ds.connectDBDriver(dbtype, dbusername, dbpassword, dburl);
        //String sql = "select email from cust_person_info where rownum<=100 and email is not null";
        String sql=sq;

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("username");// 创建根节点
        try {
            Statement st = conn.createStatement();//创建预处理对象
            ResultSet rs = st.executeQuery(sql);//获得结果集
            System.out.println(rs.toString());


            while(rs.next()){

                  Element name = root.addElement("email");
                  name.setAttributeValue("name","test");

                 name.setText(rs.getString("email"));


//                Element mean=word.addElement("mean");
//                mean.addCDATA(rs.getString("gopay_order_id"));
//                Element lx=word.addElement("lx");
//                lx.addCDATA(rs.getString("gopay_order_id"));

            }
            //生成xml
            XMLWriter writer=new XMLWriter(new FileWriter(new File("username.xml")));
            writer.write(document);
            writer.close();

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql;
    }

}


