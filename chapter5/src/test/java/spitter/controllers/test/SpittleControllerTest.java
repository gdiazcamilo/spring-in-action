package spitter.controllers.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.data.FakeSpittleRepository;
import spittr.web.DuplicateSpittleException;
import spittr.web.SpittleController;
import spittr.Spittle;
import spittr.data.SpittleRepository;
import spittr.web.SpittleNotFoundException;


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
    public void showSpittleById() throws Exception{
        SpittleRepository spittleRepository = Mockito.mock(SpittleRepository.class);
        Spittle spittle = new Spittle(new Date(), "Spittle 123");
        Mockito.when(spittleRepository.findById(123L)).thenReturn(spittle);

        SpittleController spittleController = new SpittleController(spittleRepository);
        MockMvc mockSpittleController = MockMvcBuilders.standaloneSetup(spittleController).build();
        mockSpittleController.perform(MockMvcRequestBuilders.get("/spittles/123"))
                .andExpect(MockMvcResultMatchers.view().name("spittle"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittle"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittle", spittle));
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
        .andExpect(MockMvcResultMatchers.model().attribute("spittleList", Matchers.hasItems(spittles.toArray())));
    }

    private List<Spittle> createSpittleList(int spittlesNumber) {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i = 0; i < spittlesNumber; i++) {
            spittles.add(new Spittle(new Date(), "Spittle " + i));
        }

        return spittles;
    }

    @Test
    public void whenSpittleNotFoundShouldThrowException() throws Exception {
        SpittleRepository spittleRepository = Mockito.mock(SpittleRepository.class);
        Mockito.when(spittleRepository.findById(0L)).thenReturn(null);

        SpittleController spittleController = new SpittleController(spittleRepository);
        MockMvc mockSpittleController = MockMvcBuilders.standaloneSetup(spittleController).build();

        mockSpittleController.perform(MockMvcRequestBuilders.get("/spittles/0"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void ifSaveDuplicateMessageThrowException() throws Exception {
        FakeSpittleRepository fakeSpittleRepository = new FakeSpittleRepository();
        SpittleController spittleController = new SpittleController(fakeSpittleRepository);

        MockMvc mockSpittleController = MockMvcBuilders.standaloneSetup(spittleController)
                .setHandlerExceptionResolvers(getSimpleMappingExceptionResolver())
                .build();

        mockSpittleController.perform(
                MockMvcRequestBuilders.post("/spittles/save")
                .param("message", "primer spittle")
                .param("longitude", "10")
                .param("latitude", "10")).andExpect(MockMvcResultMatchers.view().name("error/duplicate"));

    }

    private SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties mapping = new Properties();
        mapping.put(DuplicateSpittleException.class.getName(), "error/duplicate");
        simpleMappingExceptionResolver.setExceptionMappings(mapping);

        return simpleMappingExceptionResolver;
    }

    @Test
    public void ifSavedRedirectToSpittles() throws Exception {
        FakeSpittleRepository fakeSpittleRepository = new FakeSpittleRepository();
        SpittleController spittleController = new SpittleController(fakeSpittleRepository);

        MockMvc mockSpittleController = MockMvcBuilders.standaloneSetup(spittleController).build();
        mockSpittleController.perform(
                MockMvcRequestBuilders.post("/spittles/save")
                        .param("message", "segundo spittle")
                        .param("longitude", "10")
                        .param("latitude", "10")).andExpect(MockMvcResultMatchers.redirectedUrl("/spittles"));
    }

}
