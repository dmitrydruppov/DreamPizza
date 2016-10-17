package com.shop.dreampizza.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dmitry on 10/16/2016.
 */

public class ListPizza {

    //private List<Pizza> listPizzas;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @XmlElement(name = "pizza")
//    @XmlElementWrapper(name = "pizzas")
//    public List<Pizza> getListPizzas() {
//        return listPizzas;
//    }
//
//    public void setListPizzas(List<Pizza> listPizzas) {
//        this.listPizzas = listPizzas;
//    }
}
