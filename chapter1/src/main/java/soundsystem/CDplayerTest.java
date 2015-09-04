package soundsystem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // crea un application context cuando el test inicia
@ContextConfiguration(classes = CDPlayerConfig.class) //especifica donde buscar la configuración
public class CDplayerTest {

	@Autowired
	private CompactDisc cd;
	
	@Test
	public void cd_is_not_null() {
		Assert.assertNotNull(cd);
	}
	
	
	
}
