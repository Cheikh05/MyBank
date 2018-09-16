package sn.cheikh.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BanqueSecurityController {
    @RequestMapping( value = "/login" )
    public String authentification() {
        return "login";
    }

    @RequestMapping( value = "/" )
    public String home() {
        return "redirect:/bank";
    }

    @RequestMapping( value = "/403" )
    public String accessDined() {
        return "403";
    }

}
