package com.shop.dreampizza.service;

        import com.shop.dreampizza.bean.Ingredient;
        import com.shop.dreampizza.bean.ListPizza;
        import com.shop.dreampizza.bean.Pizza;
        import com.shop.dreampizza.bean.Recipe;

        import javax.jws.WebMethod;
        import javax.jws.WebService;
        import javax.jws.soap.SOAPBinding;
        import javax.ws.rs.GET;
        import javax.ws.rs.Path;
        import javax.ws.rs.Produces;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PizzaService {

    @WebMethod
    Pizza[] getAllPizzas();

    @WebMethod
    Pizza getPizzaById(int id);
}
