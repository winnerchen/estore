package chen.yiheng.estore.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerDemo {
	public static Logger logger=Logger.getLogger(LoggerDemo.class);
	public static void main(String[] args) {
		logger.setLevel(Level.INFO);
		logger.info("-----info-----");
		logger.debug("-----debug-----");
		logger.error("error");
		//logger.trace("trace");
		logger.warn("warn");
		logger.fatal("fatal");
	}
}
