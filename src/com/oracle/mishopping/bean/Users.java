package com.oracle.mishopping.bean;


public class Users {

  private long uid;
  private String uname;
  private String upassword;
  private String nickname;
  private String uphone;
  private long ustate;


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }


  public String getUpassword() {
    return upassword;
  }

  public void setUpassword(String upassword) {
    this.upassword = upassword;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getUphone() {
    return uphone;
  }

  public void setUphone(String uphone) {
    this.uphone = uphone;
  }


  public long getUstate() {
    return ustate;
  }

  public void setUstate(long ustate) {
    this.ustate = ustate;
  }

}
