<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
       <table>
           <c:forEach var="pizza" items="${pizzas}">
            <tr>
                <td>Код пиццы: ${pizza.id} , ${pizza.name} ${pizza.dough.name},цена: ${pizza.cost}грн -рецепт:
                <c:forEach var="recipe" items="${pizza.recipe}">
                    <td>${recipe.shopStock.name} кол-во ${recipe.amount}, </td>
                </c:forEach>
                </td>
            </tr>
            </c:forEach>
       </table>
       <br/>

       <form action="addToCard" method="post">
                  <table>
                      <tr>
                           <td>Код пиццы : <input type="text" name="pizzaId"></td><td>Количество: <input type="text" name="pizzaCount"> </td>
                      </tr>
                      <tr>
                           <td>Сделать заказ : <input type="submit"></td>
                      </tr>
                  </table>
       </form>
</body>
</html>