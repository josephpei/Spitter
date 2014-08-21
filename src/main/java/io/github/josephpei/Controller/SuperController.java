package io.github.josephpei.Controller;

import io.github.josephpei.Service.HibUserService;
import io.github.josephpei.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SuperController {
    @Autowired
    private HibUserService hibUserService;

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public ModelAndView showAllUsers() {
        ModelAndView model = new ModelAndView("statics");
        List<User> users = hibUserService.getAllUsers();
        model.addObject("users", users);

        return model;
    }
}
