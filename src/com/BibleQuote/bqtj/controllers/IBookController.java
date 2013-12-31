package com.BibleQuote.bqtj.controllers;

import com.BibleQuote.bqtj.exceptions.BookDefinitionException;
import com.BibleQuote.bqtj.exceptions.BookNotFoundException;
import com.BibleQuote.bqtj.exceptions.BooksDefinitionException;
import com.BibleQuote.bqtj.exceptions.OpenModuleException;
import com.BibleQuote.bqtj.modules.Book;
import com.BibleQuote.bqtj.modules.Module;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface IBookController {

	public LinkedHashMap<String, Book> getBooks(Module module) throws OpenModuleException, BooksDefinitionException, BookDefinitionException;

	/**
	 * Возвращает коллекцию Book для указанного модуля. Данные о книгах в первую
	 * очередь берутся из контекста библиотеки. Если там для выбранного модуля
	 * список книг отсутсвует, то производится загрузка коллекции Book из хранилища
	 *
	 * @param module модуль для которого необходимо получить коллекцию Book
	 * @return коллекцию Book для указанного модуля
	 * @throws OpenModuleException
	 * @throws BooksDefinitionException
	 * @throws BookDefinitionException
	 */
	public ArrayList<Book> getBookList(Module module) throws OpenModuleException, BooksDefinitionException, BookDefinitionException;

	public Book getBookByID(Module module, String bookID) throws BookNotFoundException, OpenModuleException;

	public LinkedHashMap<String, String> search(Module module, String query, String fromBookID, String toBookID) throws OpenModuleException, BookNotFoundException;

}
