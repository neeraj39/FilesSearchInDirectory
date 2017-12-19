package com.elsevier.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.elsevier.utility.FileSearchUtility;


@Service
public interface FileSearchService {

	public List<String> getFileList(String directoryName, List<String> searchStringList);
		
	

}
