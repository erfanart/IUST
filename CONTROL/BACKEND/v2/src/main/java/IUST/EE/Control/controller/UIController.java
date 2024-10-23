package IUST.EE.Control.controller;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;


@Controller

public class UIController {
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String index() {
        return "forward:/index.html";
    }
}
