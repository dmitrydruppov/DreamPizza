package com.shop.dreampizza.controllers;

import com.shop.dreampizza.controllers.card.impl.PizzaCard;
import com.shop.dreampizza.service.PizzaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dmytro_Druppov on 10/6/2016.
 */
@Controller
public class DreamOrderController {

    private static final Logger LOG = Logger.getLogger(DreamPizzaController.class);
    @Autowired
    @Qualifier("pizza")
    PizzaService pizzaService;
    PizzaCard pizzaCard = new PizzaCard();

//    @RequestMapping(value = "/makeOrder", method = RequestMethod.POST)
//    public ModelAndView makeOrder() {
//        ModelAndView modelAndView = new ModelAndView("infoOrder");
//        if(pizzaCard.getCard() != null && !pizzaCard.getCard().isEmpty())
//            makeOrder(pizzaCard);
//        return modelAndView;
//    }

    @RequestMapping(value = "/removeFromCard", method = RequestMethod.POST)
    public ModelAndView removeFromCard(@RequestParam("pizzaId") int pizzaId) {
        ModelAndView modelAndView = new ModelAndView("shopCard");
        pizzaCard.removePizza(pizzaId);
        modelAndView.addObject("shopCard", pizzaCard.getCard());
        return modelAndView;
    }

    @RequestMapping(value = "/addToCard", method = RequestMethod.POST)
    public ModelAndView addToCard(@RequestParam("pizzaId") int pizzaId, @RequestParam("pizzaCount") int pizzaCount) {
        ModelAndView modelAndView = new ModelAndView("shopCard");
        pizzaCard.addPizza(pizzaId, pizzaCount);
        modelAndView.addObject("shopCard", pizzaCard.getCard());
        return modelAndView;
    }

//    private void makeOrder(PizzaCard pizzaCard) {
//        int pizzaId[] = new int[pizzaCard.getCard().size()];
//        int pizzaCount[] = new int[pizzaCard.getCard().size()];
//        int i = 0;
//        for(Map.Entry<Integer, Integer> entryCard : pizzaCard.getCard().entrySet()) {
//            pizzaId[i] = entryCard.getKey();
//            pizzaCount[i] = entryCard.getValue();
//            i++;
//        }
//        orderService.makeOrder(pizzaId, pizzaCount);
//        pizzaCard.clear();
//    }
}
