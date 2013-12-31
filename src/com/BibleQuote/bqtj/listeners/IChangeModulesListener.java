package com.BibleQuote.bqtj.listeners;

public interface IChangeModulesListener {
	public enum ChangeCode {
		ModulesLoaded,
		ModulesAdded,
		ModulesChanged,
		ModulesDeleted
	}

	public void onChangeModules(ChangeModulesEvent event);
}
