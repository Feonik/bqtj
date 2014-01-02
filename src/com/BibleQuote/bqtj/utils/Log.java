package com.BibleQuote.bqtj.utils;

/**
 * Created with IntelliJ IDEA.
 * User: Nikita K.
 * Date: 17.11.13
 * Time: 0:02
 * To change this template use File | Settings | File Templates.
 */
public class Log {

	private static Log log;

	private static ILogSys logSys;

	private Log() {
	}

	public static synchronized void Init(ILogSys logSys) {

		if (log == null) {
			log = new Log();
			Log.logSys = logSys;
		}
	}


	public static void d(String tag, String msg) {
		logSys.d(tag, msg);
	}

	public static void d(String tag, String msg, Throwable tr) {
		logSys.d(tag, msg, tr);
	}

	public static void e(String tag, String msg) {
		logSys.e(tag, msg);
	}

	public static void e(String tag, String msg, Throwable tr) {
		logSys.e(tag, msg, tr);
	}

	public static void i(String tag, String msg) {
		logSys.i(tag, msg);
	}

	public static void i(String tag, String msg, Throwable tr) {
		logSys.i(tag, msg, tr);
	}

	public static void w(String tag, String msg) {
		logSys.w(tag, msg);
	}

	public static void w(String tag, String msg, Throwable tr) {
		logSys.w(tag, msg, tr);
	}

}
