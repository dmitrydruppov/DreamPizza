<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action = "removeFromCard" method="post">
       Ваш заказ:
       <table>
           <c:forEach var="entry" items="${shopCard}">
             <tr>
                <td> пицца: <c:out value="${entry.key.name}"/> <c:out value="${entry.key.dough.name}"/>, кол-во: <c:out value="${entry.value}"/> </td>
                <td> <input type="submit" value="${entry.key.id}" name="pizzaId"> </td>
             <tr>
           </c:forEach>
       </table>
    </form>

     <br/>

     <form action="pizzas" method="get">
         <input type="submit" value="menu">
     </form>

</body>
</html>