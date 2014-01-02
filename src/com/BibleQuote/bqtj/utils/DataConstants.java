package com.BibleQuote.bqtj.utils;

import com.BibleQuote.bqtj.CoreContext;

import java.io.File;

public final class DataConstants {

	private static DataConstants dataConstants;

	private DataConstants() {
	}

	public static synchronized void Init() {

		if (dataConstants == null) {
			dataConstants = new DataConstants();
		}
	}


	// Internal Device Memory
	public static final String APP_PACKAGE_DIR_NAME = "com.BibleQuote";


	// External Device Memory
	public static final String APP_DIR_NAME = "BibleQuote";


	// Caches
	private static final File APP_CACHE_DIR =
			CoreContext.getCoreContext().getCacheDir();

//	private static final String APP_CACHE_PATH = APP_DATA_PATH
//			+ File.separator + "cache";

	public static final String LIBRARY_CACHE_FILE_NAME = "library.cache";
	public static final String HISTORY_FILE_NAME = "history.dat";


	public static final String LOG_FILE_NAME = "log.txt";


	public static final String APP_DATA_PATH =
			CoreContext.getCoreContext().getAppDataPath();


	// Files
	private static final String FS_MODULES_DIR_NAME = "modules";
	public static final String FS_MODULE_INI_FILE_NAME = "bibleqt.ini";

	public static final String FS_MODULES_PATH =
			CoreContext.getCoreContext().getAppDataPath()
			+ File.separator + FS_MODULES_DIR_NAME;


	// Databases
	private static final String DB_DATA_DIR_NAME = "data";
	//	public static final String DB_LIBRARY_FILE_NAME = "library.db";
	public static final String DB_LIBRARY_FILE_NAME = "library";

	public static final String DB_DATA_PATH =
			CoreContext.getCoreContext().getAppDataPath()
			+ File.separator + DB_DATA_DIR_NAME;


	// Preferences
	private static final String DB_PREFERENCES_DIR_NAME = "preferences";
	public static final String DB_PREFERENCES_FILE_NAME = "preferences";

	public static final String DB_PREFERENCES_PATH =
			CoreContext.getCoreContext().getAppDataPath()
			+ File.separator + DB_PREFERENCES_DIR_NAME;

	public static final String DB_PREFERENCES_URL = "jdbc:hsqldb:file:"
			+ DB_PREFERENCES_PATH + File.separator	+ DB_PREFERENCES_FILE_NAME;


	// Database Tables
	public static final String MODULE_TABLE = "module";
	public static final String BOOK_TABLE = "book";
	public static final String BOOKMARKS_TABLE = "bookmarks";
	public static final String BOOKMARKS_TAGS_TABLE = "bookmarks_tags";
	public static final String TAGS_TABLE = "tags";

}
