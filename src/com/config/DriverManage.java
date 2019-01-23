package com.config;

import com.util.CommonMethord;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManage {
	public static WebDriver driver = null;
	private static Logger log = Logger.getLogger("DriverManage");

	public static WebDriver getDriver(String runDriver) throws Exception {

		PropertyConfigurator.configure(CommonMethord.getRealath()
				+ "conf/log4j.properties");

		switch (Integer.parseInt(runDriver)) {
		case 1:
			System.setProperty("webdriver.firefox.bin",
					"C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			driver = new FirefoxDriver();
			log.info("runDriver is ff......");
			break;

		case 2:
			System.setProperty("webdriver.ie.driver",
					CommonMethord.getRealath() + "res/IEDriverServer_32.exe");
			driver = new InternetExplorerDriver();
			log.info("runDriver is ie......");
			break;

		case 3:
			System.setProperty("webdriver.chrome.driver",
					CommonMethord.getRealath() + "res/chromedriver.exe");
			driver = new ChromeDriver();
			log.info("runDriver is chrome......");
			break;
		}

		// if (runDriver.equals("1")) {
		// System.setProperty("webdriver.firefox.bin",
		// "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		// driver = new FirefoxDriver();
		// log.info("runDriver is ff......");
		// }
		// if (runDriver.equals("2")) {
		// System.setProperty("webdriver.ie.driver",
		// CommonMethord.getRealath() + "res/IEDriverServer_32.exe");
		// driver = new InternetExplorerDriver();
		// log.info("runDriver is ie......");
		// }
		// if (runDriver.equals("3")) {
		// System.setProperty("webdriver.chrome.driver",
		// CommonMethord.getRealath() + "res/chromedriver.exe");
		// driver = new ChromeDriver();
		// log.info("runDriver is chrome......");
		// }
		return driver;
	}

}
