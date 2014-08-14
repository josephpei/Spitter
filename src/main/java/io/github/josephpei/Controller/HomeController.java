package io.github.josephpei.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
    public String hello() {
        return "home";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView loginCheck(@RequestParam("name") String name, @RequestParam("pass") String pass,
                                   HttpServletRequest request) {
        String userPage = "redirect:" + name;
        return new ModelAndView(userPage);
    }

    @RequestMapping(value="/{name}")
    public ModelAndView userPage(@PathVariable String name, HttpServletRequest request, Model model) {
        model.addAttribute("name", name);
        return new ModelAndView("sucess");
    }
}
