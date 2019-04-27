package com.oracle.mishopping.controller;

import com.oracle.mishopping.bean.Items;
import com.oracle.mishopping.bean.Order;
import com.oracle.mishopping.bean.Products;
import com.oracle.mishopping.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 购物车模块
 */
@WebServlet(name = "CartController", urlPatterns = "/CartController")
public class CartController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //设置请求和响应的编码格式
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        String method = req.getParameter("method");
        switch (method) {
            case "add": {
                //将商品加入购物车
                add(req, res);
                break;
            }
            case "subOne": {
                //将购物车商品数量-1
                subOne(req, res);
                System.out.println("-----------");
                break;
            }
            case "addOne": {
                //将购物车商品数量加1
                System.out.println("++++++++");
                addOne(req, res);
                break;
            }
            case "dingdan": {
                //购物车订单提交
                dingdan(req, res);
                break;
            }
            case "delProduct": {
                //从购物车中删除
                System.out.println("run in delProduct........");
                delProduct(req, res);
                break;
            }
            case "delete": {
                //删除单个商品
      //          delete(req, res);
                break;
            }
            case "clear": {
                //清空购物车
               clear(req, res);
                break;
            }
            default: {
                break;
            }
        }

    }


    /**
     * 将商品加入购物车
     * @param req
     * @param res
     * @throws IOException
     */
    private void add(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        /****************声明对象部分*************************/
        long userid = 1;

        // Items对象封装商品及其数量，并将对象存储在List集合
        Items items = new Items();
        List <Items> Cart = new ArrayList <Items>();
        CartDao cart = new CartDao();

        //购物车Map对象 (Key=用户ID，Value=List<Items>集合)
        Map <Long, List> cartMap = new HashMap <Long, List>();

        //通过ID获取要加入购物车的商品
        String method = req.getParameter("method");
        String index = req.getParameter("pid");

        long pid = Long.parseLong(index);

        Products poduct = cart.getProductById(pid);
        if (poduct == null) {
            System.out.println("没有找到该商品！");
        } else {
            System.out.println(poduct.toString());
        }
        //加入购物车并存储session
        Cart.add(new Items(1, poduct));
        cartMap.put(userid, Cart);
        req.getSession().setAttribute("productMap", cartMap);
        System.out.println("添加成功！");
        req.getRequestDispatcher("/front/gouwuche.jsp").forward(req, res);
    }

    /**
     * 将购物车中商品数量加  1
     * @param req
     * @param res
     * @throws IOException
     */
    private void addOne(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");

        PrintWriter out = res.getWriter();
        int index = Integer.parseInt(req.getParameter("index"));
        System.out.println(index);

        List <Items> Cart = new ArrayList <Items>();
        Map <Long, List> cartMap = new HashMap <Long, List>();

        Cart = (ArrayList <Items>) ((HashMap <Long, List>) req.getSession().getAttribute("productMap")).get(new Long(1));
        cartMap = ((HashMap <Long, List>) req.getSession().getAttribute("productMap"));
        Items temp = Cart.get(index);
        int fristNum = temp.getNum();
        int endNum = fristNum + 1;

        Cart.get(index).setNum(endNum);

        cartMap.put(new Long(1), Cart);

        req.getSession().setAttribute("productMap", cartMap);

        if (endNum - fristNum == 1) {
            out.print("ok");
        }
        out.flush();
        out.close();
    }


    /**
     * 将购物车中商品数量减 1
     * @param req
     * @param res
     * @throws IOException
     */
    private void subOne(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        int index = Integer.parseInt(req.getParameter("index"));
        System.out.println(index);

        List <Items> Cart = new ArrayList <Items>();
        Map <Long, List> cartMap = new HashMap <Long, List>();

        Cart = (ArrayList <Items>) ((HashMap <Long, List>) req.getSession().getAttribute("productMap")).get(new Long(1));
        cartMap = ((HashMap <Long, List>) req.getSession().getAttribute("productMap"));
        Items temp = Cart.get(index);
        int fristNum = temp.getNum();
        int endNum = fristNum - 1;

        Cart.get(index).setNum(endNum);

        cartMap.put(new Long(1), Cart);

        req.getSession().setAttribute("productMap", cartMap);

        if (endNum - fristNum == -1) {
            out.print("ok");
        }
        out.flush();
        out.close();
    }


    /**
     * 生成订单的操作
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    private void dingdan(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        System.out.println("run in dingdan........");
        //变量声明部分
        long userid = 1;
        CartDao dao = new CartDao();
        Order obj = new Order();
        boolean bol = false;

        //获取购物车中的Items集合对象
        List <Items> Cart = new ArrayList <Items>();
        Cart = (ArrayList <Items>) ((HashMap <Long, List>) req.getSession().getAttribute("productMap")).get(userid);
        //声明购物车Map集合
        Map <Long, List> cartMap = new HashMap <Long, List>();

        /*************************将选择好的生成订单***********************************************/
        //通过name属性将所有值放在values中（保存的是订单id）
        String[] values = req.getParameterValues("prochk");
        long pID = -1;
        long Pid = -2;
        if (values != null && values.length > 0) {
            //遍历选择框得到已经选择了的商品ID
            for (int j = 0; j < values.length; j++) {
                System.out.println(values[j]);
                Long.parseLong(values[j]);
                System.out.println(Long.parseLong(values[j]));
                pID = Long.parseLong(values[j]);

                //遍历购物车中所有商品信息，找出已经选择了的商品
                for (int i = 0; i < Cart.size(); i++) {
                    //得到每一个商品ID和选择了的ID做比较，相等就写入订单表
                    Pid = Cart.get(i).getProduct().getPid();
                    if (pID == Pid) {
                        double price = Cart.get(i).getProduct().getPprice();
                        int num = Cart.get(i).getNum();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                        String createdate = df.format(new Date());// new Date()为获取当前系统时间

                        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");//设置日期格式
                        String createdate1 = df1.format(new Date());// new Date()为获取当前系统时间

                        //将购物车数据封装成表单数据插入Order表中
                        obj.setOid(Pid);
                        obj.setOnum(createdate1 + Pid);
                        obj.setOstate(0);
                        obj.setOcreatetime(createdate);
                        obj.setOupdatetime(createdate);
                        System.out.println(obj.getOnum());//订单编号
                        //写入订单表中
                        bol = dao.insertOrders(obj);
                        if (bol) {
                            //消除已经提交的购物车购物车记录
                            Cart.remove(Cart.get(i));
                        } else {
                            System.out.println("提交订单失败！");
                        }
                    }
                }
            }
            cartMap.put(userid, Cart);
            req.getSession().setAttribute("productMap", cartMap);
            req.getRequestDispatcher("/front/gouwuche.jsp").forward(req, res);
        }
    }


    /**
     * 从购物车删除商品
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    private void delProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        System.out.println("run in delProduct........");
        //声明变量
        long userid = 1;


        //获取购物车中的Items集合对象
        List <Items> Cart = new ArrayList <Items>();
        Cart = (ArrayList <Items>) ((HashMap <Long, List>) req.getSession().getAttribute("productMap")).get(userid);
        //声明购物车Map集合
        Map <Long, List> cartMap = new HashMap <Long, List>();

        String method = req.getParameter("method");
        String temp = req.getParameter("index");

        long index = Long.parseLong(temp);
        long pid = -1;
        for (int i = 0; i <Cart.size() ; i++) {
            pid = Cart.get(i).getProduct().getPid();
            if(index==pid){
                Cart.remove(i);
            }
        }
        cartMap.put(userid,Cart);
        req.getSession().setAttribute("productMap", cartMap);
        req.getRequestDispatcher("/front/gouwuche.jsp").forward(req, res);
    }



    /**
     * 清空购物车
     * @param req
     * @param res
     * @throws IOException
     * @throws ServletException
     */
    private void clear(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        long userid = 1;

        Map <Long, List> cartMap = new HashMap <Long, List>();
        String method = req.getParameter("method");
        cartMap.remove(userid);
        req.getSession().setAttribute("productMap", cartMap);
        req.getSession().invalidate();
        req.getRequestDispatcher("/front/gouwuche.jsp").forward(req, res);
    }



    /**
     * 这个ajax分页的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
/*
    protected void listBlogsByAjaxPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        String page=request.getParameter("page");
        String count=request.getParameter("count");

        try {
            List<Blogs> bs=blogDAO.listBlogsByPage(Integer.parseInt(page),Integer.parseInt(count));
            //使用json项目提供当json。jar里面当api方法，将后台获取当java集合以及对象转成标准当json格式数据返回给前台

            JSONArray  allBlogsJson=new JSONArray();

            for(Blogs  b:bs){
                JSONObject jsonObj=new JSONObject();
                jsonObj.put("blogid",b.getBlogid());
                jsonObj.put("title",b.getTitle());
                jsonObj.put("content",b.getContent().length()>40?b.getContent().substring(0,40):b.getContent());
                jsonObj.put("publishTime",b.getPublishtime());
                jsonObj.put("visitedCount",b.getVisitedcount());
                jsonObj.put("image","images/201610181739277776.jpg");
                jsonObj.put("userimage",b.getUser().getImage());
                jsonObj.put("nickname",b.getUser().getNickname());
                jsonObj.put("userid",b.getUser().getUserid());

                allBlogsJson.put(jsonObj);

            }

            response.setContentType("text/json;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write( allBlogsJson.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

}
