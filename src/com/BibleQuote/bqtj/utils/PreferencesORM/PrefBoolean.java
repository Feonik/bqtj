package com.BibleQuote.bqtj.utils.PreferencesORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created with IntelliJ IDEA.
 * User: Nikita K.
 * Date: 02.01.14
 * Time: 4:23
 * To change this template use File | Settings | File Templates.
 */

@DatabaseTable(tableName = "PrefBoolean")
class PrefBoolean extends PrefT<Boolean> {

	@DatabaseField(id = true)
	private String key;

	@DatabaseField
	private Boolean value;

	// Constructor without arguments is needed for ORMLite, not private
	protected PrefBoolean() {
	}

	public PrefBoolean(String key, Boolean value) {
		this.key = key;
		this.value = value;
	}

	public Boolean getValue() {
		return value;
	}

}
