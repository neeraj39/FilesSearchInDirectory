File Search Service Application

Description: It provides the service to search for a user input list of search strings within the directory and its sub-directories
 and return the list of files containing the input search string.
 
1) Dependencies:

Eclipse
Spring Boot
Maven
Apache Commons

2) Project Structure:
	a) Controller: FileSearchController.java
		provides the service to search for a pattern string within the directory and its sub-directories.
	b) Service: FileSearchService.java
		Holds the logic to return list of files in the directories that contains the search pattern.
	c) Utility: FileSearchUtility.java
		It has the utility methods to pattern match a user input string in all the user input directories and its sub-directories. The list of files in the 
		directories and sub-directories is retrieved using Apache commons APIs.

3) Steps to Run the Application:
	1.Run FileSearchServiceApp.java as Java Application
 	2.Open Advance Rest client from chrome store
	3.Select Request method as Post and Request URL as http://localhost:8080/fs
	4.Select body content type as application/json
	5.Enter the json request in the text box in the below format.
	Eg: {"directoryName":"E:\\Search1","searchPattern":["Quick", "Styles"]}
	