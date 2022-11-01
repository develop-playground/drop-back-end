package com.dailymap.dailymap.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoLoginFormController {

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

}
