package io.github.josephpei.Controller;

import io.github.josephpei.Service.HibSpittleService;
import io.github.josephpei.Service.HibUserService;
import io.github.josephpei.domain.Spittle;
import io.github.josephpei.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/user_home")
public class SpitterController {
    @Autowired
    private HibSpittleService hibSpittleService;

    @Autowired
    private HibUserService hibUserService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView userPage(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        //User user = hibUserService.findUserByName(name);
        List<Spittle> spittles = hibSpittleService.getSpittlesForUser(user);
        model.addAttribute("spittle", new Spittle());
        model.addAttribute("spittles", spittles);

        return new ModelAndView("user_page");
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String pushSpittle(@ModelAttribute Spittle spittle,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        //User user = hibUserService.findUserByName(name);

        //Spittle spittle = new Spittle();
        //spittle.setText(text);
        spittle.setPushTime(new java.sql.Timestamp((new java.util.Date()).getTime()));
        spittle.setUser(user);
        hibSpittleService.addSpittle(spittle);

        response.setHeader("Location", "/user_home/{id}" + spittle.getSpittleId());

        return "redirect:user_home";
    }

    @RequestMapping(value="logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();

        return "redirect:/";
    }
}
