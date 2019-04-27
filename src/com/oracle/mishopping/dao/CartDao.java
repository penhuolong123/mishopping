package com.oracle.mishopping.dao;

import com.oracle.mishopping.bean.Products;
import com.oracle.mishopping.bean.Order;
import com.oracle.mishopping.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
    /**
     * 查找全部商品
     *
     * @return
     */
    public List <Products> allProducts() {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        List <Products> list = new ArrayList <>();
        try {
            String sql = "select * from products";
            conn = DBUtil.getConnection();
            pre = conn.prepareStatement(sql);
            res = pre.executeQuery(sql);
            while (res.next()) {
                long pid = res.getLong(1);
                String pname = res.getString(2);
                double pprice = res.getDouble(3);
                long pstock = res.getLong(4);
                String pdes = res.getString(5);
                String pimg = res.getString(6);
                long pstate = res.getLong(7);
                long categoryid = res.getLong(8);
                double ppricediscount = res.getDouble(9);
                Products product = new Products(pid, pname, pprice, pstock, pdes, pimg, pstate, categoryid, ppricediscount);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }


    /**
     * 通过id查找商品
     *
     * @param id
     * @return
     */
    public Products getProductById(Long id) {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        Products products = null;
        try {
            String sql = "select * from products where pid=?";
            conn = DBUtil.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setLong(1, id);
            res = pre.executeQuery();
           while (res.next()){
               long pid = res.getLong(1);
               String pname = res.getString(2);
               double pprice = res.getDouble(3);
               long pstock = res.getLong(4);
               String pdes = res.getString(5);
               String pimg = res.getString(6);
               long pstate = res.getLong(7);
               long categoryid = res.getLong(8);
               double ppricediscount = res.getDouble(9);
               products  = new Products(pid, pname, pprice, pstock, pdes, pimg, pstate, categoryid, ppricediscount);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return products;
    }

    /**
     * 写入订单表
     *
     * @param obj
     * @return
     */
    public boolean insertOrders(Order obj) {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        Order order = new Order();

        Long oid = obj.getOid();
        String onnum = obj.getOnum();
        Long ostate = obj.getOstate();
        String orearetime = obj.getOcreatetime();
        String oupdatetime = obj.getOupdatetime();
        try {
            String sql = "insert into Orders values(?,?,?,?,?,?);";
            conn = DBUtil.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setLong(1, oid);
            pre.setString(2, onnum);
            pre.setLong(3, ostate);
            pre.setString(4, orearetime);
            pre.setString(5, oupdatetime);
            pre.setInt(6,1);

            int i = pre.executeUpdate();
            if(i>0){
                System.out.println("seccfull..........");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        }
        return false;
    }
}
