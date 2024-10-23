package com.example.demo;

import com.example.demo.entity.Form;
import com.example.demo.repository.FormRepository;
import com.example.demo.services.FormService;
import com.example.demo.services.impl.FormServiceImpl;
import org.hibernate.Session;       
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(DemoApplication.class, args);

            
    }

}
 