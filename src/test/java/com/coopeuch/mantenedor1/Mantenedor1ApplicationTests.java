package com.coopeuch.mantenedor1;

import com.coopeuch.mantenedor1.controller.TaskController;
import com.coopeuch.mantenedor1.repository.TaskRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

@SpringBootTest
@AutoConfigureMockMvc
class Mantenedor1ApplicationTests {
/*
	@MockBean
	private TaskRepository taskRepository;

	@Autowired
	TaskController taskController;*/

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void postRequestTask() throws Exception {
		MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
		String task = "{\"descripcion\": \"Insercion de prueba\", \"fechaCreacion\" : \"2021-05-11T04:30:00\", \"vigente\" : true}";
		mockMvc.perform(MockMvcRequestBuilders.post("/task/add")
				.content(task)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.contentType(textPlainUtf8));
	}

}
