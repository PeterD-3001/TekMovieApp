package be.teknyske.tekmovieapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class ActorController {
    /*
    @Autowired
    FilmRepository fr;
    */

    @Autowired
    ActorRepository ar;

    /** ===== Actors Hier ====== **/
    @RequestMapping("/actor/list")
    public String ex2(Model model)
    {
        // System.out.println(ar.findAll());
        model.addAttribute("actorList", ar.findAll());
        return "actors";

    }

    @RequestMapping(value = "/addactor", method = RequestMethod.GET)
    public String addActor(Model model, @RequestParam(value = "actorId", required = false) Integer actorId) {
        if(actorId != null)
        {
            model.addAttribute("actor", ar.findOne(actorId));
        }
        else
        {
            model.addAttribute("actor", new Actor());
        }
        return "addactor";
    }

    @RequestMapping(value = "/addactor", method = RequestMethod.POST)
    public String processForm(@Valid Actor actor, BindingResult br) {
        if(br.hasErrors()) {
            return "addactor";
        }
        else
        {
            ar.save(actor);
            return "redirect:/actor/list";
        }
    }

    @RequestMapping("/removeactor")
    public String removeActor(@RequestParam(value = "actorId", required = true) int actorId) {
        ar.delete(actorId);
        return "redirect:/actor/list";
    }



}
