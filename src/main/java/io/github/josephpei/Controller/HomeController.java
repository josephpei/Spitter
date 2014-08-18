package io.github.josephpei.Controller;


import io.github.josephpei.Service.UserService;
import io.github.josephpei.domain.LoginCommand;
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

import java.util.Date;

@Controller
public class HomeController {
    private static String salt = "SECRET";

    @Autowired
    private UserService userService;

//    @RequestMapping(value={"/", "/home.html"}, method = RequestMethod.GET)
//    public String hello() {
//        return "home";
//    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String loginCommand(Model model){
        model.addAttribute("loginCommand", new LoginCommand());
        return "home";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView loginCheck(@ModelAttribute("loginCommand") @Valid LoginCommand loginCommand,
                                   BindingResult result,
                                   HttpServletRequest request, Model model) {
        String kaptchaReceived = loginCommand.getCaptcha();
        String name = loginCommand.getName();
        String pass = loginCommand.getPass();

        if (result.hasErrors()) {
            return new ModelAndView("home");
        }
        //用户输入的验证码的值
        String kaptchaExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //校验验证码是否正确
        if (kaptchaReceived == null || !kaptchaReceived.equals(kaptchaExpected)) {
            return new ModelAndView("home", "yzm", "Kaptcha error");//返回验证码错误
        }
        String secretPass = DigestUtils.md5Hex(salt + pass);
        boolean valid = userService.hasMatchUser(name, secretPass);
        if (!valid) {
            return new ModelAndView("redirect:home", "fatal", "Username or password error!");
        } else {
            User user = userService.findUserByName(name);
            user.setLastIp(request.getRemoteAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            String userPage = "redirect:user/" + name;
            return new ModelAndView(userPage);
        }

    }

    @RequestMapping(value="/user/{name}")
    public ModelAndView userPage(@PathVariable String name, HttpServletRequest request, Model model) {
        model.addAttribute("name", name);
        return new ModelAndView("sucess");
    }

    @RequestMapping(value="register.html", method=RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value="register.do", method=RequestMethod.POST)
    public String register(@RequestParam("name") String name, @RequestParam("pass") String pass,
                           HttpServletRequest request) {
        User user = new User();
        user.setUserName(name);
        user.setPassword(DigestUtils.md5Hex(salt + pass));
        user.setLastIp(request.getRemoteAddr());
        user.setLastVisit(new Date());
        userService.insertUser(user);
        return "home";
    }
}
