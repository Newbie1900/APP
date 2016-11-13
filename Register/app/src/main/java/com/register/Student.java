package com.register;


import java.sql.Blob;

public class Student {
    private String Sno;//学号
    private String Sname;//姓名
    private String Class;//班级
    private Blob Portrait;//肖像
    private String[] Records;//考勤记录
    public Student(){
        Records = new String[18];//18个记录
    }
    public void setSno(String Sno){
        this.Sno = Sno;
    }
    public String getSno(){
        return Sno;
    }
    public void setSname(String Sname){
        this.Sname = Sname;
    }
    public String getSname(){
        return Sname;
    }
    public void setClass(String Class){
        this.Class = Class;
    }
    public String getclass(){
        return Class;
    }
    public void setPortrait(Blob portrait){
        this.Portrait = portrait;
    }
    public Blob getPortrait(){
        return Portrait;
    }
    public void setRecords(String[] Records){
        for(int i = 0;i<18;i++){
            this.Records[i]=Records[i];
        }
    }
    public String[] getRecords(){
        return Records;
    }
}
