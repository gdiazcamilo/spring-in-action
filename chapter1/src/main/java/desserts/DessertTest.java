package desserts;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DessertConfig.class})
public class DessertTest {

	@Autowired
	private Order order;
	
	@Autowired
	@Cold
	private Dessert iceCream;
	
	@Autowired
	@Qualifier("cookies")
	private Dessert cookies;
	
	@Test
	public void order_is_not_null() {
		Assert.assertNotNull(order);
	}
	
	@Test
	public void iceCream_is_iceCream_type() {
		Assert.assertThat(iceCream, IsInstanceOf.instanceOf(IceCream.class));
	}
	
	@Test
	public void cookies_is_cookies_type(){
		Assert.assertThat(cookies, IsInstanceOf.instanceOf(Cookies.class));
	}
	
	@Test
	public void dessert_from_orden_is_iceCream_type() {
		Assert.assertThat(order.getDessert(), IsInstanceOf.instanceOf(Cookies.class));
	}
}
