package com.oracle.mishopping.bean;


public class Address {

  private long aid;
  private long uid;
  private String addr;
  private String aphone;


  public long getAid() {
    return aid;
  }

  public void setAid(long aid) {
    this.aid = aid;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }


  public String getAphone() {
    return aphone;
  }

  public void setAphone(String aphone) {
    this.aphone = aphone;
  }

}
