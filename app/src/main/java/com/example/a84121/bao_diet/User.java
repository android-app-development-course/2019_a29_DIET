package com.example.a84121.bao_diet;



public class User {
    private String name;
    private boolean isSelect;

    public User(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public boolean isSelect(){
        return isSelect;
    }
    public void setSelect(boolean select){
        isSelect=select;
    }
}
