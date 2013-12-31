package com.BibleQuote.bqtj.dal;

import com.BibleQuote.bqtj.controllers.CacheModuleController;
import com.BibleQuote.bqtj.dal.repository.IBookRepository;
import com.BibleQuote.bqtj.dal.repository.IChapterRepository;
import com.BibleQuote.bqtj.dal.repository.IModuleRepository;
import com.BibleQuote.bqtj.managers.EventManager;

public interface ILibraryUnitOfWork<TModuleId, TModule, TBook> {

	public IModuleRepository<TModuleId, TModule> getModuleRepository();

	public IBookRepository<TModule, TBook> getBookRepository();

	public IChapterRepository<TBook> getChapterRepository();

	public LibraryContext getLibraryContext();

	public CacheModuleController<TModule> getCacheModuleController();

	EventManager getEventManager();
}
