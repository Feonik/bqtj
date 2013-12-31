package com.BibleQuote.bqtj.listeners;

import com.BibleQuote.bqtj.listeners.IChangeBooksListener.ChangeCode;
import com.BibleQuote.bqtj.modules.Book;
import com.BibleQuote.bqtj.modules.Module;

import java.util.LinkedHashMap;

public class ChangeBooksEvent {

	public IChangeBooksListener.ChangeCode code;
	public Module module;
	public LinkedHashMap<String, Book> books;

	public ChangeBooksEvent(ChangeCode code, Module module, LinkedHashMap<String, Book> books) {
		this.code = code;
		this.books = books;
		this.module = module;
	}

}
