package com.oracle.mishopping.bean;


public class Products {

  private long pid;
  private String pname;
  private double pprice;
  private long pstock;
  private String pdes;
  private String pimg;
  private long pstate;
  private long categoryid;
  private double ppricediscount;

  public Products() {
  }

  public Products(long pid, String pname, double pprice, long pstock, String pdes, String pimg, long pstate, long categoryid, double ppricediscount) {
    this.pid = pid;
    this.pname = pname;
    this.pprice = pprice;
    this.pstock = pstock;
    this.pdes = pdes;
    this.pimg = pimg;
    this.pstate = pstate;
    this.categoryid = categoryid;
    this.ppricediscount = ppricediscount;
  }

  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }


  public double getPprice() {
    return pprice;
  }

  public void setPprice(double pprice) {
    this.pprice = pprice;
  }


  public long getPstock() {
    return pstock;
  }

  public void setPstock(long pstock) {
    this.pstock = pstock;
  }


  public String getPdes() {
    return pdes;
  }

  public void setPdes(String pdes) {
    this.pdes = pdes;
  }


  public String getPimg() {
    return pimg;
  }

  public void setPimg(String pimg) {
    this.pimg = pimg;
  }


  public long getPstate() {
    return pstate;
  }

  public void setPstate(long pstate) {
    this.pstate = pstate;
  }


  public long getCategoryid() {
    return categoryid;
  }

  public void setCategoryid(long categoryid) {
    this.categoryid = categoryid;
  }


  public double getPpricediscount() {
    return ppricediscount;
  }

  public void setPpricediscount(double ppricediscount) {
    this.ppricediscount = ppricediscount;
  }

  @Override
  public String toString() {
    return "Products{" +
            "pid=" + pid +
            ", pname='" + pname + '\'' +
            ", pprice=" + pprice +
            ", pstock=" + pstock +
            ", pdes='" + pdes + '\'' +
            ", pimg='" + pimg + '\'' +
            ", pstate=" + pstate +
            ", categoryid=" + categoryid +
            ", ppricediscount=" + ppricediscount +
            '}';
  }
}
