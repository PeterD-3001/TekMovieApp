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
public class ReviewController {
    @Autowired
    FilmRepository fr;

    /* Reviews hier */
    @RequestMapping("/review/list")
    public String ex3(Model model) {
        model.addAttribute("filmList", fr.findAll());
        return "filmsforreview";
    }

    @RequestMapping(value = "/addreview", method = RequestMethod.GET)
    public String addReview(Model model, @RequestParam(value = "filmId", required = true) Integer filmId)
    {
        model.addAttribute("film", fr.findOne(filmId));
        model.addAttribute("review", new Review());
        return "addreview";
    }

    @RequestMapping(value = "/addreview", method = RequestMethod.POST)
    public String processForm(@RequestParam(value= "filmId", required=true) Integer filmId, @Valid Review review, BindingResult br) {
        if(br.hasErrors()) {
            return "addreview";
        }
        else
        {
            Film f = fr.findOne(filmId);
            f.getReviewList().add(review);
            fr.save(f);
            // String returnString = "redirect:/addreview(filmId=" + filmId.toString() +")";
            // return "redirect:/addreview(filmId=" + filmId.toString() +")";
            return "redirect:/review/list";
        }
    }


}
