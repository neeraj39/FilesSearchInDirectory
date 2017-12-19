package com.elsevier.service.implementation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elsevier.service.FileSearchService;
import com.elsevier.utility.FileSearchUtility;

/**
 * searches a configured directory and subdirectories for files containing a
 * list of words.
 *
 */

@Service
public class FileSearchServiceImpl implements FileSearchService {
	
		
	private static Logger LOGGER = Logger.getLogger(FileSearchServiceImpl.class.getName());

	/**
	 * This method returns the list of files in the directories that contains the search pattern.
	 *
	 * @param	directoryName	holds the value of the directory to be searched.
	 * @param	searchStringList	holds the value of the search String list.
	 * @return 					returns the list of files matching the search criteria.
	 */

	public List<String> getFileList(String directoryName, List<String> searchStringList) {
		LOGGER.info("getFileList() : Begins");

		List<String> fileListStr = new ArrayList<String>();
		List<File> fileList = FileSearchUtility.listFiles(new File(directoryName));
		StringBuffer searchPattern = FileSearchUtility.generateSearchPattern(searchStringList);
		fileListStr = FileSearchUtility.getFilesResult(fileList, searchPattern.toString());
		LOGGER.info("getFileList() : Ends");
		return fileListStr;
	
	}

}
