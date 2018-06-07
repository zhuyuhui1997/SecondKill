package web;


import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login")
    public String Login() {
        return "login";
    }
    @RequestMapping(value = "/login/process")
    public ModelAndView processLogin(@RequestParam("username") String userName, @RequestParam("password") String check) {
        String password = userService.getPassword(userName);
        if (password == check) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("info");
            User user = userService.getUser(userName);
            modelAndView.addObject("user", user);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            modelAndView.addObject("error","错误的用户名或密码,");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/register")
    public String processRegister(
            @RequestParam("username") String userName,
            @RequestParam("password") String password,
            @RequestParam("userphone") String userPhone,
            @RequestParam("repeat") String check) {
        ModelMap modelMap = new ModelMap();
        if (userName == null || password == null) {
            modelMap.addAttribute("null", "空的用户名或密码");
            return "error";
        }
        if (check != password) {
            modelMap.addAttribute("null", "两次输入的密码不同");
            return "error";
        }
        Long Phone = 0l;
        try {
            Phone = Long.valueOf(userPhone);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("null", "非法手机号");
        }
        User user = new User(userName, password, Phone);
        modelMap.addAttribute("user", user);
        return "info";

    }
}
