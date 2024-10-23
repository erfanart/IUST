package IUST.EE.Control.Users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UsersController {
    @GetMapping("/")
    public String index() {
        return "User Page";
    }
    
}
