package com.jrm.viewpagerbanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *
 * @author jiangrenming
 * @date 2018/4/12
 */

public class ProductsAdapter extends BaseAdapter{


    private Context mContext;
    private ArrayList<ProductBean> mProducts;

    public  ProductsAdapter(Context context,ArrayList<ProductBean> products){
        this.mContext = context;
        this.mProducts = products;
    }

    @Override
    public int getCount() {
        return mProducts !=null ? mProducts.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductViewHolder viewHolder = null;
        if (convertView == null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.product_item,null);
            viewHolder = new ProductViewHolder();
            viewHolder.product_name=(TextView)convertView.findViewById(R.id.product_name);
            viewHolder.prduct_img=(ImageView) convertView.findViewById(R.id.product_img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ProductViewHolder) convertView.getTag();
        }
        ProductBean productBean = mProducts.get(position);
        viewHolder.product_name.setText(productBean.getProduct_name());
        viewHolder.prduct_img.setImageResource(productBean.getProduct_img());
        return convertView;
    }


    public  class  ProductViewHolder {
        private TextView product_name;
        private ImageView prduct_img;
    }
}
