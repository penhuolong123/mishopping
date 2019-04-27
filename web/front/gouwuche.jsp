<%@ page import="java.lang.Long" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.oracle.mishopping.bean.Products" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.oracle.mishopping.bean.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--这是测试用的获取购物车数据--%>
<c:if test="${empty sessionScope.productMap}">
    <c:redirect url="/CartControllerTestServlet"></c:redirect>
</c:if>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="order by dede58.com"/>
    <title>我的购物车-小米商城</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
    <%--
        <script src="<%=request.getContextPath()%>/js/jQuery-1.9.1.js"></script>
        <script src="<%=request.getContextPath()%>/js/CartJquery.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/gouwuche.js"></script>

        <script src="<%=basePath%>js/jQuery-1.9.1.js"></script>
        <script src="<%=basePath%>js/CartJquery.js"></script>
        <script src="<%=basePath%>js/jquery-2.1.4.min.js"></script>
        <script src="<%=basePath%>js/gouwuche.js"></script>
    --%>
    <script src="js/jQuery-1.9.1.js"></script>
    <script src="js/CartJquery.js"></script>
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="js/gouwuche.js"></script>
</head>
<body>
<div class="banner_x center">
    <a href="index.jsp" target="_blank">
        <div class="logo fl"></div>
    </a>
    <div class="wdgwc fl ml40">我的购物车</div>
    <div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>
    <div class="dlzc fr">
        <ul>
            <li><a href="login.jsp" target="_blank">登录</a></li>
            <li>|</li>
            <li><a href="register.jsp" target="_blank">注册</a></li>
        </ul>
    </div>
    <div class="clear"></div>
</div>

<form action="../CartController?method=dingdan" method="post">

    <div class="xiantiao"></div>
    <div class="gwcxqbj">
        <div class="gwcxd center">
            <div class="top2 center">
                <div class="sub_top fl">
                    <input type="checkbox" value="quanxuan" class="quanxuan" onclick="allCheck(this)">全选
                </div>
                <div class="sub_top fl">商品名称</div>
                <div class="sub_top fl">单价</div>
                <div class="sub_top fl">数量</div>
                <div class="sub_top fl">小计</div>
                <div class="sub_top fr">操作</div>
                <div class="clear"></div>
            </div>

            <%
                //获取当前登陆用户的ID
                Users user = new Users();
                //long userId = user.getUid();
                long userId = 1;
            %>
            <c:if test="${sessionScope.productMap!=null}">
                <c:forEach var="item" items="${productMap.get(1)}" begin="0" step="1" end="100" varStatus="status">
                    <div class="content2 center">
                        <div class="sub_content fl ">
                            <input type="checkbox" name="prochk" class="check" value="${item.getProduct().pid}"/>
                        </div>
                        <div class="sub_content fl"><img src="image/${item.getProduct().pimg}" width="100px;" height="100px"></div>
                        <div class="sub_content fl ft20">${item.getProduct().pname}
                        </div>
                        <div class="sub_content fl ">${item.getProduct().pprice}</div>
                        <div class="sub_content fl">
                            <input type="button" value="-"
                                   onclick="javascript:subOne(${status.index},${item.getProduct().pid})" style="position: relative;left: -5px;background-color: #83c44e;width: 10px;height: 20px;border: none;background-color: transparent "/>
                            <input id="num${item.getProduct().pid}" type="text" value="${item.num}" style="width: 30px;font-size: 18px;color: orange;"/>
                            <input type="button" value="+"
                                   onclick="javascript:addOne(${status.index},${item.getProduct().pid})" style="position: relative;left: 55px;top: -120px;background-color: #83c44e;width: 10px;height: 20px;border: none;background-color: transparent"/>
                        </div>
                        <div class="sub_content fl">
                            <input type="text" id="total${item.getProduct().pid}" value="${item.getProduct().pprice}" style="border: none;outline: none;color: orangered;font-size: 16px;"/>
                        </div>
                        <div class="sub_content fl"><a href="../CartController?method=delProduct&index=${item.getProduct().pid}" style="font-size: 20px;color: crimson">X</a>
                        </div>
                        <div class="clear"></div>
                    </div>
                </c:forEach>
            </c:if>

        </div>
        <div class="jiesuandan mt20 center">
            <div class="tishi fl ml20">
                <ul>
                    <li>
                        <nav class="pagination" style="display: none;"></nav>
                    </li>
                    <li><a href="../front/liebiao.jsp">继续购物</a></li>
                    <li><a href="/CartController?method=clear">清空购物车</a></li>
                    <li>|</li>
                    <div class="clear"></div>
                </ul>
            </div>
            <div class="jiesuan fr">
                <div class="jiesuanjiage fl">合计（不含运费）：
                   <%-- <span name="priceSum">2499.00元</span>--%>
                    <input type="text" id="totolprice" name="priceSum" value="2488" style="border: none;outline: none;color: orangered;font-size: 20px;"/>元
                </div>
                <div class="jsanniu fr"><input class="jsan" type="submit" name="jiesuan" value="去结算"/></div>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</form>
</body>
</html>


