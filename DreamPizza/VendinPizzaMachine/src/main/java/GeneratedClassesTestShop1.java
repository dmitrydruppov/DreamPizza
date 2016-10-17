import com.generated.dreampizza.service.Pizza;
import com.generated.dreampizza.service.PizzaArray;
import com.generated.dreampizza.service.PizzaService;
import com.generated.dreampizza.service.Recipe;
import com.generated.dreampizza.service.impl.PizzaServiceImplService;

import java.util.List;

/**
 * Created by Dmitry on 10/14/2016.
 */
public class GeneratedClassesTestShop1 {


    public static void main(String[] args) {
        PizzaServiceImplService pizzaServiceImplService = new PizzaServiceImplService();
        PizzaService pizzaService = pizzaServiceImplService.getPizzaServiceImplPort();
        PizzaArray pizzaArray = pizzaService.getAllPizzas();
        List<Pizza> pizzaList = pizzaArray.getItem();
        for (Pizza piz : pizzaList) {
            System.out.print("код: " + piz.getId() + ", " + piz.getName() + " -рецепт: ");
            for (Recipe recipe : piz.getRecipe()) {
                System.out.print(recipe.getShopStock().getName() + ", ");
            }
            System.out.println();
        }


    }


}
