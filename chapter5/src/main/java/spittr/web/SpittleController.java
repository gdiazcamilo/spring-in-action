package spittr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping(value = { "/spittles" })
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private SpittleRepository spittleRepository;

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

    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String showSpittle(@PathVariable long spittleId, Model model) {
        Spittle spittle = spittleRepository.findById(spittleId);

        if(spittle == null) {
            throw new SpittleNotFoundException();
        }

        model.addAttribute("spittle", spittle);
        return "spittle";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSpittle(SpittleForm spittleForm, Model model) {
        spittleRepository.save(spittleForm.getMessage(),
                                spittleForm.getLatitude(),
                                spittleForm.getLongitude());

        return "redirect:/spittles";
    }

/*    @ExceptionHandler(DuplicateSpittleException.class)
    private String handDuplicateSpittleException() {
        return "error/duplicate";
    }*/
}
