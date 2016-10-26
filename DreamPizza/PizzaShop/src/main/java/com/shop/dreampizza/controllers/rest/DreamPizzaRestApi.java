package com.shop.dreampizza.controllers.rest;

import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.controllers.DreamPizzaController;
import com.shop.dreampizza.service.PizzaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dmitry on 10/19/2016.
 */

@Controller
@RequestMapping(value = "/pizza")
public class DreamPizzaRestApi {

    private static final Logger LOG = Logger.getLogger(DreamPizzaController.class);
    @Autowired
    @Qualifier("pizza")
    private PizzaService pizzaService;

    @RequestMapping(value = {"/getAll"}, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Pizza> getAll() {
        LOG.warn("REST pizza GET");
        return Arrays.asList(pizzaService.getAllPizzas());
    }

    @RequestMapping(value = {"/getById/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Pizza getById(@PathVariable("id") int id ) {
        LOG.warn("REST pizza GET");
        return pizzaService.getPizzaById(id);
    }

    @RequestMapping(value = {"/createPizza"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String insert(@RequestBody Pizza pizza) {
        LOG.warn("REST pizza POST");
        return pizzaService.createPizza(pizza);
    }

    @RequestMapping(value = {"/updatePizza/{id}"}, method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody String update(@PathVariable("id") int id, @RequestBody Pizza pizza) {
        LOG.warn("REST pizza UPDATE");
        return "updated" + pizzaService.updatePizza(id, pizza);
    }

    @RequestMapping(value = {"/remove/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody String delete(@PathVariable("id") int id) {
        LOG.warn("REST pizza DELETE");
        return "removed: " + pizzaService.removePizza(id);
    }

}
