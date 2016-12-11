package com.example.administrator.viewflipperdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private int[] img={R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5};
    private GestureDetector gd=null;
    private ViewFlipper viewf=null;
    private static final int mindistance=100;
    private static final int minvelocity=200;
    private Activity mac=null;
    private Animation rin,rout,lin,lout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mac=this;
        viewf=(ViewFlipper)findViewById(R.id.viewf);
        gd=new GestureDetector(this,this);
        rin= AnimationUtils.loadAnimation(mac,R.anim.rightin);
        rout=AnimationUtils.loadAnimation(mac,R.anim.rightout);
        lin=AnimationUtils.loadAnimation(mac,R.anim.leftin);
        lout=AnimationUtils.loadAnimation(mac,R.anim.leftout);
        for(int i=0;i<img.length;i++){
            ImageView imgv=new ImageView(this);
            imgv.setImageResource(img[i]);
            imgv.setScaleType(ImageView.ScaleType.FIT_XY);
            viewf.addView(imgv,1,new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        }
        viewf.setAutoStart(true);
        viewf.setFlipInterval(2000);
        viewf.startFlipping();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewf.stopFlipping();
        viewf.setAutoStart(false);
        return gd.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e2.getX()-e1.getX()>mindistance&&Math.abs(velocityX)>minvelocity){
            viewf.setInAnimation(lin);
            viewf.setOutAnimation(rout);
            viewf.showPrevious();
            setTitle("num:"+viewf.getDisplayedChild());
            return true;
        }else if(e1.getX()-e2.getX()>mindistance&&Math.abs(velocityX)>minvelocity){
            viewf.setInAnimation(rin);
            viewf.setOutAnimation(lout);
            viewf.showNext();
            setTitle("num"+viewf.getDisplayedChild());
            return true;
        }
        return true;
    }
}
