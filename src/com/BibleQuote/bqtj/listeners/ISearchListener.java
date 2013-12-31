package com.BibleQuote.bqtj.listeners;

public interface ISearchListener {
	public enum SearchCode {
		Found,
		NotFound
	}

	void onSearchInLibrary(SearchInLibraryEvent event);
}
