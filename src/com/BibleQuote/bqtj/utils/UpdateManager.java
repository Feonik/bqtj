package com.BibleQuote.bqtj.utils;

import com.BibleQuote.bqtj.CoreContext;
import com.BibleQuote.bqtj.managers.bookmarks.Bookmark;
import com.BibleQuote.bqtj.managers.bookmarks.BookmarksManager;
//+import com.BibleQuote.bqtj.managers.bookmarks.repository
// .dbBookmarksRepository;
import com.BibleQuote.bqtj.managers.bookmarks.repository.prefBookmarksRepository;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Nikita K.
 * Date: 16.11.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public abstract class UpdateManager {

	protected final static String TAG = "UpdateManager";

	// TODO static + all static fields


	public void Init() {

		// Инициализация каталога программы
		File dir_modules = new File(DataConstants.FS_MODULES_PATH);
		if (!dir_modules.exists()) {
			Log.i(TAG, String.format("Create directory %1$s", dir_modules));
			dir_modules.mkdirs();
		}

		// Инициализация каталога базы данных настроек
		File dirPreferences = new File(DataConstants.DB_PREFERENCES_PATH);
		if (!dirPreferences.exists()) {
			Log.i(TAG, String.format("Create directory %1$s", dirPreferences));
			dirPreferences.mkdirs();
		}

		int currVersionCode = PreferenceHelper.getAppVersionCode();

		boolean updateModules = false;
		if (currVersionCode == 0) {
			updateModules = true;
		}

		if (currVersionCode < 39) {
			Log.i(TAG, "Update to version 0.05.02");
			saveTSK();
		}

		if (currVersionCode < 53) {
			updateModules = true;
		}

		if (currVersionCode < 59) {
			convertBookmarks_59();
		}

		if (updateModules) {
			Log.i(TAG, "Update built-in modules on external storage");
			updateBuiltInModules();
		}

		int versionCode = CoreContext.getCoreContext().getAppVersionCode();

		if (versionCode == 0) {
			versionCode = 39;
		}

		PreferenceHelper.setAppVersionCode(versionCode);
	}

	private static void convertBookmarks_59() {
		Log.d(TAG, "Convert bookmarks");
//+		BookmarksManager newBM = new BookmarksManager(new
// dbBookmarksRepository());
		ArrayList<Bookmark> bookmarks = new BookmarksManager(new prefBookmarksRepository()).getAll();
//+		for (Bookmark curr : bookmarks) {
//+			newBM.add(curr.OSISLink, curr.humanLink);
//+		}
	}

	protected abstract void updateBuiltInModules();

	protected abstract void saveTSK();

}
