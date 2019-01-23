package com.cases_old;

import org.apache.ecs.html.THead;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UI_autotest {
    private  WebDriver dr;
    @BeforeClass
    public void Setup(){
        System.setProperty("webdriver.chrome.driver", "d:\\softtest1\\chromedriver.exe");
        dr=new ChromeDriver();
        dr.manage().window().maximize();
        dr.get("https://cbos248.gopay.com.cn/admin");
    }
    @Test
    public void test01_Login() throws InterruptedException {
        dr.findElement(By.id("username")).sendKeys("442891581@qq.com");
        dr.findElement(By.id("password")).sendKeys("1111aaaa");
        dr.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        Thread.sleep(7000);
    }

    @Test
    public void test02_Insert() throws InterruptedException {
        //切换单笔生成订单frame
        dr.switchTo().frame("menuFrame");
        dr.findElements(By.tagName("a")).get(2).click();
        dr.switchTo().defaultContent();
        //切换单笔上传列表frame
        dr.switchTo().frame("mainFrame");
        String file="C:\\Users\\TECH\\Desktop\\新建文本文档.zip";
        dr.findElement(By.id("attachment_file")).sendKeys(file);
        Select select=new Select(dr.findElement(By.id("order_cmd_property")));
        select.selectByValue("SERVICE");
        dr.findElement(By.id("order_cmd_name")).sendKeys("测试");
        dr.findElement(By.id("order_cmd_count")).sendKeys("1");
        dr.findElement(By.id("order_cmd_pre_price")).sendKeys("1");
        dr.findElement(By.id("order_total_amount")).sendKeys("2");
        dr.findElement(By.id("order_cmd_desc")).sendKeys("测试");
        dr.findElement(By.id("order_payer_name")).sendKeys("测试");
        dr.findElement(By.id("order_payer_idcard")).sendKeys("511702198807288390");
        dr.findElement(By.id("order_carrier_name")).sendKeys("顺丰");
        dr.findElement(By.id("order_freight_num")).sendKeys("528555480");
        dr.findElement(By.id("isDeclare2")).click();
        dr.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        dr.switchTo().defaultContent();
        //获取订单号及成功结果
        dr.switchTo().frame("mainFrame");
        Thread.sleep(2000);
        String mag=dr.findElements(By.tagName("span")).get(0).getText();
        System.out.println(mag);
        Assert.assertTrue(mag.contains("上传成功!"));
        Thread.sleep(7000);
//        String orderid=dr.findElements(By.tagName("span")).get(1).getText();
//        System.out.println(orderid);
//        dr.switchTo().defaultContent();
    }
    @Test
    public void test03_Query() throws InterruptedException {
        String orderid=dr.findElements(By.tagName("span")).get(1).getText();
        String orderid1=orderid.substring(6).trim();
        System.out.println(orderid1);
        dr.switchTo().defaultContent();
        //切换菜单
        dr.switchTo().frame("menuFrame");
        dr.findElements(By.tagName("a")).get(1).click();
        dr.switchTo().defaultContent();
        //切换订单列表frame
        dr.switchTo().frame("mainFrame");
        dr.findElement(By.id("form_merchant_order_id")).sendKeys(orderid1);
        dr.findElement(By.id("btn_submit")).click();
        //验证查询结果
        String str= dr.findElement(By.id("contentTable")).findElements(By.tagName("td")).get(4).getText();
        Assert.assertTrue(orderid1.equals(str));
        Thread.sleep(7000);
    }

    @Test
    public void test04_Delete() throws InterruptedException {
        dr.findElement(By.id("checkAll")).click();
        dr.findElement(By.id("btn_delete_selected")).click();
        dr.findElement(By.xpath("/html/body/div[7]/div[3]/div/button[1]")).click();
        Thread.sleep(1000);
        Alert alert= dr.switchTo().alert();
        Assert.assertTrue("已成功删除1条失效订单".equals(alert.getText()));
        alert.accept();
        Thread.sleep(7000);
    }

}
