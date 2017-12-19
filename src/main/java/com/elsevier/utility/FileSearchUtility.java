package com.elsevier.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * The Class has the utility methods to pattern match a user input string in all the user input directories and its sub-directories..
 */
public class FileSearchUtility  {

	private static Logger LOGGER = Logger.getLogger(FileSearchUtility.class.getName());
	
	public static final Integer THREAD_POOL_SIZE=5;
	
	/**
	 * Generate Regular expression based on search words provided.
	 *
	 * @param textToMatch
	 * @return Regular expression as String
	 */
	public static StringBuffer generateSearchPattern(List<String> searchList) {
		LOGGER.info("generateSearchPattern Method : Begins()");
		StringBuffer searchPattern = new StringBuffer();
		searchPattern.append("^");
		for (int counter = 0; counter < searchList.size(); counter++) {
			searchPattern.append("(?=.*?\\b");
			searchPattern.append(searchList.get(counter));
			searchPattern.append("\\b)");
		}
		searchPattern.append(".*");
		LOGGER.info("generateSearchPattern Method : End()");
		return searchPattern;
	}

	

	/**
	 * List files.
	 *
	 * @param dir
	 * @return List of files present in directory/subdirectory
	 */
	public static List<File> listFiles(File dir) {
		return (List<File>) FileUtils.listFiles(dir, null, true);//Finds files within a given directory (and optionally its subdirectories) which match an array of extensions.
																//Parameters:directory the directory to search inextensions an array of extensions, ex. {"java","xml"}. 
																//If this parameter is null, all files are returned.recursive if true all subdirectories are searched as wellReturns:an collection of java.io.File with the matching files
	}

	/**
	 * Searches the files in filelist which matches the list of words.
	 *
	 * @param fileList 			list of files to be searched for the input search string.
	 * @param searchPattern		input search string.
	 * @return 					List of files containing the input search string.
	 */
	public static List<String> getFilesResult(List<File> fileList, String searchPattern) {
		LOGGER.info("getFilesResult Method : Begins()");
		List<String> fileListStr = new ArrayList<String>();
		Future<Boolean> searchStatus;
		for (File currentFile : fileList) {
			try {
				
				String content = FileUtils.readFileToString(currentFile);
				 ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
				    Callable<Boolean> callable = new Callable<Boolean>() {
				        @Override
				        public Boolean call() {
				        	boolean foundMatch=false;
							LOGGER.info("searchPatternInFile Method : Begins()");
							Pattern sourcePattern = Pattern.compile(searchPattern);
							Matcher patternMatcher = sourcePattern.matcher(content);
							 foundMatch = patternMatcher.matches();
							LOGGER.info("searchPatternInFile Method : End()");
							
							return foundMatch;
				        }
				    };
				    Future<Boolean> future = executor.submit(callable);
				 if (future.get()) {
						fileListStr.add(currentFile.getAbsolutePath());
					}
			} catch (Exception ioException) {
				LOGGER.error("Error processing the file content::", ioException);
			}
		
		}
		LOGGER.info("getFilesResult Method : End()");
		return fileListStr;
	}

	
}
