package spitter.controllers.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spittr.data.FakeSpitterRepository;
import spittr.web.SpitterController;
import spittr.Spitter;
import spittr.data.SpitterRepository;

/**
 * Created by elgut on 27/09/2015.
 */
public class SpitterControllerTest {

    @Test
    public void testShowRegisterForm() throws Exception {
        SpitterRepository spittleRepository = Mockito.mock(SpitterRepository.class);

        SpitterController spittlerController = new SpitterController(spittleRepository);
        MockMvc mockSpittleController = MockMvcBuilders.standaloneSetup(spittlerController).build();

        mockSpittleController.perform(MockMvcRequestBuilders.get("/spitter/register"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spitter"))
                .andExpect(MockMvcResultMatchers.view().name("registerForm"));

    }

    @Test
    public void shouldProcessRegistration() throws Exception {
        Spitter unsaved = new Spitter("Gustavo", "Diaz", "gdiaz", "gd123");
        Spitter saved = new Spitter(24L, "Gustavo", "Diaz", "gdiaz", "gd123");
        SpitterRepository spittlerRepository = Mockito.mock(SpitterRepository.class);
        Mockito.when(spittlerRepository.save(unsaved)).thenReturn(saved);

        SpitterController spittleController = new SpitterController(spittlerRepository);

        MockMvc mockSpittleController = MockMvcBuilders.standaloneSetup(spittleController).build();

        mockSpittleController.perform(MockMvcRequestBuilders.post("/spitter/registerNoPic")
                .param("firstName", "Gustavo")
                .param("lastName", "Diaz")
                .param("userName", "gdiaz")
                .param("password", "gd123"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/spitter/" + unsaved.getUserName() + "?id=" + unsaved.getFirstName()));

        // capture arguments pass to save call.
        ArgumentCaptor<Spitter> spitterArgumentCaptor = ArgumentCaptor.forClass(Spitter.class);
        Mockito.verify(spittlerRepository, Mockito.atLeastOnce()).save(spitterArgumentCaptor.capture());

        // assert save method was called using same arguments.
        Assert.assertThat(spitterArgumentCaptor.getValue(), org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs(unsaved));
    }

    @Test
    public void whenHasValidationFailsShouldReturnToRegisterForm() throws Exception {
        SpitterRepository spittlerRepository = Mockito.mock(SpitterRepository.class);
        SpitterController spittleController = new SpitterController(spittlerRepository);

        MockMvc mockSpittleController = MockMvcBuilders.standaloneSetup(spittleController).build();

        mockSpittleController.perform(MockMvcRequestBuilders.post("/spitter/registerNoPic")
                .param("userName", "gdiaz")
                .param("password", "gd123"))
                .andExpect(MockMvcResultMatchers.view().name("registerForm"));
    }

    @Test
    public void testShowProfilePage() throws Exception {

        Spitter spitterUser = new Spitter(1L, "name", "secondname", "gdiaz", "");
        SpitterRepository spitterRepository = Mockito.mock(SpitterRepository.class);
        Mockito.when(spitterRepository.findByUsername(spitterUser.getUserName())).thenReturn(spitterUser);
        SpitterController spitterController = new SpitterController(spitterRepository);

        MockMvc mockSpitterController = MockMvcBuilders.standaloneSetup(spitterController).build();

        mockSpitterController.perform(MockMvcRequestBuilders.get("/spitter/" + spitterUser.getUserName()))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spitter"))
                .andExpect(MockMvcResultMatchers.view().name("profile"));

        Mockito.verify(spitterRepository, Mockito.atLeastOnce()).findByUsername(spitterUser.getUserName());
    }

    @Test
    public void whenFlashAttributeIsPassedRepositoryIsNotCalled() throws Exception {
        SpitterRepository fakeSpitterRepository = Mockito.mock(SpitterRepository.class);
        Mockito.when(fakeSpitterRepository.findByUsername(Mockito.anyString())).thenReturn(new Spitter());
        SpitterController spitterController = new SpitterController(fakeSpitterRepository);

        MockMvc mockSpitterController = MockMvcBuilders.standaloneSetup(spitterController).build();

        mockSpitterController.perform(MockMvcRequestBuilders.get("/spitter/gdiaz")
                .flashAttr("spitter", new Spitter()))
            .andExpect(MockMvcResultMatchers.model().attributeExists("spitter"))
            .andExpect(MockMvcResultMatchers.view().name("profile"));

        Mockito.verify(fakeSpitterRepository, Mockito.never()).findByUsername(Mockito.anyString());
    }
}
