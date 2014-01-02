package com.BibleQuote.bqtj;


import com.BibleQuote.bqtj.managers.Librarian;
import com.BibleQuote.bqtj.managers.bookmarks.repository.IBookmarksRepository;
//import com.BibleQuote.bqtj.managers.bookmarks.repository.dbBookmarksRepository;
import com.BibleQuote.bqtj.utils.DataConstants;
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

		// TODO log messages
		LogTxt.i(TAG, "Init CoreContext...");

		LogTxt.i(TAG, "Init DataConstants...");
		DataConstants.Init();

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
//		return new dbBookmarksRepository();
		return null;
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

}
