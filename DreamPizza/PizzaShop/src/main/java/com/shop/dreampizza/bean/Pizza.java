package com.shop.dreampizza.bean;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */

@XmlRootElement(name = "Pizza")
@Document (collection = Pizza.COLLECTION_NAME)
public class Pizza {

    public static final String COLLECTION_NAME = "pizza";
    @Id
    private int id;
    private String name;
    private Dough dough;
    private Recipe[] recipe;
    private BigDecimal cost;

    public int getId() {return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "dough", required = false)
    public Dough getDough() {
        return dough;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    @XmlElement(name = "recipe", required = false)
    public Recipe[] getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe[] recipe) {
        this.recipe = recipe;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        return getId() == pizza.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dough=" + dough +
                ", recipe=" + Arrays.toString(recipe) +
                '}';
    }
}
