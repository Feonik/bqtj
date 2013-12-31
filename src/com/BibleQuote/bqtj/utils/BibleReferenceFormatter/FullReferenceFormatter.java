package com.BibleQuote.bqtj.utils.BibleReferenceFormatter;

import com.BibleQuote.bqtj.modules.Book;
import com.BibleQuote.bqtj.modules.Module;
import com.BibleQuote.bqtj.utils.PreferenceHelper;

import java.util.TreeSet;

public class FullReferenceFormatter extends ReferenceFormatter implements IBibleReferenceFormatter {

	public FullReferenceFormatter(Module module, Book book, String chapter,
								  TreeSet<Integer> verses) {
		super(module, book, chapter, verses);
	}

	public String getLink() {

		String result = String.format(
				"%1$s %2$s:%3$s",
				book.name, chapter, getVerseLink());
		if (PreferenceHelper.addModuleToBibleReference()) {
			result = String.format("%1$s|%2$s", result, module.getID());
		}
		result = String.format("%1$s-%2$s", result, getOnLineBibleLink());
		return result;
	}

}
