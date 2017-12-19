package com.elsevier.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elsevier.bean.ErrorResponse;
import com.elsevier.bean.FileSearchCriteria;
import com.elsevier.exception.FileSearchException;
import com.elsevier.service.FileSearchService;

/**
 * This class provides the service to search for a pattern string within the directory and its sub-directories.
 * @author Neeraj
 *
 */
@RestController
public class FileSearchController {

	private static Logger LOGGER = Logger.getLogger(FileSearchController.class.getName());
	@Autowired
	private FileSearchService fileSearchService;

	
	/**
	 * This method will return the list of files containing the user input string list as part of the search criteria
	 * @param fileSearchCriteria	holds the directory to be searched and the search string.
	 * @return						returns the list of files matching the search criteria.
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/fs")
	public synchronized ResponseEntity<List<String>> getFileListByRequest(@RequestBody FileSearchCriteria fileSearchCriteria)
			throws Exception {

		LOGGER.info("RequestMappingHandlerMapping :getFileListByRequest() :Begins()");
		List<String> fileList = new ArrayList<String>();
		boolean noResultFound=false;
		try {
			String searchDirectory = fileSearchCriteria.getDirectoryName();
			List<String> searchPatternList = fileSearchCriteria.getSearchPattern();
			fileList = fileSearchService.getFileList(searchDirectory, searchPatternList);
			if (CollectionUtils.isEmpty(fileList)) {
				noResultFound=true;
			}
		} catch (Exception genExp) {
			LOGGER.error("Exception during file search::", genExp);
			throw new Exception();
		}
		
			
		if(noResultFound) {
			throw new FileSearchException("No Result Found");
		}

		LOGGER.info("RequestMappingHandlerMapping :getFileListByRequest() :Ends()");

		return new ResponseEntity<List<String>>(fileList, HttpStatus.OK);
	}

}
