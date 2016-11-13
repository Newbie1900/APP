package com.register;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class User {
    private int id;                                     //保存用户的id
    private String username;                            //保存用户名
    private String password;                            //保存密码
    public User(){
    }
    public User(String  username, String password){
        this.username = username;
        this.password = password;
    }
    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
