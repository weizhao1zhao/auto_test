package com.util;

import com.config.GetTestProperties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Selenium 2.0
 * 
 * 
 */
public class Selenium2Proxy extends LogsInit {
	private WebDriver driver;

	public Selenium2Proxy(WebDriver driver) {
		this.driver = driver;
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

	public void forward() {
		driver.navigate().forward();
	}

	public void back() {
		driver.navigate().back();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 清除所有的cookies
	 */
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	// // 截图
	// public void captureScreenshot(String fileName) {
	// TakesScreenshot tsDriver = (TakesScreenshot) driver;
	// File image = new File(fileName);
	// tsDriver.getScreenshotAs(OutputType.FILE).renameTo(image);
	// }

	/**
	 * 截图,路径为工程目录下的screenshot,追加方法名
	 * 
	 * @param methodname
	 */
	public void captureScreenshot(String methodname)
			throws InterruptedException {
		if (!isAlertExist()) {
			// 定义了截图存放目录名
			// String dir_name = "screenshot";
			String dir_name = GetTestProperties.getPicDir();
			// 判断是否存在该目录
			if (!(new File(dir_name).isDirectory())) {
				// 如果不存在则新建一个目录
				new File(dir_name).mkdir();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
			// 格式化当前时间，例如20121231-165210
			String time = sdf.format(new Date());

			try {
				// 执行屏幕截图，默认会把截图保存到temp目录
				File source_file = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				// 将截图另存到我们需要保存的目录，例如screenshot\20121231-165210.png
				FileUtils.copyFile(source_file, new File(dir_name
						+ File.separator + time + "_" + methodname + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnhandledAlertException e1) {
				// e1.printStackTrace();
				RobotScreenShot cam = new RobotScreenShot();
				cam.snapShot(methodname);
			} catch (NoSuchWindowException e2) {
				// e2.printStackTrace();
				RobotScreenShot cam = new RobotScreenShot();
				cam.snapShot(methodname);
			}
		} else {
			RobotScreenShot cam = new RobotScreenShot();
			cam.snapShot(methodname);
		}
	}

	/**
	 * 截图,路径为工程目录下的screenshot
	 */
	public void captureScreenshot() throws InterruptedException {
		if (!isAlertExist()) {
			// 定义了截图存放目录名
			// String dir_name = "screenshot";
			String dir_name = GetTestProperties.getPicDir();
			// 判断是否存在该目录
			if (!(new File(dir_name).isDirectory())) {
				// 如果不存在则新建一个目录
				new File(dir_name).mkdir();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
			// 格式化当前时间，例如20121231-165210
			String time = sdf.format(new Date());

			try {
				// 执行屏幕截图，默认会把截图保存到temp目录
				File source_file = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				// 将截图另存到我们需要保存的目录，例如screenshot\20121231-165210.png
				FileUtils.copyFile(source_file, new File(dir_name
						+ File.separator + time + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnhandledAlertException e1) {
				// e1.printStackTrace();
				RobotScreenShot cam = new RobotScreenShot();
				cam.snapShot();
			} catch (NoSuchWindowException e2) {
				// e2.printStackTrace();
				RobotScreenShot cam = new RobotScreenShot();
				cam.snapShot();
			}
		} else {
			RobotScreenShot cam = new RobotScreenShot();
			cam.snapShot();
		}
	}

	// // 切换指定的窗口----遍历最多10次，找到为止
	// public void switchToWindow(String windowTitle) {
	// for (int a = 0; a < 9; a++) {
	// Set<String> windowHandles = driver.getWindowHandles();
	// for (String handler : windowHandles) {
	// driver.switchTo().window(handler);
	// String title = driver.getTitle();
	// log.info("fangfa " + driver.getTitle());
	// if (windowTitle.equals(title)) {
	// // log.info("遍历了几次啊？？？！！！" +a);
	// a = 10;
	// break;
	// }
	// }
	// }
	// }

	/**
	 * 改进版的切换指定的窗口,适用于只弹出一个窗口的情况,不需要传入任何参数,直接切换到下一个窗口
	 */
	public void switchToWindow() {
		// 得到当前窗口的句柄
		String currentWindow = driver.getWindowHandle();
		// 得到所有窗口的句柄
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			String handle = it.next();
			if (currentWindow.equals(handle))
				continue;
			WebDriver window = driver.switchTo().window(handle);
			log.info("title,url = " + window.getTitle() + ","
					+ window.getCurrentUrl());
		}
	}

	/**
	 * 判断指定的窗口存在--遍历最多10次，找到为止，适用于多个弹窗的情况，传入指定窗口的title
	 * 
	 * @param windowTitle
	 * @return
	 */
	public boolean switchToWindow(String windowTitle) {
		boolean flag = false;
		for (int a = 0; a < 9; a++) {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String handler : windowHandles) {
				driver.switchTo().window(handler);
				String title = driver.getTitle();
				if (windowTitle.equals(title)) {
					a = 10;
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断指定的窗口存在--适用于多个弹窗的情况，传入指定窗口的title(尚未测试)
	 * 
	 * @param windowTitle
	 * @return
	 */
	public boolean switchToWindow2(String windowTitle) {
		boolean flag = false;
		try {
			String currentHandle = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			for (String handler : handles) {
				if (handler.equals(currentHandle))
					continue;
				else {
					driver.switchTo().window(handler);
					if (driver.getTitle().equals(windowTitle)) {
						flag = true;
						log.info("Switch to window: " + windowTitle
								+ " successfully!");
						break;
					} else
						continue;
				}
			}
		} catch (NoSuchWindowException e) {
			log.info("Window: " + windowTitle + " cound not found!",
					e.fillInStackTrace());
			flag = false;
		}
		return flag;
	}

	/**
	 * 切换到对于的frame，传入参数为frame在dom中的id
	 */
	public void switchToFrame(String frameId) {
		driver.switchTo().frame(frameId);
	}

	/**
	 * 切换到对于的frame，传入参数为frame在dom中的index，从0开始
	 */
	public void switchToFrame(int frameId) {
		driver.switchTo().frame(frameId);
	}

	// 定位到所对应的Alert
	private Alert switchToAlert() {
		return driver.switchTo().alert();
	}

	// 点击alert的确定按钮
	public void acceptAlert() {
		switchToAlert().accept();
	}

	// 点击取消和上面的关闭按钮
	public void dismissAlert() {
		switchToAlert().dismiss();
	}

	

	/**
	 * 模拟鼠标悬停事件
	 */
	public void moveToElement(By locator) {
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(locator)).perform();
	}

	/**
	 * 判断弹窗存在
	 * added by harry 2013-01-11
	 */
	public boolean isAlertExist() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex1) {
			return false;
		} catch (NoSuchWindowException ex2) {
			return false;
		}
	}

	/**
	 * 判断页面是否刷新
	 * added by harry 2013-01-29
	 * 
	 * @param trigger
	 * @return
	 */
	public boolean waitPageRefresh(WebElement trigger) {
		int refreshTime = 0;
		boolean isRefresh = false;
		try {
			for (int i = 1; i < 60; i++) {
				refreshTime = i;
				trigger.getTagName();
				Thread.sleep(1000);
			}
		} catch (StaleElementReferenceException e) {
			isRefresh = true;
			log.info("Page refresh time is:" + refreshTime + " seconds!");
			return isRefresh;
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Page didnt refresh in 60 seconds!");
		return isRefresh;
	}

	/**
	 * 处理一个潜在的弹窗
	 * added by harry 2013-01-29
	 * 
	 * @param driver
	 * @param option
	 *            :true(click ok);false(click cancel)
	 * @return
	 */
	public boolean dealPotentialAlert(boolean option) {
		boolean flag = false;
		try {
			Alert alert = driver.switchTo().alert();
			if (null == alert)
				throw new NoAlertPresentException();
			try {
				if (option) {
					alert.accept();
					log.info("Accept the alert: " + alert.getText());
				} else {
					alert.dismiss();
					log.info("Dismiss the alert: " + alert.getText());
				}
				flag = true;
			} catch (WebDriverException ex) {
				if (ex.getMessage().startsWith("Could not find"))
					log.info("There is no alert appear!");
				else
					throw ex;
			}
		} catch (NoAlertPresentException e) {
			log.info("There is no alert appear!");
		}
		return flag;
	}

}