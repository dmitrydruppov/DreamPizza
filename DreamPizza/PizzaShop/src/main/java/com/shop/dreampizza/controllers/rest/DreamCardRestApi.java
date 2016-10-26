package com.shop.dreampizza.controllers.rest;

import com.shop.dreampizza.controllers.card.impl.PizzaCard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dmitry on 10/26/2016.
 */
public class DreamCardRestApi {

    private PizzaCard pizzaCard = new PizzaCard();

    @RequestMapping(value = "/removeFromCard", method = RequestMethod.DELETE)
    public @ResponseBody
    String removeFromCard(@RequestParam("pizzaId") String pizzaId) {
        if(pizzaId != null) {
            int id = Integer.parseInt(pizzaId);
            boolean condition = pizzaCard.removePizza(id);
            return (condition)? pizzaId + " removed" : " nothing to delete";
        }
        return "no parameter!";
    }

    @RequestMapping(value = "/addToCard", method = RequestMethod.POST)
    public @ResponseBody String addToCard(HttpServletRequest request) {
        String pizzaCount = request.getParameter("pizzaCount");
        String pizzaId = request.getParameter("pizzaId");
        if(pizzaId != null || pizzaCount != null) {
            int count = Integer.parseInt(pizzaCount);
            int id = Integer.parseInt(pizzaId);
            pizzaCard.addPizza(id, count);
            return pizzaId + " added";
        }
        return "no parameters in request";
    }

}
