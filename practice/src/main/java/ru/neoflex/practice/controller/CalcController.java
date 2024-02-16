package ru.neoflex.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {
    @GetMapping("/plus/{a}/{b}")
    public Integer Sum (@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        return a+b;
    }

    @GetMapping("/minus/{a}/{b}")
    public Integer Diff(@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        return a-b;
    }
}
