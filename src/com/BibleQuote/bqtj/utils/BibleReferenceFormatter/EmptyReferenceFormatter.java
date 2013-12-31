package com.BibleQuote.bqtj.utils.BibleReferenceFormatter;

import com.BibleQuote.bqtj.modules.Book;
import com.BibleQuote.bqtj.modules.Module;

import java.util.TreeSet;

public class EmptyReferenceFormatter extends ReferenceFormatter implements IBibleReferenceFormatter {

	public EmptyReferenceFormatter(Module module, Book book, String chapter,
								   TreeSet<Integer> verses) {
		super(module, book, chapter, verses);
	}

	public String getLink() {
		return "";
	}

}
