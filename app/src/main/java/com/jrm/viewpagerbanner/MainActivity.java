package com.jrm.viewpagerbanner;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * @author jiangrenming
 */
public class MainActivity extends Activity implements View.OnClickListener{

   private  Integer [] imgs = {R.mipmap.home_banner_daikuan,R.mipmap.home_banner_dingdanlaile,R.mipmap.home_banner_qiankeduo,
                                    R.mipmap.home_banner_xingguanjia,R.mipmap.home_banner_yingqianyun};
    private  Integer [] nomarls = {R.mipmap.home_smallbanner_n,R.mipmap.home_smallbanner_me,R.mipmap.home_smallbanner_me_two};
    private  Integer [] product_imgs = {R.mipmap.home_icon_fenqi,R.mipmap.home_icon_daikuan,R.mipmap.home_icon_yingyongshangdian
            ,R.mipmap.home_icon_shouyingtai,R.mipmap.home_icon_xingguanjia,R.mipmap.home_icon_yingqianyun,R.mipmap.home_icon_qiankeduo};
    private  String [] products_names = {"星POS分期","星POS贷款","应用商店","收银台","星管家","赢钱云点餐","星POS钱客多"};
    private ArrayList<Integer> bananers = new ArrayList<>();
    private ArrayList<Integer> nomral_bananers = new ArrayList<>();
    private  ArrayList<ProductBean> products_list = new ArrayList<>();
    private MZBannerView bananViews;
    private  MZBannerView nomral_banner;
    private GridView product_img;
    private  ProductsAdapter adapter;
    private RelativeLayout prize;
    private PrizeDialog  prizeDiolog;
    private  ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bananViews = (MZBannerView)findViewById(R.id.banner);
        nomral_banner = (MZBannerView)findViewById(R.id.nomral_banner);
        product_img = (GridView)findViewById(R.id.product_img);
        prize = (RelativeLayout)findViewById(R.id.prize);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        prize.setOnClickListener(this);
        initData();
        setAnims();
    }

    private void initData() {
        for (int i =0; i<imgs.length ; i++){
            bananers.add(imgs[i]);
        }
        bananViews.setIndicatorVisible(true);
        // 代码中更改indicator 的位置
        bananViews.setIndicatorAlign(MZBannerView.IndicatorAlign.BOTTOM);
        bananViews.setIndicatorPadding(10,0,0,10);
        bananViews.setDelayedTime(3000);
        bananViews.setIndicatorRes(R.drawable.unseletc_dot,R.drawable.select_dot);
        bananViews.setPages(bananers, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        bananViews.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Log.i("点击的位置","Tag="+position);
            }
        });

       for (int i =0;i< nomarls.length ;i++){
           nomral_bananers.add(nomarls[i]);
       }
        nomral_banner.setIndicatorVisible(true);
        nomral_banner.setIndicatorRes(R.drawable.unseletc_dot,R.drawable.select_dot);
        nomral_banner.setIndicatorAlign(MZBannerView.IndicatorAlign.BOTTOM);
        nomral_banner.setIndicatorPadding(10,0,0,10);
        nomral_banner.setDelayedTime(5000);
        nomral_banner.setPages(nomral_bananers, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new NomralBannerViewHolder();
            }
        });
        for (int i =0 ;i < product_imgs.length ;i++){
            ProductBean products = new ProductBean();
            products.setProduct_img(product_imgs[i]);
            products.setProduct_name(products_names[i]);
            products_list.add(products);
        }
        adapter = new ProductsAdapter(this,products_list);
        product_img.setAdapter(adapter);
        product_img.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("点击产品列表位置=",position+"");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prize:
                //抽奖按钮
                prizeDiolog = new PrizeDialog(MainActivity.this,R.style.MidDialogStyle);
                prizeDiolog.show();
              break;
            default:
                break;
        }
    }

    private class BannerViewHolder implements MZViewHolder<Integer>{
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.item_layout,null);
            mImageView = (ImageView) view.findViewById(R.id.iv_tip);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            mImageView.setImageResource(data);
        }
    }

    private class NomralBannerViewHolder implements MZViewHolder<Integer>{
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.nomral_item,null);
            mImageView = (ImageView) view.findViewById(R.id.nomral_img);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            mImageView.setImageResource(data);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        bananViews.pause();
        nomral_banner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        bananViews.start();
        nomral_banner.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( null != prizeDiolog && prizeDiolog.isShowing()){
            prizeDiolog.dismiss();
        }
    }

    private  void setAnims(){
        Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.img_romate);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        imageView2.setAnimation(operatingAnim);

    }
}
