import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import spitter.web.HomeController;

public class HomeControllerTest {

	@Test
	public void testHomePage() throws Exception {
		HomeController homeController = new HomeController();
		MockMvc mockHomeController = MockMvcBuilders.standaloneSetup(homeController).build();
		
		mockHomeController.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.view().name("home"));
	}
	
}
