package com.util;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogsInit {

	// protected final Log log = LogFactory.getLog(getClass());
	protected final Logger log = Logger.getLogger(getClass());

	public LogsInit() {
		try {
			PropertyConfigurator.configure(CommonMethord.getRealath()
					+ "conf/log4j.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
