package com.BibleQuote.bqtj.listeners;

public interface IChangeChaptersListener {
	public enum ChangeCode {
		ChapterAdded
	}

	public void onChangeChapters(ChangeChaptersEvent event);
}
