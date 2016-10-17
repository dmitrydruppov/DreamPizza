
package com.shop.dreampizza.service.order.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;

import com.shop.dreampizza.service.order.net.IntArray;
import com.shop.dreampizza.service.order.net.ObjectFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "OrderService", targetNamespace = "http://service.dreampizza.shop.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface OrderService {

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.dreampizza.shop.com/OrderService/makeOrderRequest", output = "http://service.dreampizza.shop.com/OrderService/makeOrderResponse")
    public String makeOrder(
            @WebParam(name = "arg0", partName = "arg0")
            IntArray arg0,
            @WebParam(name = "arg1", partName = "arg1")
            IntArray arg1);

}
