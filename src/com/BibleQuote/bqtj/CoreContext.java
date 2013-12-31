package com.BibleQuote.bqtj;


import com.BibleQuote.bqtj.managers.Librarian;
import com.BibleQuote.bqtj.managers.bookmarks.repository.IBookmarksRepository;
import com.BibleQuote.bqtj.managers.bookmarks.repository.dbBookmarksRepository;
import com.BibleQuote.bqtj.utils.LogTxt;
import com.BibleQuote.bqtj.utils.PreferenceHelper;
import com.BibleQuote.bqtj.utils.UpdateManager;

import java.io.File;


public abstract class CoreContext {

	private static final String TAG = "CoreContext";

	private static CoreContext coreContext;

	private Librarian myLibrarian;


	protected CoreContext() {
		coreContext = this;
	}

	public static CoreContext getCoreContext() {

		if (coreContext == null) {
			// TODO исключение -- if (coreContext == null)
		}

		return coreContext;
	}


	public void Init() {

		LogTxt.i(TAG, "Init CoreContext...");

		LogTxt.i(TAG, "Init application preference helper...");
		initPreferenceHelper();

		LogTxt.i(TAG, "Start update manager...");
		getUpdateManager().Init();

		if (myLibrarian == null) {
			LogTxt.i(TAG, "Init library...");
			initLibrarian();
		}
	}

	public void RestartInit() {

		LogTxt.i(TAG, "RestartInit...");

		LogTxt.i(TAG, "Init application preference helper...");
		initPreferenceHelper();
		LogTxt.i(TAG, "Init library...");
		initLibrarian();
	}

	public IBookmarksRepository getBookmarksRepository() {
		//return new prefBookmarksRepository();
		return new dbBookmarksRepository();
	}


	private void initLibrarian() {
		myLibrarian = new Librarian(coreContext);
	}

	private void initPreferenceHelper() {
		PreferenceHelper.Init();
	}

	public Librarian getLibrarian() {
		if (myLibrarian == null) {
			// Сборщик мусора уничтожил ссылки на myLibrarian и на PreferenceHelper
			// Восстановим ссылки
			initPreferenceHelper();
			initLibrarian();
		}
		return myLibrarian;
	}


	public abstract String getAppVersionName();

	public abstract int getAppVersionCode();

	public abstract String getAppDataPath();

	public abstract File getCacheDir();

	protected abstract UpdateManager getUpdateManager();


	// --- Constants ---

	// Internal Device Memory
	public static final String APP_PACKAGE_DIR_NAME = "com.BibleQuote";


	// External Device Memory
	public static final String APP_DIR_NAME = "BibleQuote";


	// Caches
	private static final File APP_CACHE_DIR = coreContext.getCacheDir();
//	private static final String APP_CACHE_PATH = APP_DATA_PATH
//			+ File.separator + "cache";

	public static final String LIBRARY_CACHE_FILE_NAME = "library.cache";
	public static final String HISTORY_FILE_NAME = "history.dat";


	public static final String LOG_FILE_NAME = "log.txt";


	public static final String APP_DATA_PATH = coreContext.getAppDataPath();


	// Files
	private static final String FS_MODULES_DIR_NAME = "modules";
	public static final String FS_MODULE_INI_FILE_NAME = "bibleqt.ini";

	public static final String FS_MODULES_PATH = coreContext.getAppDataPath()
			+ File.separator + FS_MODULES_DIR_NAME;


	// Databases
	private static final String DB_DATA_DIR_NAME = "data";
	//	public static final String DB_LIBRARY_FILE_NAME = "library.db";
	public static final String DB_LIBRARY_FILE_NAME = "library";

	public static final String DB_DATA_PATH = coreContext.getAppDataPath()
			+ File.separator + DB_DATA_DIR_NAME;


	// Preferences
	private static final String DB_PREFERENCES_DIR_NAME = "preferences";
	public static final String DB_PREFERENCES_FILE_NAME = "preferences";

	public static final String DB_PREFERENCES_PATH = coreContext.getAppDataPath()
			+ File.separator + DB_PREFERENCES_DIR_NAME;

	public static final String DB_PREFERENCES_URL = "jdbc:hsqldb:file:"
			+ CoreContext.DB_PREFERENCES_PATH + File.separator
			+ CoreContext.DB_PREFERENCES_FILE_NAME;


	// Database Tables
	public static final String MODULE_TABLE = "module";
	public static final String BOOK_TABLE = "book";
	public static final String BOOKMARKS_TABLE = "bookmarks";
	public static final String BOOKMARKS_TAGS_TABLE = "bookmarks_tags";
	public static final String TAGS_TABLE = "tags";

}
