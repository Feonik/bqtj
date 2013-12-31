package com.BibleQuote.bqtj.managers.tags.repository;
import java.util.*;
import com.BibleQuote.bqtj.managers.tags.*;

public interface ITagRepository {
	long add(String tag);
	int update(Tag tag);
	int delete(Tag tag);
	ArrayList<Tag> getAll();
	int deleteAll();
}
