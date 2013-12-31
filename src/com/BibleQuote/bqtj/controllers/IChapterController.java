package com.BibleQuote.bqtj.controllers;

import com.BibleQuote.bqtj.exceptions.BookNotFoundException;
import com.BibleQuote.bqtj.modules.Book;
import com.BibleQuote.bqtj.modules.Chapter;
import com.BibleQuote.bqtj.modules.ChapterQueueList;
import com.BibleQuote.bqtj.modules.Module;

import java.util.ArrayList;

public interface IChapterController {

	public ArrayList<Chapter> getChapterList(Book book) throws BookNotFoundException;

	public Chapter getChapter(Book book, Integer chapterNumber, Boolean isReload) throws BookNotFoundException;

	public boolean saveChapter(Chapter chapter);

	public ArrayList<Chapter> getAllChapters(Book book) throws BookNotFoundException;

	public ArrayList<Integer> getVerseNumbers(Book book, Integer chapterNumber) throws BookNotFoundException;

	public String getChapterHTMLView(Chapter chapter);

	public String getParChapterHTMLView(Chapter chapter, ChapterQueueList chapterQueueList);

	public String getVerseTextHtmlBody(Module module, String sVerseText);
}
