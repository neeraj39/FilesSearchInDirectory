package com.elsevier.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.elsevier.exception.FileSearchException;
import com.elsevier.service.FileSearchService;
import com.elsevier.service.implementation.FileSearchServiceImpl;

public class FileSearchServiceTest {

	private FileSearchService fileSearchServiceTest;
	List<String> searchList = new ArrayList<String>();

	@Before
	public void setUp()  {

		searchList.add("Quick");
		searchList.add("Styes");
		fileSearchServiceTest = new FileSearchServiceImpl();
	}

	@Test(expected = NullPointerException.class)
	public void testWithNullList()  {
		fileSearchServiceTest.getFileList(null, null);
	}

	@Test()
	public void testWithPositiveSearch()  {
		fileSearchServiceTest.getFileList("E:\\Search1", searchList);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithoutDirectory()  {
		fileSearchServiceTest.getFileList("E:\\NoDir", searchList);
	}

	@Test(expected = NullPointerException.class)
	public void testWithnullSearchPattern()  {
		fileSearchServiceTest.getFileList("E:\\Search1", null);
	}

	@Test(expected = NullPointerException.class)
	public void testWithnullDirectory()  {
		fileSearchServiceTest.getFileList(null, searchList);
	}
	
	@Test()
	public void testWithNoResult()  {
		searchList.add("abc");
		fileSearchServiceTest.getFileList("E:\\Search1", searchList);
	}

	@Test()
	public void testWithEmptyDirectory()  {
		fileSearchServiceTest.getFileList("E:\\empty", searchList);
	}

	@After
	public void tearDown()  {
		fileSearchServiceTest = null;
	}

}
