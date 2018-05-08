package com.jrm.viewpagerbanner;

import java.io.Serializable;

/**
 * Created by jiangrenming on 2018/4/12.
 */

public class ProductBean implements Serializable{
    private String product_name;
    private int product_img;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_img() {
        return product_img;
    }

    public void setProduct_img(int product_img) {
        this.product_img = product_img;
    }
}
