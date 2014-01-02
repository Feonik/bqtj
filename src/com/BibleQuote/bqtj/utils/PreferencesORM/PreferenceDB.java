package com.BibleQuote.bqtj.utils.PreferencesORM;

import com.BibleQuote.bqtj.utils.DataConstants;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Nikita K.
 * Date: 29.12.13
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
public class PreferenceDB {

	private Dao<PrefString, String> DaoPrefString;
	private Dao<PrefInteger, String> DaoPrefInteger;
	private Dao<PrefBoolean, String> DaoPrefBoolean;


	public PreferenceDB() {
		try {

			// TODO проверить автосоздание базы
			// (?) если базы нет, то она будет создана автоматически,
			// (?) иначе будет создано подключение к существующей базе
			ConnectionSource connectionSource =
					new JdbcConnectionSource(DataConstants.DB_PREFERENCES_URL);

			try {
				DaoPrefString =
						DaoManager.createDao(connectionSource, PrefString.class);
				DaoPrefInteger =
						DaoManager.createDao(connectionSource, PrefInteger.class);
				DaoPrefBoolean =
						DaoManager.createDao(connectionSource, PrefBoolean.class);

				TableUtils.createTableIfNotExists(connectionSource, PrefString.class);
				TableUtils.createTableIfNotExists(connectionSource, PrefInteger.class);
				TableUtils.createTableIfNotExists(connectionSource, PrefBoolean.class);

			} finally {
				connectionSource.close();
			}

		} catch (SQLException e) {
			// TODO обработка SQLException
			e.printStackTrace();
		}
	}


	private <D extends PrefT<T>, T> T getValue(
			Dao<D, String> dao, String key, T defaultValue) {

		PrefT<T> prefT = null;

		try {
			prefT = dao.queryForId(key);
		} catch (SQLException e) {
			// TODO обработка SQLException
			e.printStackTrace();
		}

		return prefT == null ? defaultValue : prefT.getValue();
	}


	private <D extends PrefT> void setValue(Dao<D, String> dao, D prefT) {

		try {
			dao.createOrUpdate(prefT);

		} catch (SQLException e) {
			// TODO обработка SQLException
			e.printStackTrace();
		}

	}


	public String getString(String key, String defaultValue) {
		return getValue(DaoPrefString, key, defaultValue);
	}

	public void setString(String key, String value) {
		setValue(DaoPrefString, new PrefString(key, value));
	}

	public Integer getInteger(String key, Integer defaultValue) {
		return getValue(DaoPrefInteger, key, defaultValue);
	}

	public void setInteger(String key, Integer value) {
		setValue(DaoPrefInteger, new PrefInteger(key, value));
	}

	public Boolean getBoolean(String key, Boolean defaultValue) {
		return getValue(DaoPrefBoolean, key, defaultValue);
	}

	public void setBoolean(String key, Boolean value) {
		setValue(DaoPrefBoolean, new PrefBoolean(key, value));
	}

}
