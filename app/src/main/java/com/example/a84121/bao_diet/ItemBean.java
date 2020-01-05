package com.example.a84121.bao_diet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黎活宝 on 2019/11/26.
 */

public class ItemBean {
    String product_name;
    int ImgId;
    String product_price;
    public ItemBean(String product_name,int ImgId,String product_price){
        this.product_name=product_name;
        this.ImgId=ImgId;
        this.product_price=product_price;
    }

    public void setName(String product_name){
        this.product_name=product_name;
    }

    public void setImgId(int ImgId){
        this.ImgId=ImgId;
    }

    public void setProduct_price(String product_price){
        this.product_price=product_price;
    }

    public String getProduct_name(){
        return product_name;
    }

    public int getImgId(){
        return ImgId;
    }

    public String getProduct_price(){
        return product_price;
    }

    public static List<ItemBean> getList(){
        List<ItemBean> ItemList=new ArrayList<>();
        String product_name[]={
                "饱腹代餐粥","天农鸡胸肉400g","番茄340g","精选鲜鸡蛋10枚",
                "麦维他消化饼干 250g","河源山地鸡500g","低脂牛肉棒"
        };
        int ImgId[]={
          R.drawable.full_porridge,R.drawable.chicken_breast,R.drawable.tomato,
                R.drawable.egg,R.drawable.biscuit,R.drawable.chicken,R.drawable.beef_stick
        };

        String Product_Price[]={
                "￥59.9/盒","￥14.9/盒","￥4.5/份","￥11.8/盒",
                "￥13.5/袋","￥23.8/盒","￥39.9/5条"
        };
        ItemBean itemBean[]=new ItemBean[product_name.length];

        //为ItemList列表添加元素
        for(int i=0;i<product_name.length;i++){
            itemBean[i]=new ItemBean(product_name[i],ImgId[i],Product_Price[i]);
            ItemList.add(itemBean[i]);
        }
        return ItemList;
    }


}
