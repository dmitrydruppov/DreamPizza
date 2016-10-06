import com.shop.dreampizza.service.pizza.Pizza;
import com.shop.dreampizza.service.pizza.PizzaArray;
import com.shop.dreampizza.service.pizza.Recipe;
import com.shop.dreampizza.service.pizza.impl.PizzaService;
import com.shop.dreampizza.service.pizza.impl.PizzaServiceImplServiceShop1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */
public class TestClass {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        List<String> list = (List<String>) context.getBean("simpleList");
        System.out.println(list.getClass());
//        PizzaServiceImplServiceShop1 pizzaServiceImplService = new PizzaServiceImplServiceShop1();
//        PizzaService service = pizzaServiceImplService.getPizzaServiceImplPort();
//        PizzaArray pizzaArray = service.getAllPizzas();
//        List<Pizza> pizzaList = pizzaArray.getItem();
//        System.out.println(pizzaList.size());
//        for(Pizza piz : pizzaList) {
//            System.out.println("-recipe: ");
//            for(Recipe recipe : piz.getRecipe()) {
//                System.out.println(recipe.getShopStock().getName() + " " + recipe.getAmount());
//            }
//        }
//        System.out.println("hello");
    }
}
