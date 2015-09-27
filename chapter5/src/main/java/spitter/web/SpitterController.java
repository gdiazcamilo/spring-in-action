package spitter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.Spitter;
import spittr.data.SpitterRepository;

/**
 * Created by elgut on 26/09/2015.
 */
@Controller
@RequestMapping(value = "spitter")
public class SpitterController {
    SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spittlerRepository) {
        this.spitterRepository = spittlerRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(Spitter spitter){
        spitterRepository.save(spitter);

        return "redirect:/spitter/" + spitter.getUserName();
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public String showUserProfile(@PathVariable String userName, Model model) {
        Spitter spitter = spitterRepository.findByUsername(userName);
        model.addAttribute(spitter);
        return "profile";
    }


}
