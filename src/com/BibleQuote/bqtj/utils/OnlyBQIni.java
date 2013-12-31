package com.BibleQuote.bqtj.utils;

import com.BibleQuote.bqtj.CoreContext;

import java.io.File;
import java.io.FileFilter;


public class OnlyBQIni implements FileFilter {
	private String filter;

	public OnlyBQIni() {
		this.filter = CoreContext.FS_MODULE_INI_FILE_NAME;
	}

	public OnlyBQIni(String filter) {
		this.filter = filter;
	}

	public boolean accept(File myFile) {
		return myFile.getName().toLowerCase().equals(this.filter)
				|| myFile.isDirectory();
	}

	@Override
	public String toString() {
		return this.filter;
	}
}