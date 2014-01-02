package com.BibleQuote.bqtj.utils.PreferencesORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created with IntelliJ IDEA.
 * User: Nikita K.
 * Date: 02.01.14
 * Time: 4:22
 * To change this template use File | Settings | File Templates.
 */

@DatabaseTable(tableName = "preferencesinteger")
class PrefInteger extends PrefT<Integer> {

	@DatabaseField(id = true)
	private String key;

	@DatabaseField
	private Integer value;

	// Constructor without arguments is needed for ORMLite, not private
	protected PrefInteger() {
	}

	public PrefInteger(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

}
