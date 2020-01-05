package com.example.a84121.bao_diet;

/**
 * Created by 黎活宝 on 2019/12/15.
 */

public class FruitsAndVeg {
    private String name;
    private String Cal;
    private String Fat;
    private String Prot;
    private String Carb;
    private boolean isSelect;

    public FruitsAndVeg(String name, String Cal, String Fat, String Prot, String Carb){
        this.name=name;
        this.Cal=Cal;
        this.Fat=Fat;
        this.Prot=Prot;
        this.Carb=Carb;
    }
    public void setName(String name){this.name=name;}
    public void setCal(String Cal){this.Cal=Cal;}
    public void setFat(String Fat){this.Fat=Fat;}
    public void setProt(String Prot){this.Prot=Prot;}
    public void setCarb(String Carb){this.Carb=Carb;}
    public String getName(){return name;}
    public String getCal(){return Cal;}
    public String getFat(){return Fat;}
    public String getProt(){return Prot;}
    public String getCarb(){return Carb;}
    public boolean isSelect(){
        return isSelect;
    }
    public void setSelect(boolean select){
        isSelect=select;
    }
}
