package com.business;

import com.cases_new.test_login;
import com.config.GetTestProperties;
import com.page.page_Login;
import com.util.Assert_zw;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({com.util.AssertionListener.class})

public class business_login {
        static org.apache.log4j.Logger log= org.apache.log4j.Logger.getLogger(business_login.class.getName());
        private WebDriver driver;

        public business_login(WebDriver driver){
            this.driver=driver;
        }
        //获取测试地址
        public void goToUrl() {
        driver.get(GetTestProperties.getTestUrl());
        }

        public void login_portal(String username,String password){
            this.goToUrl();
            page_Login login =new page_Login(driver);
            login.userName(username);
            login.passWord(password);
            login.loginButton();
          if (Assert_zw.AssertEquals(driver.getTitle(),"Cbos Admin - Powered By Cbos","登录断言")==true) {
            log.info("登录成功");
           }else {
               log.error("登录失败请查询原因".toString());
           }

        }
}
