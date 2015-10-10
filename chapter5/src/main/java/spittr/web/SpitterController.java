package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.Spitter;
import spittr.data.SpitterRepository;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

/**
 * Created by elgut on 26/09/2015.
 */
@Controller
@RequestMapping(value = "/spitter")
public class SpitterController {
    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("spitter", new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(
            @RequestParam MultipartFile profilePicture,
            @Valid Spitter spitter,
            Errors errors){

        if(errors.hasErrors()) {
            return "registerForm";
        }

        try {
            profilePicture.transferTo(new File("/data/spittr/" + profilePicture.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        spitterRepository.save(spitter);

        //This way is insecure:
        return "redirect:/spitter/" + spitter.getUserName();
    }

    @RequestMapping(value = "/registerNoPic", method = RequestMethod.POST)
    public String processRegistrationWithoutPicture(@Valid Spitter spitter,
                                                    Errors errors,
                                                    RedirectAttributes model) {
        if(errors.hasErrors()) {
            return "registerForm";
        }

        spitterRepository.save(spitter);

        //This is recomended way (more secure)
        model.addAttribute("id", spitter.getFirstName());
        model.addAttribute("username", spitter.getUserName());
        model.addFlashAttribute(spitter);
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public String showUserProfile(@PathVariable String userName,
                                  @RequestParam(value = "id", required = false) String id,
                                  Model model) {

        if(!model.containsAttribute("spitter")) {
            Spitter spitter = spitterRepository.findByUsername(userName);
            model.addAttribute(spitter);
        }

        return "profile";
    }


}
