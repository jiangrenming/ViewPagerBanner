package com.jrm.viewpagerbanner;

import android.content.ComponentName;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jiangrenming
 * @date 2018/4/12
 */

public class BannerPagerAdapter extends PagerAdapter{


    private  List<Integer> mImgs;
    private  Context mContext;
    private ArrayList<View> views = new ArrayList<>();


    public  BannerPagerAdapter(List<Integer> imgs, Context context){
        this.mImgs = imgs;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mImgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View  infalte = View.inflate(mContext, R.layout.item_layout, null);
        ImageView imageView = infalte.findViewById(R.id.iv_tip);
        imageView.setImageResource(mImgs.get(position));
        container.addView(infalte);
        views.add(position,infalte);
        return infalte;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
