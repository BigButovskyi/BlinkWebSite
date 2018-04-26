package com.blink.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @RequestMapping({"/*"})
    public ModelAndView getMainPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("MainPage");

        return modelAndView;
    }
}
