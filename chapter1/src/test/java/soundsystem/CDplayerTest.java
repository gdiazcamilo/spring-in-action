package soundsystem;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // crea un application context cuando el test inicia
@ContextConfiguration(classes = {CDPlayerConfig.class, CDConfig.class}) //especifica donde buscar la configuración
public class CDplayerTest {
	
	@Rule
	public final SystemOutRule log = new SystemOutRule().enableLog();
	
	@Autowired(required = true)
	private CompactDisc cd;
	
	@Autowired
	private CDPlayer player;
	
	@Autowired
	@Qualifier("dvd")
	private CompactDisc dvd;
	
	@Autowired
	private PropertyService propertySvc;
	
	@Test
	public void cd_is_not_null() {
		Assert.assertNotNull(cd);
	}
	
	@Test
	public void cd_bean_is_singleton() {
		Assert.assertEquals(cd, player.getCd());
	}
	
	@Test
	public void dvd_bean_is_prototype() {
		Assert.assertNotEquals(dvd, player.getDvd());
	}
	
	@Test
	public void primary_bean_is_el_cuarteto_de_nos() {
		player.play();
		Assert.assertEquals("Playing Porfiado by El Cuarteto de Nos", log.getLog().trim());
	}
	
	@Test
	public void property_disc_artists_existis() {
		Assert.assertNotNull(propertySvc.getProperty("disc.artist"));
	}
	
	@Test
	public void property_disc_artists_value_is_el_cuarteto_de_nos() {
		Assert.assertEquals(propertySvc.getProperty("disc.artist"), "El Cuarteto de Nos");
	}
	
	@Test(expected = IllegalStateException.class)
	public void required_property_doesnt_existis() {
		propertySvc.getRequiredProperty("none");
		
	}
	
}
