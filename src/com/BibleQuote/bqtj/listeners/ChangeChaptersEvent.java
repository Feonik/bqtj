package com.BibleQuote.bqtj.listeners;

import com.BibleQuote.bqtj.listeners.IChangeChaptersListener.ChangeCode;
import com.BibleQuote.bqtj.modules.Book;
import com.BibleQuote.bqtj.modules.Chapter;
import com.BibleQuote.bqtj.modules.Module;

public class ChangeChaptersEvent {

	public IChangeChaptersListener.ChangeCode code;
	public Module module;
	public Book book;
	public Chapter chapter;

	public ChangeChaptersEvent(ChangeCode code, Module module, Book book, Chapter chapter) {
		this.code = code;
		this.module = module;
		this.book = book;
		this.chapter = chapter;
	}

}
