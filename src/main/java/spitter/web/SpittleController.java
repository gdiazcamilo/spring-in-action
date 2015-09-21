package spitter.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping(value = { "/spittles" })
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";
	
	SpittleRepository spittleRepository;
	
	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles(
			@RequestParam(value = "max", 
						  defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count",
						  defaultValue = "20") int count) {
		return spittleRepository.findSpittles(max, count);
	}
	
	@RequestMapping(value = {"spittlesList"}, method = RequestMethod.GET)
	public List<Spittle> spittlesList() {
		return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
		//The view name is inferred from request mapping value.
		//The list of spittles is added to the model automatically.
		//The key of the added spittles list is inferred from the type, in this case: spittleList
	}
	
	
}