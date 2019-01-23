package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class page_Login {
    private WebDriver driver;

    public page_Login(WebDriver driver) {
        this.driver =driver;
    }


    public  void userName(String username) {

        this.driver.findElement(By.id("username")).sendKeys(username);
    }


    public  void passWord(String passWord){

        this.driver.findElement(By.id("password")).sendKeys(passWord);
    }

    public  void loginButton( ){

        this.driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    }
}
