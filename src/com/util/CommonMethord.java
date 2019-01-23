package com.util;

import com.config.DriverManage;
import com.config.DriverManage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class CommonMethord {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger("CommonMethord");

	public CommonMethord() {
		try {
			PropertyConfigurator.configure(CommonMethord.getRealath()
					+ "conf/log4j.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// /**
	// * 干掉IE浏览器
	// *
	// * @param
	// * @return
	// */
	// public static void killProcess() throws Exception {
	//
	// try {
	// while (isProcessRunning("iexplore.exe")) {
	// log.info("强行结束IE进程-------");
	// Runtime.getRuntime().exec("TaskKill /IM iexplore.exe /F");
	// Thread.sleep(500);
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	// /**
	// * 立刻干掉IE浏览器
	// *
	// * @param
	// * @return
	// */
	// public static void killProcessIE() throws Exception {
	// log.info("立刻强行结束IE进程-------");
	// Runtime.getRuntime().exec("TaskKill /IM iexplore.exe /F");
	// Thread.sleep(500);
	// }

	/**
	 * 结束任意进程
	 * 
	 * @param processName
	 * @return
	 * @throws Exception
	 */
	public static void killProcess(String processName) throws Exception {

		try {
			while (isProcessRunning(processName)) {
				// log.info("强行结束***进程-------");
				Runtime.getRuntime()
						.exec("taskkill /IM " + processName + " /F");
				Thread.sleep(500);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// --判断进程是否存在，供killProcess调用
	private static boolean isProcessRunning(String processName) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("tasklist");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				if (line.toLowerCase().contains(processName)) {
					return true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 根据参数值获取0-max的随机整数
	 * 
	 * @param max
	 * @return
	 */
	public static int getRandom(int max) throws Exception {
		return (int) (Math.random() * max);
	}

	/**
	 * 生成任意长度的随机字符
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) throws Exception {
		String str = "";
		for (int a = 0; a < length; a++) {
			char ch = (char) ('a' + Math.random() * ('z' - 'a' + 1));
			str = str + String.valueOf(ch);
		}
		return str;
	}

	/**
	 * 获取系统日期
	 * 
	 * @param
	 * @return
	 */
	public static String getSystemDate() throws Exception {

		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String sDateSuffix = dateformat.format(date);
		return sDateSuffix;
	}

	/**
	 * 处理编译后的文件路径
	 * 
	 * @param filepath
	 * @return
	 */
	public static String convertFilepath(String filepath) throws Exception {

		if (filepath.contains("bin")) {
			filepath = filepath.replace("bin/", "");
		}
		return filepath;
	}

	/**
	 * 获取绝对路径(工程根目录)
	 * 
	 * @param
	 * @return
	 */
	public static String getRealath() throws Exception {

		String path = "";
		try {
			path = DriverManage.class.getClassLoader().getResource("").toURI()
					.getPath();

			try {
				path = CommonMethord.convertFilepath(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return path;
	}

	/**
	 * 按照,分隔输入的字符串,得到一个list
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> getList(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str, ",");
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}

	/**
	 * createDir
	 * 
	 * @param dir_name
	 */
	public static void createDir(String dir_name) {
		if (!(new File(dir_name).isDirectory())) {
			// 如果不存在则新建一个目录
			new File(dir_name).mkdir();
		}
	}

}
