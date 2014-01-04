package com.BibleQuote.bqtj.dal;

import com.BibleQuote.bqtj.controllers.CacheModuleController;
import com.BibleQuote.bqtj.dal.repository.*;
import com.BibleQuote.bqtj.managers.EventManager;
import com.BibleQuote.bqtj.modules.FsBook;
import com.BibleQuote.bqtj.modules.FsModule;

public class FsLibraryUnitOfWork implements ILibraryUnitOfWork<String, FsModule, FsBook> {

	private FsLibraryContext libraryContext;
	private IModuleRepository<String, FsModule> moduleRepository;
	private IBookRepository<FsModule, FsBook> bookRepository;
	private IChapterRepository<FsBook> chapterRepository;
	private CacheModuleController<FsModule> cacheModuleController;

	public FsLibraryUnitOfWork(FsLibraryContext fsLibraryContext, CacheContext cacheContext) {
		this.libraryContext = fsLibraryContext;
		this.cacheModuleController = new CacheModuleController<FsModule>(cacheContext);
	}

	public LibraryContext getLibraryContext() {
		return this.libraryContext;
	}

	public IModuleRepository<String, FsModule> getModuleRepository() {
		if (this.moduleRepository == null) {
			this.moduleRepository = new FsModuleRepository(libraryContext);
		}
		return this.moduleRepository;
	}

	public IBookRepository<FsModule, FsBook> getBookRepository() {
		if (this.bookRepository == null) {
			this.bookRepository = new FsBookRepository(libraryContext);
		}
		return bookRepository;
	}

	public IChapterRepository<FsBook> getChapterRepository() {
		if (this.chapterRepository == null) {
			this.chapterRepository = new FsChapterRepository(libraryContext);
		}
		return chapterRepository;
	}

	public CacheModuleController<FsModule> getCacheModuleController() {
		return this.cacheModuleController;
	}

	public EventManager getEventManager() {
		return libraryContext.eventManager;
	}
}
