package IUST.EE.Control.controller;



// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@RequestMapping("/api")
public class APIController {
    @GetMapping("/")
    public String index(){
        return "Api Page";
        
    }
    
}
