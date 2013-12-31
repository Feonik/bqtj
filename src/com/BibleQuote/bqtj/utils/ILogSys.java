package com.BibleQuote.bqtj.utils;

/**
 * Created with IntelliJ IDEA.
 * User: Nikita K.
 * Date: 17.11.13
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */
public interface ILogSys {

	public void d(String tag, String msg);

	public void d(String tag, String msg, Throwable tr);

	public void e(String tag, String msg);

	public void e(String tag, String msg, Throwable tr);

	public void i(String tag, String msg);

	public void i(String tag, String msg, Throwable tr);

	public void w(String tag, String msg);

	public void w(String tag, String msg, Throwable tr);

}
