package spitter.controllers.test;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import spittr.web.HomeController;

public class HomeControllerTest {

	@Test
	public void canAccessHomePageFromDefaultUri() throws Exception {
		HomeController homeController = new HomeController();
		MockMvc mockHomeController = MockMvcBuilders.standaloneSetup(homeController).build();
		
		mockHomeController.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.view().name("home"));
	}
	
	@Test
	public void canAccessHomePageFromHomePageUri() throws Exception{
		HomeController homeController = new HomeController();
		MockMvc mockHomeController = MockMvcBuilders.standaloneSetup(homeController).build();
		
		mockHomeController.perform(MockMvcRequestBuilders.get("/homepage"))
		.andExpect(MockMvcResultMatchers.view().name("home"));
	}
}
