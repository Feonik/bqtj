package com.BibleQuote.bqtj.dal.repository;

import com.BibleQuote.bqtj.exceptions.BookNotFoundException;
import com.BibleQuote.bqtj.modules.Chapter;
import com.BibleQuote.bqtj.modules.FsBook;

import java.util.ArrayList;
import java.util.Collection;

public interface IChapterRepository<TBook> {

	/*
	 * Data source related methods
	 * 
	 */
	Collection<Chapter> loadChapters(TBook book) throws BookNotFoundException;

	Chapter loadChapter(TBook book, Integer chapterNumber) throws BookNotFoundException;

	public ArrayList<Chapter> loadAllChapters(FsBook book) throws BookNotFoundException;

	boolean saveChapter(Chapter chapter);

	void insertChapter(Chapter chapter);

	void deleteChapter(Chapter chapter);

	void updateChapter(Chapter chapter);

	/*
	 * Internal cache related methods
	 *
	 */
	Collection<Chapter> getChapters(TBook book);

	Chapter getChapterByNumber(TBook book, Integer chapterNumber);

}
