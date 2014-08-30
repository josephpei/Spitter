package io.github.josephpei.Controller;

import io.github.josephpei.Service.HibSpittleService;
import io.github.josephpei.Utils.JsonResponse;
import io.github.josephpei.domain.Spittle;
import io.github.josephpei.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value="/u/{username}")
public class SpitterController {
    @Autowired
    private HibSpittleService hibSpittleService;

    //@Autowired
    //private HibUserService hibUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String userPage(@PathVariable String username, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");

        //User user = hibUserService.findUserByName(username);
        List<Spittle> spittles = hibSpittleService.getSpittlesForUser(user);
        model.addAttribute("spittle", new Spittle());
        model.addAttribute("spittles", spittles);

        return "userpage";
    }

    @RequestMapping(method=RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Spittle pushSpittle(@PathVariable String username, @RequestBody Spittle spittle,
                              HttpServletRequest request ) {
        User user = (User) request.getSession().getAttribute("user");
        //User user = hibUserService.findUserByName(username);

        //Spittle spittle = new Spittle();
        //spittle.setText(text);
        spittle.setPushTime(new java.sql.Timestamp((new java.util.Date()).getTime()));
        spittle.setUser(user);
        hibSpittleService.addSpittle(spittle);

        return spittle;

        //response.setHeader("Location", "/user_home/{id}" + spittle.getSpittleId());

        //return "redirect:/u/" + username;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Long delSpittleByID(@PathVariable String username, @PathVariable Long id) {
        hibSpittleService.delSpittleById(id);

        return id;
    }

    @RequestMapping(value="logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();

        return "redirect:/";
    }
}
