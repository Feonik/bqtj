//+/*
//* Copyright (C) 2011 Scripture Software (http://scripturesoftware.org/)
//*
//* Licensed under the Apache License, Version 2.0 (the "License");
//* you may not use this file except in compliance with the License.
//* You may obtain a copy of the License at
//*
//*       http://www.apache.org/licenses/LICENSE-2.0
//*
//* Unless required by applicable law or agreed to in writing, software
//* distributed under the License is distributed on an "AS IS" BASIS,
//* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//* See the License for the specific language governing permissions and
//* limitations under the License.
//*/
//
//package com.BibleQuote.bqtj.dal;
//
//import android.database.sqlite.SQLiteDatabase;
//import com.BibleQuote.bqtj.CoreContext;
//
//import java.io.File;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
///**
//* User: Vladimir Yakushev
//* Date: 02.05.13
//*/
//public class dbLibraryHelper {
//	private final static String TAG = dbLibraryHelper.class.getSimpleName();
//
//	private static int version = 1;
//
//	public static final String BOOKMARKS_KEY_ID = "_id";
//	public static final String BOOKMARKS_OSIS = "osis";
//	public static final String BOOKMARKS_LINK = "link";
//	public static final String BOOKMARKS_DATE = "date";
//
//	public static final String BOOKMARKS_TAGS_KEY_ID = "_id";
//	public static final String BOOKMARKS_TAGS_BM_ID = "bm_id";
//	public static final String BOOKMARKS_TAGS_TAG_ID = "tag_id";
//
//	public static final String TAGS_KEY_ID = "_id";
//	public static final String TAGS_NAME = "name";
//
//	private static final String[] CREATE_DATABASE = new String[] {
//			"create table " + CoreContext.BOOKMARKS_TABLE + " ("
//					+ BOOKMARKS_KEY_ID + " integer primary key autoincrement, "
//					+ BOOKMARKS_OSIS + " text unique not null, "
//					+ BOOKMARKS_LINK + " text not null, "
//					+ BOOKMARKS_DATE + " text not null"
//				+ ");",
//			"create table " + CoreContext.BOOKMARKS_TAGS_TABLE + " ("
//					+ BOOKMARKS_TAGS_KEY_ID + " integer primary key autoincrement, "
//					+ BOOKMARKS_TAGS_BM_ID + " integer not null, "
//					+ BOOKMARKS_TAGS_TAG_ID + " integer not null"
//				+ ");",
//			"create table " + CoreContext.TAGS_TABLE + " ("
//					+ TAGS_KEY_ID + " integer primary key autoincrement, "
//					+ TAGS_NAME + " text unique not null"
//				+ ");"
//	};
//
//
////	public static SQLiteDatabase getConnectionDB() {
//	public static Connection getConnectionDB() {
//
//		File dbDir = new File(CoreContext.DB_DATA_PATH);
//		if (!dbDir.exists()) dbDir.mkdir();
//
//
//		Connection connection = null;
//		try {
//			// если базы нет, то она будет создана автоматически,
//			// иначе будет создано подключение к существующей базе
//			connection = DriverManager.getConnection(
//					"jdbc:hsqldb:file:" + dbDir.getPath() + File.separator
//							+ CoreContext.DB_LIBRARY_FILE_NAME, "SA", "");
//
//		} catch (SQLException e) {
//			// TODO change to log
//			System.err.println("ERROR: failed to create connection with db");
//			e.printStackTrace();
//			// TODO change System.exit(1)
//			System.exit(1);
//		}
//
//
//
//		// TODO version db
////		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
////				new File(dbDir, CoreContext.DB_LIBRARY_FILE_NAME), null);
////
////		if (db.getVersion() != version) {
////			db.beginTransaction();
////			try {
////				int currVersion = db.getVersion();
////				if (currVersion == 0) {
////					onCreate(db);
////				};
////				onUpgrade(db, currVersion);
////				db.setVersion(version);
////				db.setTransactionSuccessful();
////			} finally {
////				db.endTransaction();
////			}
////		}
//
//		return connection;
//	}
//
//	public static SQLiteDatabase openDB() {
//		SQLiteDatabase db = getConnectionDB();
//		db.beginTransaction();
//		return db;
//	}
//
//	public static void closeDB(SQLiteDatabase db) {
//		db.setTransactionSuccessful();
//		db.endTransaction();
//		db.close();
//	}
//
//	// TODO onCreate(db)
//	private static void onCreate(SQLiteDatabase db) {
//		for (String command : CREATE_DATABASE) {
//			db.execSQL(command);
//		}
//	}
//
//	private static void onUpgrade(SQLiteDatabase db, int currVersion) {
//		//TODO Create dbLibraryHelper.onUpgrade()
//	}
//
//	static {
//		try {
//			Class.forName("org.hsqldb.jdbc.JDBCDriver" );
//		} catch (Exception e) {
//			// TODO change to log
//			System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
//			e.printStackTrace();
////			return;
//
//			// TODO change System.exit(1)
//			System.exit(1);
//		}
//	}
//
//}
//
