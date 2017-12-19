package com.elsevier.bean;

import java.util.List;

/**
 * Description : FileSearchCriteria bean containing directory Name and search
 * pattern as input parameter.
 *
 */
public class FileSearchCriteria {

	private String directoryName;

	private List<String> searchPattern;

	/**
	 * Gets the directory name.
	 *
	 * @return the directory name
	 */
	public String getDirectoryName() {
		return directoryName;
	}

	/**
	 * Sets the directory name.
	 *
	 * @param directoryName
	 *            the new directory name
	 */
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	/**
	 * Gets the search pattern.
	 *
	 * @return the search pattern
	 */
	public List<String> getSearchPattern() {
		return searchPattern;
	}

	/**
	 * Sets the search pattern.
	 *
	 * @param searchPattern
	 *            the new search pattern
	 */
	public void setSearchPattern(List<String> searchPattern) {
		this.searchPattern = searchPattern;
	}

}
