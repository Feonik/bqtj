package com.BibleQuote.bqtj.listeners;

import com.BibleQuote.bqtj.modules.Book;

import java.util.ArrayList;

public class SearchInLibraryEvent {

	public ISearchListener.SearchCode code;
	public ArrayList<Book> books;
}
