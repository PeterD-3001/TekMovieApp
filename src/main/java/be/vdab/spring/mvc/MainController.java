package be.vdab.spring.mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class MainController
{
    @RequestMapping("/")
    public String landing()
    {
        return "redirect:/landing";
    }

    /**
     * A controller that renders directly without a view
     **/
    @RequestMapping("/direct")
    @ResponseBody
    String directExample()
    {
        return "Hello World!";
    }

    /**
     * A controller that uses an explicit view name
     */
    @RequestMapping("/landing")
    String anyMethodName()
    {
        return "index";
    }


}
