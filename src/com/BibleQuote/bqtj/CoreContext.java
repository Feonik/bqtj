package com.BibleQuote.bqtj;


import com.BibleQuote.bqtj.managers.Librarian;
import com.BibleQuote.bqtj.managers.bookmarks.repository.IBookmarksRepository;
//+import com.BibleQuote.bqtj.managers.bookmarks.repository
// .dbBookmarksRepository;
import com.BibleQuote.bqtj.utils.*;

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

		// TODO log messages
		LogTxt.i(TAG, "Init CoreContext...");

		// Инициализация каталога модулей
		InitPath(DataConstants.FS_MODULES_PATH);

		// Инициализация каталога настроек
		InitPath(DataConstants.DB_PREFERENCES_PATH);

		// Инициализация каталога истории
		InitPath(DataConstants.HISTORY_PATH);

		// Инициализация каталога кэша
		InitPath(DataConstants.CACHE_PATH);

		initPreferenceHelper();

		initUpdateManager();

		if (myLibrarian == null) {
			initLibrarian();
		}
	}

	public void RestartInit() {

		LogTxt.i(TAG, "RestartInit...");

		initPreferenceHelper();
		initLibrarian();
	}

	private void InitPath(String path) {

		File filePath = new File(path);

		if (!filePath.exists()) {
			LogTxt.i(TAG, String.format("Create directory %1$s", filePath));

			if (!filePath.mkdirs()) {
				LogTxt.e(TAG, String.format("Can not create directory %1$s",
						filePath));
				System.exit(1);
			}
		}
	}

	private void initPreferenceHelper() {
		LogTxt.i(TAG, "Init application preference helper...");
		PreferenceHelper.Init();
	}

	private void initUpdateManager() {
		LogTxt.i(TAG, "Start update manager...");
		getUpdateManager().Init();
	}

	private void initLibrarian() {
		LogTxt.i(TAG, "Init library...");
		myLibrarian = new Librarian(coreContext);
	}

	public Librarian getLibrarian() {
		if (myLibrarian == null) {
			// Сборщик мусора уничтожил ссылки на myLibrarian и на PreferenceHelper
			// Восстановим ссылки
			LogTxt.i(TAG, "Recovery of library links...");
			initPreferenceHelper();
			initLibrarian();
		}
		return myLibrarian;
	}

	public IBookmarksRepository getBookmarksRepository() {
		//return new prefBookmarksRepository();
//+		return new dbBookmarksRepository();
		return null;
	}

	public abstract String getAppVersionName();

	public abstract int getAppVersionCode();

	public abstract String getAppDataPath();

	public abstract File getSystemCacheDir();

	protected abstract UpdateManager getUpdateManager();

}
