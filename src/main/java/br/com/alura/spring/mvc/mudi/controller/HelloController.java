package br.com.alura.spring.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {

    @GetMapping("/teste")
    public String hello(Model model){
        model.addAttribute("nome", "Cleber");
        return "hello";
    }
}
