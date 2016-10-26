package com.shop.dreampizza.service;

import com.shop.dreampizza.bean.Ingredient;
import com.shop.dreampizza.bean.Order;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

/**
 * Created by Dmytro_Druppov on 9/25/2016.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface OrderService {

    @WebMethod
    Order getOrderById(String order);

    @WebMethod
    String makeOrder(int[] idArray, int[] amountArray);

    @WebMethod
    Order[] getAllOrders();
}
