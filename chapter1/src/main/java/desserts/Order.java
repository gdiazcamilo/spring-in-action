package desserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Order {

	private Dessert dessert;

	public Dessert getDessert() {
		return dessert;
	}

	@Autowired
	@Qualifier("cookies")
	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}

}
