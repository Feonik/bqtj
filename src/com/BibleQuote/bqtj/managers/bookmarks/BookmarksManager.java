package com.BibleQuote.bqtj.managers.bookmarks;

import com.BibleQuote.bqtj.entity.BibleReference;
import com.BibleQuote.bqtj.managers.bookmarks.repository.IBookmarksRepository;
import com.BibleQuote.bqtj.managers.bookmarks.repository.dbBookmarksTagsRepository;
import com.BibleQuote.bqtj.managers.tags.Tag;
import com.BibleQuote.bqtj.managers.tags.repository.ITagRepository;
//import com.BibleQuote.bqtj.managers.tags.repository.dbTagRepository;

import java.util.ArrayList;

public class BookmarksManager {

	private IBookmarksRepository bmRepo;
//	private ITagRepository tagRepo = new dbTagRepository();
	public static final String TAGS_DELIMETER = ",";

	public BookmarksManager(IBookmarksRepository repository) {
		this.bmRepo = repository;
	}

	public void add(BibleReference ref) {
		add(ref.getPath(), ref.toString());
	}

	public long add(String OSISLink, String link) {
		return bmRepo.add(new Bookmark(OSISLink, link));
	}

	public void add(BibleReference ref, String tags) {
		add(ref.getPath(), ref.toString(), tags);
	}

	public void add(String OSISLink, String link, String tags) {
		long bmID = add(OSISLink, link);
		ArrayList<Long> tagIDs = getTagsIDs(tags);
		new dbBookmarksTagsRepository().add(bmID, tagIDs);
	}

	public void delete(Bookmark bookmark) {
		bmRepo.delete(bookmark);
	}

	public ArrayList<Bookmark> getAll() {
		return bmRepo.getAll();
	}

	public ArrayList<Bookmark> getAll(Tag tag) {
		return bmRepo.getAll(tag);
	}

	public void sort() {
		bmRepo.sort();
	}

	public void deleteAll() {
		bmRepo.deleteAll();
	}

	private ArrayList<Long> getTagsIDs(String tags) {
		ArrayList<Long> result = new ArrayList<Long>();
		for (String tag : tags.split(TAGS_DELIMETER)) {
//			result.add(tagRepo.add(tag));
		}
		return result;
	}
}