package com.cases_new;

import com.business.business_login;
import com.cases_old.set_Browwer;
import com.config.StaticProvider;
import com.util.DatabaseService;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;
//断言监听
@Listeners({com.util.AssertionListener.class})
public class test_login extends TestBase{
    static org.apache.log4j.Logger log= org.apache.log4j.Logger.getLogger(test_login.class.getName());
    //关联excel
    @Test(dataProvider = "dp", dataProviderClass = StaticProvider.class)
    public void login(Map<String,String> data) throws Exception {
//      business_login login=new business_login(driver);
//      login.login_portal("442891581@qq.com","1111aaaa");
        business_login login=new business_login(driver);
         //使用excel中的数据登录
        login.login_portal(data.get("username"),data.get("password"));

        //查询数据库
        String sql="select * from cps_gen_main_order b where b.gopay_order_id='2018083108982153'";
        String s1= ds.getData(conn,sql,1,1);


    }

    @Override
    public void init() throws Exception {

    }
}
