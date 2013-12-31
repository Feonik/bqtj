package com.BibleQuote.bqtj.controllers;

import com.BibleQuote.bqtj.dal.LibraryUnitOfWork;
import com.BibleQuote.bqtj.dal.repository.IModuleRepository;
import com.BibleQuote.bqtj.exceptions.OpenModuleException;
import com.BibleQuote.bqtj.modules.FsModule;
import com.BibleQuote.bqtj.modules.Module;

import java.util.Map;

public class FsModuleController implements IModuleController {
	//private final String TAG = "FsModuleController";

	private IModuleRepository<String, FsModule> mRepository;

	public FsModuleController(LibraryUnitOfWork unit) {
		mRepository = unit.getModuleRepository();
	}

	@Override
	public Map<String, Module> loadFileModules() {
		return mRepository.loadFileModules();
	}

	@Override
	public Map<String, Module> getModules() {
		Map<String, Module> result = mRepository.getModules();
		if (result.size() == 0) {
			return loadFileModules();
		} else {
			return result;
		}
	}

	@Override
	public Module getModuleByID(String moduleID) throws OpenModuleException {
		FsModule fsModule = mRepository.getModuleByID(moduleID);
		if (fsModule == null) {
			throw new OpenModuleException(moduleID, moduleID);
		}
		return fsModule;
	}
}
