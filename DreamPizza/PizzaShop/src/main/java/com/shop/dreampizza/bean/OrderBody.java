package com.shop.dreampizza.bean;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Dmytro_Druppov on 10/17/2016.
 */
@XmlRootElement(name = "OrderBody")
public class OrderBody implements Serializable {

    Integer[] idArray;
    Integer[] amountArray;

    public Integer[] getAmountArray() {
        return amountArray;
    }

    public void setAmountArray(Integer[] amountArray) {
        this.amountArray = amountArray;
    }

    public Integer[] getIdArray() {
        return idArray;
    }

    public void setIdArray(Integer[] idArray) {
        this.idArray = idArray;
    }
}
