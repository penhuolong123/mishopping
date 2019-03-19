package com.sample;


public class Products {

  private long pid;
  private String pname;
  private double pprice;
  private long pstock;
  private String pdes;
  private String pimg;
  private long pstate;
  private long catagoryid;
  private double ppricediscount;


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


  public long getCatagoryid() {
    return catagoryid;
  }

  public void setCatagoryid(long catagoryid) {
    this.catagoryid = catagoryid;
  }


  public double getPpricediscount() {
    return ppricediscount;
  }

  public void setPpricediscount(double ppricediscount) {
    this.ppricediscount = ppricediscount;
  }

}
