package com.BibleQuote.bqtj.controllers;

import com.BibleQuote.bqtj.dal.*;
import com.BibleQuote.bqtj.managers.EventManager;
import com.BibleQuote.bqtj.modules.FsModule;
import com.BibleQuote.bqtj.CoreContext;
import com.BibleQuote.bqtj.utils.DataConstants;

import java.io.File;

public class LibraryController {

	private IModuleController moduleCtrl;

	public IModuleController getModuleCtrl() {
		return moduleCtrl;
	}

	private IBookController bookCtrl;

	public IBookController getBookCtrl() {
		return bookCtrl;
	}

	private IChapterController chapterCtrl;

	public IChapterController getChapterCtrl() {
		return chapterCtrl;
	}

	private ILibraryUnitOfWork unit;

	public ILibraryUnitOfWork getUnit() {
		return unit;
	}


	public static LibraryController create(CoreContext coreContext) {
		String libraryPath = DataConstants.FS_MODULES_PATH;

		CacheContext cacheContext = new CacheContext(coreContext.getCacheDir(), DataConstants.LIBRARY_CACHE_FILE_NAME);
		CacheModuleController<FsModule> cache = new CacheModuleController<FsModule>(cacheContext);

		LibraryContext libraryContext = new FsLibraryContext(new File(libraryPath), coreContext, cache);
		return new LibraryController(new LibraryUnitOfWork((FsLibraryContext) libraryContext, cacheContext));
	}

	private LibraryController(LibraryUnitOfWork unit) {
		this.unit = unit;
		moduleCtrl = new FsModuleController(unit);
		bookCtrl = new FsBookController(unit);
		chapterCtrl = new FsChapterController(unit);
	}

	public EventManager getEventManager() {
		return unit.getEventManager();
	}
}
