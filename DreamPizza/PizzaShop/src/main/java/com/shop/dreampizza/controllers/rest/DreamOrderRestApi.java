package com.shop.dreampizza.controllers.rest;

import com.shop.dreampizza.controllers.card.impl.PizzaCard;
import com.shop.dreampizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Dmitry on 10/23/2016.
 */
@Controller
@RequestMapping("/order")
public class DreamOrderRestApi {
    @Autowired
    @Qualifier("order")
    private OrderService orderService;

    @RequestMapping(value = "/makeOrder", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String makeOrder(@RequestBody(required = false) PizzaCard pizzaCard) {
        List<Integer> ids = pizzaCard.getPizzaList();
        List<Integer> count = pizzaCard.getCountList();
        int pizzaId[] = new int[ids.size()];
        int pizzaCount[] = new int[ids.size()];
        for(int i = 0; i < ids.size(); i++) {
            pizzaId[i] = ids.get(i);
            pizzaCount[i] = count.get(i);
            i++;
        }
        String orderId = orderService.makeOrder(pizzaId, pizzaCount);
        pizzaCard.clear();
        return orderId;
    }

}
