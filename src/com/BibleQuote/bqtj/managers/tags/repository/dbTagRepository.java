//+package com.BibleQuote.bqtj.managers.tags.repository;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import com.BibleQuote.bqtj.utils.Log;
//import com.BibleQuote.bqtj.dal.dbLibraryHelper;
//import com.BibleQuote.bqtj.managers.bookmarks.repository.dbBookmarksTagsRepository;
//import com.BibleQuote.bqtj.managers.tags.Tag;
//import com.BibleQuote.bqtj.CoreContext;
//
//import java.util.ArrayList;
//
//public class dbTagRepository implements ITagRepository {
//	private final static String TAG = dbTagRepository.class.getSimpleName();
//	private dbBookmarksTagsRepository bmTagRepo = new dbBookmarksTagsRepository();
//
//	@Override
//	public long add(String tag) {
//		Log.w(TAG, String.format("Add tag %s", tag));
//		SQLiteDatabase db = dbLibraryHelper.getConnectionDB();
//		db.delete(CoreContext.TAGS_TABLE, dbLibraryHelper.TAGS_NAME + "=\"" + tag + "\"", null);
//		long id = addRow(db, tag);
//		db.close();
//		return id;
//	}
//
//	@Override
//	public int update(Tag tag) {
//		Log.w(TAG, String.format("Update tag %s", tag.name));
//		SQLiteDatabase db = dbLibraryHelper.getConnectionDB();
//		ContentValues values = new ContentValues();
//		values.put(dbLibraryHelper.TAGS_NAME, tag.name);
//		return db.update(CoreContext.TAGS_TABLE, values, dbLibraryHelper.TAGS_KEY_ID + "=\"" + tag.id + "\"", null);
//	}
//
//	@Override
//	public int delete(Tag tag) {
//		Log.w(TAG, String.format("Delete tag %s", tag.name));
//		SQLiteDatabase db = dbLibraryHelper.getConnectionDB();
//		bmTagRepo.deleteTag(db, tag);
//		return db.delete(CoreContext.TAGS_TABLE, dbLibraryHelper.TAGS_KEY_ID + "=\"" + tag.id + "\"", null);
//	}
//
//	@Override
//	public ArrayList<Tag> getAll() {
//		SQLiteDatabase db = dbLibraryHelper.getConnectionDB();
//		ArrayList<Tag> result = getAllRowsToArray(db);
//		return result;
//	}
//
//	@Override
//	public int deleteAll() {
//		SQLiteDatabase db = dbLibraryHelper.getConnectionDB();
//		return db.delete(CoreContext.TAGS_TABLE, null, null);
//	}
//
//	private ArrayList<Tag> getAllRowsToArray(SQLiteDatabase db) {
//		ArrayList<Tag> result = new ArrayList<Tag>();
//		Cursor allRows = db.query(true, CoreContext.TAGS_TABLE,
//				null, null, null, null, null, dbLibraryHelper.TAGS_NAME, null);
//		if (allRows.moveToFirst()) {
//			do {
//				result.add(new Tag(
//						allRows.getInt(allRows.getColumnIndex(dbLibraryHelper.TAGS_KEY_ID)),
//						allRows.getString(allRows.getColumnIndex(dbLibraryHelper.TAGS_NAME))
//				)
//				);
//			} while (allRows.moveToNext());
//		}
//		return result;
//	}
//
//	private long addRow(SQLiteDatabase db, String tag) {
//		ContentValues values = new ContentValues();
//		values.put(dbLibraryHelper.TAGS_NAME, tag.trim());
//		return db.insert(CoreContext.TAGS_TABLE, null, values);
//	}
//
//}
