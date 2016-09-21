package be.vdab.spring.mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class FilmController {
    @Autowired
    FilmRepository fr;

    /*
    @Autowired
    ActorRepository ar;
    */

    /* Films hier */
    @RequestMapping("/film/list")
    public String ex(Model model) {
        model.addAttribute("filmList", fr.findAll());
        return "films";
    }

    @RequestMapping(value = "/addfilm", method = RequestMethod.GET)
    public String addFilm(Model model, @RequestParam(value = "filmId", required = false) Integer filmId) {
        if(filmId != null)
        {
            model.addAttribute("film", fr.findOne(filmId));
        }
        else
        {
            model.addAttribute("film", new Film());
        }
        return "addfilm";
    }

    @RequestMapping(value = "/addfilm", method = RequestMethod.POST)
    public String processForm(@Valid Film film, BindingResult br) {
        if(br.hasErrors()) {
            return "addfilm";
        } else {
            fr.save(film);
            return "redirect:/film/list";
        }
    }

    @RequestMapping("/removefilm")
    public String removeFilm(@RequestParam(value = "filmId", required = true) int filmId) {
        fr.delete(filmId);
        return "redirect:/film/list";
    }

    /** ===== Actors Hier ======
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

    ---- **/



}
