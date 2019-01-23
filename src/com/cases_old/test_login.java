package com.cases_old;

import com.business.business_login;
import org.testng.annotations.Test;

public class test_login extends set_Browwer{
    @Test
    public void login(){
//         page_Login login=new page_Login(dr);
//         login.userName("442891581@qq.com");
//         login.passWord("1111aaaa");
//         login.loginButton();
        business_login login=new business_login(dr);
        login.login_portal("442891581@qq.com","1111aaa");

    }
}
