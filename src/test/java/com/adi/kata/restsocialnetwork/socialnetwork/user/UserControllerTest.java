package com.adi.kata.restsocialnetwork.socialnetwork.user;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	@Test
	public void verifyAllUsersList() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$",hasSize(3))).andDo(print());
	}
	
	@Test
	public void verifyUserById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.name").value("Alice"))
		.andDo(print());
	}
	
//	@Test
//	public void verifyPostsForUser() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/users/1/posts").accept(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$",hasSize(1)))
//		.andExpect(jsonPath("$[0].id").exists())
//		.andExpect(jsonPath("$[0].description").exists())
//		.andExpect(jsonPath("$[0].description").value("I love the weather today."))
//		.andDo(print());
//	}
	
	@Test
	public void verifyNullUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/0").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("User does not exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/a").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(400))
		.andExpect(jsonPath("$.message").value("Malformed request!"))
		.andDo(print());
	}
	
	@Test
	public void verifyDeleteUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/3").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("User has been deleted"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidDeleteUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/5").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("User does not exist"))
		.andDo(print());
	}
	
	@Test
	public void verifySaveUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/users/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Mark\"}"))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.id").value(3))
		.andExpect(jsonPath("$.name").value("Mark"))
		.andDo(print());
	}
	
	@Test
	public void verifyMalformedSaveUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/users/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content("{\"id\":\"6\",\"name\":\"Mark\"}"))
		.andExpect(jsonPath("$.errorCode").value(400))
		.andExpect(jsonPath("$.message").value("Malformed request!"))
		.andDo(print());
	}
	
	@Test
	public void verifySavePost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/users/2/posts/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content("{\"description\":\"Hi.. this is Mark.. My new post\",\"time\":\"2020-02-19T05:26:44.778+0000\"}"))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.description").exists())
		.andExpect(jsonPath("$.time").exists())
		.andExpect(jsonPath("$.description").value("Hi.. this is Mark.. My new post"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidUserForSavePost() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/users/6/posts/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content("{\"description\":\"You will not see this post\",\"time\":\"2020-02-19T05:26:44.778+0000\"}"))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("User does not exist"))
		.andDo(print());
	}
}
