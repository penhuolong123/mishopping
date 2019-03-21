package com.oracle.mishopping.bean;


public class Order {

  private long oid;
  private String onum;
  private long ostate;
  private String ocreatetime;
  private String oupdatetime;


  public long getOid() {
    return oid;
  }

  public void setOid(long oid) {
    this.oid = oid;
  }


  public String getOnum() {
    return onum;
  }

  public void setOnum(String onum) {
    this.onum = onum;
  }


  public long getOstate() {
    return ostate;
  }

  public void setOstate(long ostate) {
    this.ostate = ostate;
  }


  public String getOcreatetime() {
    return ocreatetime;
  }

  public void setOcreatetime(String ocreatetime) {
    this.ocreatetime = ocreatetime;
  }


  public String getOupdatetime() {
    return oupdatetime;
  }

  public void setOupdatetime(String oupdatetime) {
    this.oupdatetime = oupdatetime;
  }

}
