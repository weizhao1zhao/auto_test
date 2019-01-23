package com.cases_old;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class set_Browwer {
       WebDriver dr;
       String url;
    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "d:\\softtest\\chromedriver.exe");
        dr=new ChromeDriver();
        dr.manage().window().maximize();
        url="https://cbos248.gopay.com.cn/admin";
        dr.get(url);
    }
}
