package com.util;


import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
//断言失败可以继续执行下面的用例
public class Assert_zw {

    public static boolean flag = true;

    public static List<Error> errors = new ArrayList<Error>();

    public static void AssertEquals(Object actual, Object expected){
        try{
            Assert.assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }


    public static Boolean AssertEquals(Object actual, Object expected, String message){
        try{
            Assert.assertEquals(actual, expected, message);
        }catch(Error e){
            errors.add(e);
            flag = false;

        }
        //自己加的，有需要删除
        return flag;
    }

}