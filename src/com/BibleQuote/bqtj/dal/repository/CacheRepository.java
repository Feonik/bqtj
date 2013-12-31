package com.BibleQuote.bqtj.dal.repository;

import com.BibleQuote.bqtj.dal.CacheContext;
import com.BibleQuote.bqtj.exceptions.FileAccessException;
import com.BibleQuote.bqtj.utils.LogTxt;

public class CacheRepository<T> {
	private final String TAG = "CacheRepository";

	private CacheContext cacheContext;

	public CacheContext getCacheContext() {
		return cacheContext;
	}

	public CacheRepository(CacheContext cacheContext) {
		this.cacheContext = cacheContext;
	}

	public T getData() throws FileAccessException {
		LogTxt.i(TAG, "Loading data from a file system cache.");
		return cacheContext.loadData();
	}

	public void saveData(T data) throws FileAccessException {
		LogTxt.i(TAG, "Save modules to a file system cache.");
		cacheContext.saveData(data);
	}

	public boolean isCacheExist() {
		return cacheContext.isCacheExist();
	}
}
