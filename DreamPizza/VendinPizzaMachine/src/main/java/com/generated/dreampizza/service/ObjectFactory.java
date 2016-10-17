
package com.generated.dreampizza.service;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.shop.dreampizza.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.shop.dreampizza.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Pizza }
     * 
     */
    public Pizza createPizza() {
        return new Pizza();
    }

    /**
     * Create an instance of {@link Dough }
     * 
     */
    public Dough createDough() {
        return new Dough();
    }

    /**
     * Create an instance of {@link PizzaArray }
     * 
     */
    public PizzaArray createPizzaArray() {
        return new PizzaArray();
    }

    /**
     * Create an instance of {@link ShopStock }
     * 
     */
    public ShopStock createShopStock() {
        return new ShopStock();
    }

    /**
     * Create an instance of {@link Recipe }
     * 
     */
    public Recipe createRecipe() {
        return new Recipe();
    }

}
