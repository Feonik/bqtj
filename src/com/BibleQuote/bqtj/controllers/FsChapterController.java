package com.BibleQuote.bqtj.controllers;

import com.BibleQuote.bqtj.dal.LibraryUnitOfWork;
import com.BibleQuote.bqtj.dal.repository.IBookRepository;
import com.BibleQuote.bqtj.dal.repository.IChapterRepository;
import com.BibleQuote.bqtj.exceptions.BookNotFoundException;
import com.BibleQuote.bqtj.modules.*;
import com.BibleQuote.bqtj.utils.StringProc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FsChapterController implements IChapterController {
	//private final String TAG = "FsChapterController";

	private IBookRepository<FsModule, FsBook> bRepository;
	private IChapterRepository<FsBook> chRepository;


	public FsChapterController(LibraryUnitOfWork unit) {
		bRepository = unit.getBookRepository();
		chRepository = unit.getChapterRepository();
	}


	public ArrayList<Chapter> getChapterList(Book book) throws BookNotFoundException {
		book = getValidBook(book);
		ArrayList<Chapter> chapterList = (ArrayList<Chapter>) chRepository.getChapters((FsBook) book);
		if (chapterList.size() == 0) {
			chapterList = (ArrayList<Chapter>) chRepository.loadChapters((FsBook) book);
		}
		return chapterList;
	}


	public Chapter getChapter(Book book, Integer chapterNumber, Boolean isReload) throws BookNotFoundException {
		book = getValidBook(book);
		Chapter chapter;

		if (isReload) {
			chapter = chRepository.loadChapter((FsBook) book, chapterNumber);
		} else {
			chapter = chRepository.getChapterByNumber((FsBook) book, chapterNumber);
			if (chapter == null) {
				chapter = chRepository.loadChapter((FsBook) book, chapterNumber);
			}
		}

		return chapter;
	}


	public ArrayList<Chapter> getAllChapters(Book book) throws BookNotFoundException {
		book = getValidBook(book);
		ArrayList<Chapter> ChapterList = chRepository.loadAllChapters((FsBook) book);
		return ChapterList;
	}


	public boolean saveChapter(Chapter chapter) {
		return chRepository.saveChapter(chapter);
	}


	public ArrayList<Integer> getVerseNumbers(Book book, Integer chapterNumber) throws BookNotFoundException {
		book = getValidBook(book);
		Chapter chapter = chRepository.getChapterByNumber((FsBook) book, chapterNumber);
		if (chapter == null) {
			chapter = chRepository.loadChapter((FsBook) book, chapterNumber);
		}
		return chapter.getVerseNumbers();
	}


	public String getChapterHTMLView(Chapter chapter) {
		if (chapter == null) {
			return "";
		}
		Module currModule = chapter.getBook().getModule();

		ArrayList<Verse> verses = chapter.getVerseList();
		StringBuilder chapterHTML = new StringBuilder();
		for (int verse = 1; verse <= verses.size(); verse++) {
			chapterHTML.append("<div id=\"verse_")
					  .append(verse)
					  .append("\" class=\"verse\">")
					  .append(getVerseTextHtmlBody(currModule, verses.get(verse - 1).getText()))
					  .append("</div>")
					  .append("\r\n");
		}

		return chapterHTML.toString();
	}


	public String getVerseTextHtmlBody(Module module, String sVerseText) {

		if (module == null || sVerseText == null || sVerseText.length() == 0) {
			return "";
		}

		if (module.containsStrong) {
			// убираем номера Стронга
			sVerseText = sVerseText.replaceAll("\\s(\\d)+", "");
		}

		sVerseText = StringProc.stripTags(sVerseText, module.HtmlFilter);
		sVerseText = sVerseText.replaceAll("<a\\s+?href=\"verse\\s\\d+?\">(\\d+?)</a>", "<b>$1</b>");


		StringBuilder sbNewVerseText = new StringBuilder();

		if (module.isBible) {

			int iVerseTextRegionStart = 0;
			int iVerseTextRegionEnd = sVerseText.length();

			Matcher matcher = Pattern.compile("((^|\\n)(<[^/]+?>)*?)(\\d+)(</(.)+?>){0,1}?\\s+").matcher(sVerseText);

			if (matcher.find()) {

				sbNewVerseText
						  .append(sVerseText.substring(iVerseTextRegionStart, matcher.start()))
						  .append((matcher.group(1) == null) ? "" : matcher.group(1))
						  .append("<b>")
						  .append((matcher.group(4) == null) ? "" : matcher.group(4))
						  .append("</b>")
						  .append((matcher.group(5) == null) ? "" : matcher.group(5))
						  .append(" ");

				iVerseTextRegionStart = matcher.end();
			}


			int iTagRegionStart = iVerseTextRegionStart;
			int iTagRegionEnd = iVerseTextRegionStart;
			int iMcrEnd = iVerseTextRegionStart;

			matcher.usePattern(Pattern.compile("(^|\\G|[^\\p{L}\\d])([\\p{L}\\d]+?)([^\\p{L}\\d]|$)"));

			if ((iTagRegionStart = sVerseText.indexOf("<", iVerseTextRegionStart)) != -1) {

				matcher.region(iVerseTextRegionStart, iTagRegionStart);
				while (matcher.find()) {
					sbNewVerseText
							  .append((matcher.group(1) == null) ? "" : matcher.group(1))
							  .append("<span>")
							  .append((matcher.group(2) == null) ? "" : matcher.group(2))
							  .append("</span>")
							  .append((matcher.group(3) == null) ? "" : matcher.group(3));
					iMcrEnd = matcher.end();
				}

				sbNewVerseText
						  .append(sVerseText.substring(iMcrEnd, iTagRegionStart));

				if ((iTagRegionEnd = sVerseText.indexOf(">", iTagRegionStart)) != -1) {
					sbNewVerseText
							  .append(sVerseText.substring(iTagRegionStart, iTagRegionEnd + 1));
				} else {
					return "";
				}


				while ((iTagRegionStart = sVerseText.indexOf("<", iTagRegionEnd)) != -1) {

					matcher.region(iTagRegionEnd + 1, iTagRegionStart);
					iMcrEnd = iTagRegionEnd + 1;
					while (matcher.find()) {
						sbNewVerseText
								  .append((matcher.group(1) == null) ? "" : matcher.group(1))
								  .append("<span>")
								  .append((matcher.group(2) == null) ? "" : matcher.group(2))
								  .append("</span>")
								  .append((matcher.group(3) == null) ? "" : matcher.group(3));
						iMcrEnd = matcher.end();
					}
					sbNewVerseText
							  .append(sVerseText.substring(iMcrEnd, iTagRegionStart));

					if ((iTagRegionEnd = sVerseText.indexOf(">", iTagRegionStart)) != -1) {
						sbNewVerseText
								  .append(sVerseText.substring(iTagRegionStart, iTagRegionEnd + 1));
					} else {
						return "";
					}
				}


				matcher.region(iTagRegionEnd + 1, iVerseTextRegionEnd);
				iMcrEnd = iTagRegionEnd + 1;
				while (matcher.find()) {
					sbNewVerseText
							  .append((matcher.group(1) == null) ? "" : matcher.group(1))
							  .append("<span>")
							  .append((matcher.group(2) == null) ? "" : matcher.group(2))
							  .append("</span>")
							  .append((matcher.group(3) == null) ? "" : matcher.group(3));
					iMcrEnd = matcher.end();
				}
				sbNewVerseText
						  .append(sVerseText.substring(iMcrEnd, iVerseTextRegionEnd));


			} else {
				matcher.region(iVerseTextRegionStart, iVerseTextRegionEnd);
				while (matcher.find()) {
					sbNewVerseText
							  .append((matcher.group(1) == null) ? "" : matcher.group(1))
							  .append("<span>")
							  .append((matcher.group(2) == null) ? "" : matcher.group(2))
							  .append("</span>")
							  .append((matcher.group(3) == null) ? "" : matcher.group(3));
					iMcrEnd = matcher.end();
				}
				sbNewVerseText
						  .append(sVerseText.substring(iMcrEnd, iVerseTextRegionEnd));
			}
		}

		return sbNewVerseText.toString().replaceAll("<(/)*div(.*?)>", "<$1p$2>");
	}


	public String getParChapterHTMLView(Chapter chapter, ChapterQueueList chapterQueueList) {

		if (chapterQueueList == null && chapter !=null) {
			return getChapterHTMLView(chapter);

		} else if (chapterQueueList == null || chapterQueueList.isEmpty()) {
			return "";

		} else {

			ChapterQueue chapterQueue = chapterQueueList.get(0);

			if (chapterQueue == null || chapterQueue.isEmpty()) {
				return "";
			}


			Module module;

			VerseQueue verseQueue;
			VerseQueue verseQueueNext;

			boolean isNormal;
			boolean isRepeated;
			boolean isSequenced;

			boolean isNormalNext;
			boolean isRepeatedNext;
			boolean isSequencedNext;

			StringBuilder chapterHTML = new StringBuilder();


			boolean isEmptyChapQueueList = chapterQueueList.isEmpty();
			int iChapterListSize = chapterQueueList.size();

			while (!isEmptyChapQueueList) {

				for (int iChQ = 0; iChQ < iChapterListSize; iChQ++) {

					chapterQueue = chapterQueueList.get(iChQ);

					if (chapterQueue != null) {

						module = chapterQueue.getBook().getModule();

						do {
							verseQueue = chapterQueue.poll();
							verseQueueNext = chapterQueue.peek();

							isNormal = false;
							isRepeated = false;
							isSequenced = false;

							isNormalNext = false;
							isRepeatedNext = false;
							isSequencedNext = false;


							if (verseQueueNext != null) {
								isNormalNext = ((verseQueueNext.getSequenceFlags() & VerseQueue.SEQ_NORMAL) == VerseQueue.SEQ_NORMAL);
								isRepeatedNext = ((verseQueueNext.getSequenceFlags() & VerseQueue.SEQ_REPEATED) == VerseQueue.SEQ_REPEATED);
								isSequencedNext = ((verseQueueNext.getSequenceFlags() & VerseQueue.SEQ_SEQUENCED) == VerseQueue.SEQ_SEQUENCED);
							}


							if (verseQueue != null) {
								isNormal = ((verseQueue.getSequenceFlags() & VerseQueue.SEQ_NORMAL) == VerseQueue.SEQ_NORMAL);
								isRepeated = ((verseQueue.getSequenceFlags() & VerseQueue.SEQ_REPEATED) == VerseQueue.SEQ_REPEATED);
								isSequenced = ((verseQueue.getSequenceFlags() & VerseQueue.SEQ_SEQUENCED) == VerseQueue.SEQ_SEQUENCED);


								int iChapterNumber = verseQueue.getChapter();
								int iVerseNumber = verseQueue.getNumber();

								String verseText = verseQueue.getText();

								if (module.containsStrong) {
									// убираем номера Стронга
									verseText = verseText.replaceAll("\\s(\\d)+", "");
								}

								verseText = StringProc.stripTags(verseText, module.HtmlFilter);
								verseText = verseText.replaceAll("<a\\s+?href=\"verse\\s\\d+?\">(\\d+?)</a>", "<b>$1</b>");


								StringBuilder sbNewVerseText = new StringBuilder();

								if (module.isBible) {

									int iVerseTextRegionStart = 0;
									int iVerseTextRegionEnd = verseText.length();

									Matcher matcher = Pattern
											  .compile("((^|\\n)(<[^/]+?>)*?)([\\w\\-\\+]+?[.])*?(\\d+.\\d+)(</(.)+?>){0,1}?\\s+")
											  .matcher(verseText);

									if (matcher.find()) {

										sbNewVerseText
												  .append(verseText.substring(iVerseTextRegionStart, matcher.start()))
												  .append((matcher.group(1) == null) ? "" : matcher.group(1))
												  .append("<b>")
												  .append((matcher.group(4) == null) ? "" : matcher.group(4))
												  .append((matcher.group(5) == null) ? "" : matcher.group(5))
												  .append("</b>")
												  .append((matcher.group(6) == null) ? "" : matcher.group(6))
												  .append(" ");

										iVerseTextRegionStart = matcher.end();
									}


									int iTagRegionStart = iVerseTextRegionStart;
									int iTagRegionEnd = iVerseTextRegionStart;
									int iMcrEnd = iVerseTextRegionStart;

									matcher.usePattern(Pattern.compile("(^|\\G|[^\\p{L}\\d])([\\p{L}\\d]+?)([^\\p{L}\\d]|$)"));

									if ((iTagRegionStart = verseText.indexOf("<", iVerseTextRegionStart)) != -1) {

										matcher.region(iVerseTextRegionStart, iTagRegionStart);
										while (matcher.find()) {
											sbNewVerseText
													  .append((matcher.group(1) == null) ? "" : matcher.group(1))
													  .append("<span>")
													  .append((matcher.group(2) == null) ? "" : matcher.group(2))
													  .append("</span>")
													  .append((matcher.group(3) == null) ? "" : matcher.group(3));
											iMcrEnd = matcher.end();
										}

										sbNewVerseText
												  .append(verseText.substring(iMcrEnd, iTagRegionStart));

										if ((iTagRegionEnd = verseText.indexOf(">", iTagRegionStart)) != -1) {
											sbNewVerseText
													  .append(verseText.substring(iTagRegionStart, iTagRegionEnd + 1));
										} else {
											return "";
										}


										while ((iTagRegionStart = verseText.indexOf("<", iTagRegionEnd)) != -1) {

											matcher.region(iTagRegionEnd + 1, iTagRegionStart);
											iMcrEnd = iTagRegionEnd + 1;
											while (matcher.find()) {
												sbNewVerseText
														  .append((matcher.group(1) == null) ? "" : matcher.group(1))
														  .append("<span>")
														  .append((matcher.group(2) == null) ? "" : matcher.group(2))
														  .append("</span>")
														  .append((matcher.group(3) == null) ? "" : matcher.group(3));
												iMcrEnd = matcher.end();
											}
											sbNewVerseText
													  .append(verseText.substring(iMcrEnd, iTagRegionStart));

											if ((iTagRegionEnd = verseText.indexOf(">", iTagRegionStart)) != -1) {
												sbNewVerseText
														  .append(verseText.substring(iTagRegionStart, iTagRegionEnd + 1));
											} else {
												return "";
											}
										}


										matcher.region(iTagRegionEnd + 1, iVerseTextRegionEnd);
										iMcrEnd = iTagRegionEnd + 1;
										while (matcher.find()) {
											sbNewVerseText
													  .append((matcher.group(1) == null) ? "" : matcher.group(1))
													  .append("<span>")
													  .append((matcher.group(2) == null) ? "" : matcher.group(2))
													  .append("</span>")
													  .append((matcher.group(3) == null) ? "" : matcher.group(3));
											iMcrEnd = matcher.end();
										}
										sbNewVerseText
												  .append(verseText.substring(iMcrEnd, iVerseTextRegionEnd));


									} else {
										matcher.region(iVerseTextRegionStart, iVerseTextRegionEnd);
										while (matcher.find()) {
											sbNewVerseText
													  .append((matcher.group(1) == null) ? "" : matcher.group(1))
													  .append("<span>")
													  .append((matcher.group(2) == null) ? "" : matcher.group(2))
													  .append("</span>")
													  .append((matcher.group(3) == null) ? "" : matcher.group(3));
											iMcrEnd = matcher.end();
										}
										sbNewVerseText
												  .append(verseText.substring(iMcrEnd, iVerseTextRegionEnd));
									}
								}


								// отображение чередующимися строками
								if (iChQ == 0) {
									chapterHTML.append(
											  "<div id=\"verse_" + iVerseNumber + "\" class=\"verse\">"
														 + sbNewVerseText.toString().replaceAll("<(/)*div(.*?)>", "<$1p$2>")
														 + "</div>"
														 + "\r\n");
								} else {
									chapterHTML.append(
											  "<div>"
														 + sbNewVerseText.toString().replaceAll("<(/)*div(.*?)>", "<$1p$2>")
														 + "</div>"
														 + "\r\n");
								}
							}
						} while (isSequenced && isSequencedNext);


						if (iChQ > 0 && iChQ == (iChapterListSize - 1)) {
							chapterHTML.append("<br>\r\n");
						}
					}
				}

				isEmptyChapQueueList = chapterQueueList.isEmpty();
			}


			//!!!!
			/*
			// отображение таблицей (пока криво)
			chapterHTML.append("<table cols=\"2\"><col width=\"50%\"/><tr><td align=\"left\">");

			chapterHTML.append(
					"<div id=\"verse_" + verse + "\" class=\"verse\">"
							+ verseText.replaceAll("<(/)*div(.*?)>", "<$1p$2>")
							+ "</div>"
							+ "</td>\r\n");

			chapterHTML.append(
					"<td align=\"left\"><div id=\"verse_" + verse + "\" class=\"verse\">"
							+ ParVerseText.replaceAll("<(/)*div(.*?)>", "<$1p$2>")
							+ "</div>"
							+ "</td></tr></table>\r\n");

        	//chapterHTML.append("<br>\r\n");
			*/


			return chapterHTML.toString();
		}
	}


	private Book getValidBook(Book book) throws BookNotFoundException {
		String moduleID = null;
		String bookID = null;
		try {
			Module module = book.getModule();
			book = bRepository.getBookByID((FsModule) module, book.getID());
			if (book == null) {
				throw new BookNotFoundException(moduleID, bookID);
			}
		} catch (Exception e) {
			throw new BookNotFoundException(moduleID, bookID);
		}
		return book;
	}
}
