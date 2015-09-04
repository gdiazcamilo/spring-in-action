package soundsystem;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // crea un application context cuando el test inicia
@ContextConfiguration(classes = CDPlayerConfig.class) //especifica donde buscar la configuración
public class CDplayerTest {

	@Rule
	public final SystemOutRule log = new SystemOutRule().enableLog();
	
	@Autowired(required = true)
	private CompactDisc cd;
	
	@Autowired
	private MediaPlayer player;
	
	@Test
	public void cd_is_not_null() {
		Assert.assertNotNull(cd);
	}
	
	@Test
	public void play() {
		player.play();
		Assert.assertEquals("Playing Porfiado by El Cuarteto de Nos", log.getLog().trim());
	}
	
	//@Test
	public void get_cd_instance_by_bean_name() {
		ApplicationContext appContext = new AnnotationConfigApplicationContext();
		CompactDisc cd = appContext.getBean("elCuartetoDeNos", CompactDisc.class);
		
		Assert.assertNotNull(cd);
	}
	
	
	
}
