package com.cases_new;

import com.config.DriverManage;
import com.config.GetTestProperties;
import com.util.CommonMethord;
import com.util.DatabaseService;
import com.util.Selenium2Proxy;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.databene.feed4testng.FeedTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public abstract class TestBase  {
	protected WebDriver driver = null;
	protected String browser;
	protected final Logger log = Logger.getLogger(getClass());
	protected boolean captureflag;
	protected Connection conn = null;
	protected DatabaseService ds = new DatabaseService();

	String dbusername;
	String dbpassword;
	String dbtype;
	String dburl;

	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		captureflag = true;

		CommonMethord.killProcess(getBrowserProcess(browser));
		CommonMethord.killProcess("iedriverserver_32.exe");
		driver = DriverManage.getDriver(browser);
		// driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	abstract public void init() throws Exception;

	@AfterMethod
	public void afterMethod() throws Exception {
		try {
			Selenium2Proxy se = new Selenium2Proxy(driver);
			if (captureflag) {
				se.captureScreenshot();
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null) {
				CommonMethord.killProcess(getBrowserProcess(browser));
				Thread.sleep(500);
				if (!browser.equals("1")) {
					driver.quit();
				}
			}
		}
	}

	@Parameters({"driverType"})
	@BeforeClass
	public void beforeClass(@Optional("3") String driverType) throws Exception {
		dbusername = GetTestProperties.getdbusername();
		dbpassword = GetTestProperties.getdbpassword();
		dbtype = GetTestProperties.getdbtype();
		dburl = GetTestProperties.getdburl();
		conn = ds.connectDBDriver(dbtype, dbusername, dbpassword, dburl);
		// browser = GetTestProperties.getBrowserType();
		browser = driverType;
		log.info("Start to run test class " + this.getClass().getName()
				+ " on driverType" + browser);
	}

	@AfterClass
	public void afterClass() throws Exception {
		ds.closeDBDriver(conn);
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		PropertyConfigurator.configure(CommonMethord.getRealath()
				+ "conf/log4j.properties");
	}

	public String getBrowserProcess(String bs) throws Exception {
		String process = null;

		switch (Integer.parseInt(bs)) {
		case 1:
			process = "firefox.exe";
			break;

		case 2:
			process = "iexplore.exe";
			break;

		case 3:
			process = "chrome.exe";
			break;
		}
		return process;
	}

}