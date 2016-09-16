package be.vdab.spring.mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MyController {
    @Autowired
    FilmRepository fr;

    @RequestMapping("/")
    public String landing() {
        return "redirect:/film/list";
    }

    /**
     * A controller that renders directly without a view
     */
    @RequestMapping("/direct")
    @ResponseBody
    String directExample() {
        return "Hello World!";
    }

    /**
     * A controller that uses an explicit view name
     */
    @RequestMapping("/bar")
    String anyMethodName() {
        return "foo";
    }

    @RequestMapping("/film/list")
    public String ex(Model model) {
        model.addAttribute("filmList", fr.findAll());
        return "films";
    }

    @RequestMapping(value = "/addfilm", method = RequestMethod.GET)
    public String addFilm(Model model, @RequestParam(value = "filmId", required = false) Integer filmId) {
        if(filmId != null) {
            model.addAttribute("film", fr.findOne(filmId));
        } else {
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
}
