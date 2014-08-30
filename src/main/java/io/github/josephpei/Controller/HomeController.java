package io.github.josephpei.Controller;


import io.github.josephpei.Service.HibSpittleService;
import io.github.josephpei.Service.HibUserService;
import io.github.josephpei.Service.UserService;
import io.github.josephpei.Utils.RegistrationValidator;
import io.github.josephpei.domain.LoginCommand;
import io.github.josephpei.domain.RegisterCommand;
import io.github.josephpei.domain.Spittle;
import io.github.josephpei.domain.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    private static String salt = "SECRET";
    //private static final int DEFAULT_SPITTERS_PER_PAGE = 25;

    @Autowired
    private HibUserService hibUserService;
    //private UserService userService;

    @Autowired
    private HibSpittleService hibSpittleService;

    @Autowired
    private RegistrationValidator registrationValidator;


    @RequestMapping(value="index", method = RequestMethod.GET)
    public String loginCommand(Model model){
        List<Spittle> spittleList = hibSpittleService.getRecentSpittles(5);
        model.addAttribute("spittleList", spittleList);
        model.addAttribute("loginCommand", new LoginCommand());
        return "index";
    }

    @RequestMapping(value="index", method = RequestMethod.POST)
    public ModelAndView loginCheck(@ModelAttribute("loginCommand") @Valid LoginCommand loginCommand,
                                   BindingResult result,
                                   HttpServletRequest request, Model model) {
        //String kaptchaReceived = loginCommand.getKaptcha();
        String name = loginCommand.getUsername();
        String pass = loginCommand.getPassword();

        if (result.hasErrors()) {
            return new ModelAndView("index");
        }

//        String kaptchaExpected = (String) request.getSession().getAttribute(
//                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//
//        if (kaptchaReceived == null || !kaptchaReceived.equals(kaptchaExpected)) {
//            return new ModelAndView("home", "yzm", "Kaptcha error");
//        }
        String secretPass = DigestUtils.md5Hex(salt + pass);
        boolean valid = hibUserService.hasMatchUser(name, secretPass);
        if (!valid) {
            return new ModelAndView("redirect:index", "fatal", "Username or password error!");
        } else {
            User user = hibUserService.findUserByName(name);
            user.setLastIp(request.getRemoteAddr());
            user.setLastVisit(new java.sql.Timestamp((new java.util.Date()).getTime()));
            hibUserService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            String userPage = "redirect:u/" + name;
            return new ModelAndView(userPage);
        }

    }

    @RequestMapping(value="register.html", method=RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("registerCommand", new RegisterCommand());
        return "register";
    }

    @RequestMapping(value="register.do", method=RequestMethod.POST)
    public String register(@ModelAttribute("registerCommand") @Valid RegisterCommand registerCommand,
                           BindingResult result,
                           HttpServletRequest request, Model model) {
        registrationValidator.validate(registerCommand, result);

        if (result.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setUserName(registerCommand.getUsername());
        user.setPassword(DigestUtils.md5Hex(salt + registerCommand.getPassword()));
        user.setLastIp(request.getRemoteAddr());
        user.setLastVisit(new Date());
        hibUserService.insertUser(user);

        model.addAttribute("loginCommand", new LoginCommand());
        return "index";
    }
}
