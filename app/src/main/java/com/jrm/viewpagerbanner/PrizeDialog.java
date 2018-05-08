package com.jrm.viewpagerbanner;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 *
 * @author jiangrenming
 * @date 2018/4/12
 */

public class PrizeDialog extends Dialog implements View.OnClickListener {


    private Context mContext;
    private View prizeView;
    private Button comfirm;

    public PrizeDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public PrizeDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        prizeView = View.inflate(mContext, R.layout.prize_dialog, null);
        setContentView(prizeView);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER|Gravity.CENTER_VERTICAL;
        dialogWindow.setAttributes(lp);
        initView();
        initListener();
    }

    private void initListener() {
        comfirm.setOnClickListener(this);
    }

    private void initView() {
        comfirm = prizeView.findViewById(R.id.comfirm);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.dismiss();
        return super.onTouchEvent(event);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comfirm:
                if (isShowing()){
                    this.dismiss();
                }
                break;
            default:
                break;
        }
    }
}
