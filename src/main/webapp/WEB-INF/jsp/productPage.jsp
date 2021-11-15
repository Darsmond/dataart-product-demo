<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<body>
<h1>List of available product groups</h1>
<div class="leftMenu" style="float:left; width: 30%">
    <table border="1">
        <c:forEach  items="${productGroupCounts}" var ="productGroupCount">
            <tr>
                <td> <a href="/products/${productGroupCount.groupId}">${productGroupCount.groupName}</a> </td>
                <td>${productGroupCount.productCount}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="rightMenu" style="float:right; width: 70%">
    <c:choose>
        <c:when test="${empty products}"></c:when>
            <c:otherwise>
            <table border="1">
                <td>
                    <form method="post" action="/sort/product_name">
                        <button>
                            Наименование
                        </button>
                    </form>
                </td>
                <td>
                    <form method="post" action="/sort/product_price">
                        <button>
                            Цена
                        </button>
                    </form>
                </td>
                <c:forEach  items="${products}" var ="product">
                    <tr>
                        <td>${product.productName}</td>
                        <td>${product.productPrice}</td>
                    </tr>
                </c:forEach>
            </table>
            </c:otherwise>
    </c:choose>
</div>

</body>
</html>