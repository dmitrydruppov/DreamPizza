package com.shop.dreampizza.service;

import com.shop.dreampizza.bean.Pizza;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */
@WebService
public interface PizzaService {

    @WebMethod
    @SOAPBinding(style = SOAPBinding.Style.RPC)
    public List<Pizza> getAllPizzas();
}
