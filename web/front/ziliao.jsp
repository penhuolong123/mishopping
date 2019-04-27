<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script>
        var input = document.getElementById('shuliang');

        input.oninput = function (event) {
            var value = this.value;
            var regx = /\+|\-/i;

            if (regx.test(value)) {
                this.value = value.substring(0, value.length - 1);
            }
        };
        $(document).ready(function () {
            $(window).scroll(function () {
                if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
                    //当鼠标滚动到网页底部，发起ajax加载下一页
                    var nowPage = $("[name='nowPage']").val();
                    $.ajax({
                        type: "get",
                        url: "/CartController?method=listBlogsByAjaxPage&page=" + (parseInt(nowPage) + 1) + "&count=10",
                        beforeSend: function () {
                            $("#loadImg").show(50);
                        },
                        complete: function () {
                            $("#loadImg").hide(1);
                        },
                        success: function (data) {
                            for (var n = 0; n < data.length; n++) {
                                $("#allContent").append("<article class='excerpt excerpt-5' style=''><div style='float: left; width: 70%;'><a class='focus' href='#' title='" + data[n].title + "' target='_blank' ><img class='thumb' data-original='images/201610181739277776.jpg' src='images/201610181739277776.jpg' alt='用DTcms做一个独立博客网站（响应式模板）'  style='display: inline;'></a><header><a class='cat' href='#' title='java板块' >java板块<i></i></a><h2><a href='BlogServlet?method=getDetailOfBlogById&blogid=" + data[n].blogid + "' title='" + data[n].title + "' target='_blank' >" + data[n].blogid + "---" + data[n].title + "</a></h2></header><p class='meta'><time class='time'><i class='glyphicon glyphicon-time'></i>" + data[n].publishTime + "</time><span class='views'><i class='glyphicon glyphicon-eye-open'></i> " + data[n].visitedCount + "</span> <a class='comment' href='##comment' title='评论' target='_blank' ><i class='glyphicon glyphicon-comment'></i>0</a></p><p class='note'>" + data[n].content + " ...</p></div><div style='float: left; width: 30%'><a href='/UserServlet?method=loadUserInfo&userid=" + data[n].userid + "'><img src='" + data[n].userimage + "' width='40px' height='40px' style='border: 1px solid gray; border-radius: 20px'/></a><br/>" + data[n].nickname + "</div></article>");
                            }
                        }

                    });
                    $("[name='nowPage']").val(parseInt(nowPage) + 1);
                }
            });


            $(function () {
                //1. 注册事件
                $(".text").change(function () {
                    //2. 验证数据的有效性
                    var number = this.value; //也可以使用$(this).val();
                //isNaN(number)表示若number不是数字就返回真
                    if (!isNaN(number) && parseInt(number) == number && number > 0) {
//如果合法，同步更新的数
                        $(this).attr("lang", number);
//找到当前标签中第一个是tr的父节点，然后拿到属性为lang的值，也就是商品的id
                        var pid = $(this).parents("tr:first").attr("lang");
//发送Ajax请求，传输当前的数量与商品的id，返回修改数量后的总价格
                        $.post("sorder_updateSorder.action",
                            {number: number, 'product.id': pid},
                            function (total) {
                                $("#total").html(total); //所有商品小计
                                var yunfei = $("#yunfei").html();
                                $("#totalAll").html((total * 1 + yunfei * 1).toFixed(2));//所有商品小计和运费的和
                            }, "text");
//计算单个商品的小计，保留两位小数
                        var price = ($(this).parent().prev().html() * number).toFixed(2);
                        $(this).parent().next().html(price);
                    } else {
//如果非法，还原为刚刚合法的数
                        this.value = $(this).attr("lang");
                    }
                })
            })
            $(window).scroll(function () {
                if ($(document).scrollTop() <= 0)
                    })
            $("img[src='CodeServlet']").click(function () {
                $(this).attr("src", "CodeServlet?sdfsf=" + Math.random());
            });
            $(".content-icon-item-1").click(function () {
                var num = parseInt($('#invest-number').val());
                totalnum = num - 100;
                if (totalnum < 0) {
                    $('#invest-number').val("0");
                    return;
                }
                $('#invest-number').val(totalnum);
            })
            // 获取数量的值
            $("#shuliang input[type=number]").click(function () {
                var value = this.value;
                var regx = /\+|\-/i;

                if (regx.test(value)) {
                    alert("hello");
                    this.value = value.substring(0, value.length - 1);
                }
                var curNum = $(this).val();
                $.get("/CartController?method=addNumber&Num=" + curNum, function (data) {
                    if (data == 'false') {
                        alert("增加数量。。。。。");
                    }
                });

            })


            //这事焦点失去后判断用户名是否存在的ajax代码

            $("#username").blur(function () {
                $.post("/UserServlet", {"method": "checkUserExist", "username": $(this).val()}, function (aaa) {
                    if (aaa == 'false') {
                        alert('用户名不存在，请检查用户名是否是注册过的账户！');
                    }
                })
            });
        });


    </script>
</head>
<body>


</body>
</html>

<%--
			提交数据有：

			pid            int auto_increment primary key,
			pname          varchar(50)     not null,
			pprice         double          not null,
			pstock         int default '0' null,
			pdes           text            null,
			pimg           varchar(200)    null,
			pstate         int default '1' not null
			comment '0:下架
			1:上架',
			  categoryid     int             null,
			  ppricediscount double          not null
		--%><%--
					${productMap.get(1).get(2).pname}
					${productMap.get(1).get(2).pprice}
					${productMap.get(1).get(2).pstock}
					${productMap.get(1).get(2).pdes}
					${productMap.get(1).get(2).pimg}
					${productMap.get(1).get(2).pstate}


					--%>

<%--
			<div class="content2 center">
				<div class="sub_content fl ">
					<input type="checkbox" value="quanxuan" class="quanxuan" />
				</div>
				<div class="sub_content fl"><img src="../image/gwc_xiaomi6.jpg"></div>
				<div class="sub_content fl ft20">小米6全网通6GB内存+64GB 亮黑色</div>
				<div class="sub_content fl ">2499元</div>
				<div class="sub_content fl">
					<input class="shuliang" type="number" value="1" step="1" min="1" >
				</div>
				<div class="sub_content fl">2499元</div>
				<div class="sub_content fl"><a href="">×</a></div>
				<div class="clear"></div>
			</div>
--%>

