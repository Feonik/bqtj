package com.BibleQuote.bqtj.dal.repository;

import com.BibleQuote.bqtj.exceptions.BQUniversalException;
import com.BibleQuote.bqtj.exceptions.TskNotFoundException;

public interface ITskRepository {
	String getReferences(String book, String chapter, String verse) throws TskNotFoundException, BQUniversalException;
}
