package com.elsevier.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.elsevier.bean.FileSearchCriteria;
import com.elsevier.main.FileSearchMainApp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FileSearchMainApp.class)
@SpringBootTest
public class FileSearchControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	FileSearchCriteria fileSearchCriteria = new FileSearchCriteria();
	List<String> searchList = new ArrayList<String>();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void testControllerNoContentException() throws Exception {
		searchList.add("Quick");
		searchList.add("Styes");
		fileSearchCriteria.setSearchPattern(searchList);
		fileSearchCriteria.setDirectoryName("E:\\Search1");
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(fileSearchCriteria);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/fs").contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString);
		this.mockMvc.perform(builder).andExpect(
				MockMvcResultMatchers.content().json("{\"errorCode\":204}"));
	}

	@Test
	public void testControllerGeneralException() throws Exception {
		searchList = null;
		fileSearchCriteria.setSearchPattern(searchList);
		fileSearchCriteria.setDirectoryName("E:\\Search1");
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(fileSearchCriteria);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/fs").contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString);
		this.mockMvc.perform(builder).andExpect(
				MockMvcResultMatchers.content().json("{\"errorCode\":500}"));
	}
	
}
