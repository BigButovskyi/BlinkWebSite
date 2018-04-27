package com.blink.controllers;

import com.blink.services.blinkServices.BlinkServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
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
