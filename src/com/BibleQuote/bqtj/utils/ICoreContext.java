package com.BibleQuote.bqtj.utils;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Nikita K.
 * Date: 12.11.13
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
public interface ICoreContext {

	public String getAppVersionName();

	public int getAppVersionCode();

	public String getAppDataPath();

	public File getCacheDir();

}
