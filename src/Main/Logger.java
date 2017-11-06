package Main;

import java.time.LocalTime;

public class Logger {

	public final static String ERROR = "ERROR";
	public final static String DEBUG = "DEBUG";
	public final static String INFO = "INFO";
	public final static String FINEST = "FINEST";
	public final static String GENERAL = "GENERAL";
	
	public final static int ERROR_LEVEL = 0;
	public final static int INFO_LEVEL = 1;
	public final static int DEBUG_LEVEL = 2;
	public final static int FINEST_LEVEL = 3;
	
	public static int level = INFO_LEVEL;
	
	public static void log(String value, String name, String type, int loglevel) {
		if(name == null) {
			StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
			name = ste.getClassName().substring(ste.getClassName().lastIndexOf(".") + 1) + "." + ste.getMethodName();
		}

		if(loglevel == 0) {
			System.err.println(LocalTime.now() + " " + type + "\t" + name + " - " + value);
		}
		else {
			System.out.println(LocalTime.now() + " " + type + "\t" + name + " - " + value);
		}
	}

	public static void logError(String value) {
		Logger.logError(value, null);
	}
	
	public static void logError(String value, String name) {
		Logger.log(value, name, ERROR, ERROR_LEVEL);
	}
	
	public static void logInfo(String value) {
		Logger.logInfo(value, null);
	}
	
	public static void logInfo(String value, String name) {
		if(level >= INFO_LEVEL)
			Logger.log(value, name, INFO, INFO_LEVEL);
	}
	
	public static void logDebug(String value) {
		Logger.logDebug(value, null);
	}
	
	public static void logDebug(String value, String name) {
		if(level >= DEBUG_LEVEL)
			Logger.log(value, name, DEBUG, DEBUG_LEVEL);
	}
	
	public static void logFinest(String value) {
		Logger.logFinest(value,  null);
	}
	
	public static void logFinest(String value, String name) {
		if(level >= FINEST_LEVEL)
			Logger.log(value,  name,  FINEST, FINEST_LEVEL);
	}
	
	public static void setLogInfo() {
		Logger.setLoggingLevel(INFO_LEVEL);
	}
	
	public static void setLogDebug() {
		Logger.setLoggingLevel(DEBUG_LEVEL);
	}
	
	public static void setLogFinest() {
		Logger.setLoggingLevel(FINEST_LEVEL);
	}
	
	public static void setLoggingLevel(int value) {
		Logger.level = value;
	}
	
	public static boolean isDebug() {
		return Logger.level == DEBUG_LEVEL;
	}
	
	public static boolean isFinest() {
		return Logger.level == FINEST_LEVEL;
	}
	
}
