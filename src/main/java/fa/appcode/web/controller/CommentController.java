package fa.appcode.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {

    @RequestMapping("/register")
    public String showRegister(Model model){
        return "register";
    }
}
