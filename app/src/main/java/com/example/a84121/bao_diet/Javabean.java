package com.example.a84121.bao_diet;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by 84121 on 2019/11/23.
 */

public class Javabean implements Serializable {
        /**
         * 名字
         */
        private String name;
        /**
         * 图片id
         */
        private int imageId;
        private int touxiang;
        private String user;
        public  int like;
        public  int collect;

        public Javabean(String name, int imageId,int touxiang,String user) {
            this.name = name;
            this.imageId = imageId;
            this.touxiang=touxiang;
            this.user=user;
            like=0;
        }

        public String output(){
            String image=String.valueOf(imageId);
            String tou=String.valueOf(touxiang);
            return name+","+user+","+image+","+tou;
        }
        public String getName() {
            return name;
        }
        public String getUser() {
        return user;
    }
        public int getImageId() {
        return imageId;
    }
        public int getTouxiang() {
            return touxiang;
        }
        public int getLike() {
        return like;
    }
        public void addLike() {like+=1;}
        public void addCollect() {collect+=1;}
        public int getCollect() {
        return collect;
    }
    }