package spitter.controllers.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import spitter.web.SpittleController;
import spittr.Spittle;
import spittr.data.SpittleRepository;

public class SpittleControllerTest {

	@Test
	public void shouldShowRecentSpittles() throws Exception{
		SpittleRepository spittleRepository = Mockito.mock(SpittleRepository.class);
		List<Spittle> spittles = createSpittleList(20);
		
		Mockito.when(spittleRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(spittles);
		
		SpittleController spitterController = new SpittleController(spittleRepository);
		MockMvc mockSpitterController = MockMvcBuilders.standaloneSetup(spitterController)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
				.build();
		
		mockSpitterController.perform(MockMvcRequestBuilders.get("/spittles"))
		.andExpect(MockMvcResultMatchers.view().name("spittles"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
		.andExpect(MockMvcResultMatchers.model().attribute("spittleList", 
				org.hamcrest.Matchers.hasItems(spittles.toArray())));
	}
	
	@Test
	public void shouldShowRecentSpittlesForInferredMethod() throws Exception{
		SpittleRepository spittleRepository = Mockito.mock(SpittleRepository.class);
		List<Spittle> spittles = createSpittleList(20);
		
		Mockito.when(spittleRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(spittles);
		
		SpittleController spitterController = new SpittleController(spittleRepository);
		MockMvc mockSpitterController = MockMvcBuilders.standaloneSetup(spitterController)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittlesList.jsp"))
				.build();
		
		mockSpitterController.perform(MockMvcRequestBuilders.get("/spittles/spittlesList"))
		.andExpect(MockMvcResultMatchers.view().name("spittles/spittlesList"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
		.andExpect(MockMvcResultMatchers.model().attribute("spittleList", 
				org.hamcrest.Matchers.hasItems(spittles.toArray())));
	}
	
	@Test
	public void shouldShowPagedSpittles() throws Exception{
		List<Spittle> spittles = createSpittleList(50);
		SpittleRepository mockSpittleRepository = Mockito.mock(SpittleRepository.class);
		Mockito.when(mockSpittleRepository.findSpittles(238900, 50)).thenReturn(spittles);
		
		SpittleController spittleController = new SpittleController(mockSpittleRepository);
		MockMvc mockSpittleController = MockMvcBuilders.standaloneSetup(spittleController)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
				.build();
		
		mockSpittleController.perform(MockMvcRequestBuilders.get("/spittles?max=238900&count=50"))
		.andExpect(MockMvcResultMatchers.view().name("spittles"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
		.andExpect(MockMvcResultMatchers.model().attribute("spittleList", Matchers.hasItem(spittles.toArray())));
	}

	private List<Spittle> createSpittleList(int spittlesNumber) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < spittlesNumber; i++) {
			spittles.add(new Spittle(new Date(), "Spittle " + i));
		}
		
		return spittles;
	}
}
