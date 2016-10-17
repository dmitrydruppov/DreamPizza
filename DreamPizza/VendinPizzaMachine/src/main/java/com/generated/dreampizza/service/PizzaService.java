package com.generated.dreampizza.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.2.3
 * Sat Oct 15 01:11:02 EEST 2016
 * Generated source version: 2.2.3
 * 
 */
 
@WebService(targetNamespace = "http://service.dreampizza.shop.com/", name = "PizzaService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = ParameterStyle.BARE)
public interface PizzaService {

    @WebMethod
    @WebResult(name = "return", targetNamespace = "", partName = "return")
    public Pizza getPizzaById(
            @WebParam(partName = "arg0", name = "arg0", targetNamespace = "")
                    int arg0
    );

    @WebMethod
    @WebResult(name = "return", targetNamespace = "", partName = "return")
    public PizzaArray getAllPizzas();
}
