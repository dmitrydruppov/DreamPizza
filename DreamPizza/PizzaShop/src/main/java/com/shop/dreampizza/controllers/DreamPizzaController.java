package com.shop.dreampizza.controllers;

import com.shop.dreampizza.service.PizzaService;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dmytro_Druppov on 10/1/2016.
 */

@Controller
public class DreamPizzaController {

    private static final Logger LOG = Logger.getLogger(DreamPizzaController.class);
    @Autowired private PizzaService pizzaService;

    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public ModelAndView printAllPizzas() {
        LOG.warn("init message");
        ModelAndView modelAndView = new ModelAndView("pizzas");
        modelAndView.addObject("pizzas", pizzaService.getAllPizzas());
        return modelAndView;
    }

}
