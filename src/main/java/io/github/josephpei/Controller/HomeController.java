package io.github.josephpei.Controller;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import io.github.josephpei.Service.UserService;
import io.github.josephpei.domain.LoginCommand;
import io.github.josephpei.domain.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
public class HomeController extends KaptchaExtend {
    private static String salt = "SECRET";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/captcha.jpg", method = RequestMethod.GET)
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.captcha(req, resp);
    }

    @RequestMapping(value={"/", "/home.html"}, method = RequestMethod.GET)
    public String hello() {
        return "home";
    }

    @RequestMapping(value="/login.do", method = RequestMethod.POST)
    public ModelAndView loginCheck(HttpServletRequest request,
                                   @RequestParam(value = "name", required = true) String name,
                                   @RequestParam(value = "pass", required = true) String pass,
                                   @RequestParam(value = "captcha", required = true) String kaptchaReceived) {
        //用户输入的验证码的值
        String kaptchaExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //校验验证码是否正确
        if (kaptchaReceived == null || !kaptchaReceived.equals(kaptchaExpected)) {
            return new ModelAndView("home");//返回验证码错误
        }
        String secretPass = DigestUtils.md5Hex(salt + pass);
        boolean valid = userService.hasMatchUser(name, secretPass);
        if (!valid) {
            return new ModelAndView("home", "error", "Username or password error!");
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
