package com.shop.dreampizza.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */
@XmlRootElement(name = "Dough")
public class Dough {

    public Dough() {

    }

    private int idDough;
    private String name;
    private BigDecimal cost;

    public int getIdDough() {
        return idDough;
    }

    public void setIdDough(int idDough) {
        this.idDough = idDough;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Dough{" +
                "idDough=" + idDough +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
