
package com.generated.dreampizza.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recipe complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recipe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pizza" type="{http://service.dreampizza.shop.com/}pizza" minOccurs="0"/>
 *         &lt;element name="shopStock" type="{http://service.dreampizza.shop.com/}shopStock" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipe", propOrder = {
    "amount",
    "pizza",
    "shopStock"
})
public class Recipe {

    protected int amount;
    protected Pizza pizza;
    protected ShopStock shopStock;

    /**
     * Gets the value of the amount property.
     * 
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(int value) {
        this.amount = value;
    }

    /**
     * Gets the value of the pizza property.
     * 
     * @return
     *     possible object is
     *     {@link Pizza }
     *     
     */
    public Pizza getPizza() {
        return pizza;
    }

    /**
     * Sets the value of the pizza property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pizza }
     *     
     */
    public void setPizza(Pizza value) {
        this.pizza = value;
    }

    /**
     * Gets the value of the shopStock property.
     * 
     * @return
     *     possible object is
     *     {@link ShopStock }
     *     
     */
    public ShopStock getShopStock() {
        return shopStock;
    }

    /**
     * Sets the value of the shopStock property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShopStock }
     *     
     */
    public void setShopStock(ShopStock value) {
        this.shopStock = value;
    }

}
