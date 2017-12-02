package com.dongumen.nickolas.appleswitch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;


public class AppleSwitcher extends FrameLayout {

    private TextView first;
    private TextView second;

    private Switch aSwitch;
    private OnCheckedChangeListenerCollection listenerCollection;

    int mainColor = Color.parseColor("#666666");

    public AppleSwitcher(Context context) {
        super(context);
        init();
    }

    public AppleSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.apple_switcher, this);
        first = view.findViewById(R.id.first);
        second = view.findViewById(R.id.second);
        aSwitch = view.findViewById(R.id.mySwitch);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AppleSwitcher, 0, 0);
        try {
            first.setText(ta.getString(R.styleable.AppleSwitcher_textOff));
            second.setText(ta.getString(R.styleable.AppleSwitcher_textOn));
            mainColor = ta.getColor(R.styleable.AppleSwitcher_backColor, mainColor);
            first.setTextColor(mainColor);
        } finally {
            ta.recycle();
        }
        init();
    }


    public AppleSwitcher(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        listenerCollection = new OnCheckedChangeListenerCollection();
        refreshColor();
        aSwitch.setOnCheckedChangeListener(listenerCollection);
        listenerCollection.addOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    first.setTextColor(mainColor);
                    second.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                } else {
                    second.setTextColor(mainColor);
                    first.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                }
            }
        });
    }

    public void setChecked(boolean b){
        aSwitch.setChecked( b);
    }

    public boolean isChecked(){
        return aSwitch.isChecked();
    }

    public void setOnText(String s){
        second.setText(s);
    }

    public void setOffText(String s){
        first.setText(s);
    }

    public void setColor(int color){
        mainColor = color;
        refreshColor();
    }

    public void setColor(String color){
        setColor(Color.parseColor(color));
    }


    private void refreshColor(){
        aSwitch.getTrackDrawable().setColorFilter(mainColor, PorterDuff.Mode.SRC_IN);
        if (isChecked()){
            second.setTextColor(mainColor);
        }else{
            first.setTextColor(mainColor);
        }
    }


    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        listenerCollection.addOnCheckedChangeListener(listener);
    }

    private class OnCheckedChangeListenerCollection implements CompoundButton.OnCheckedChangeListener {
        private ArrayList<CompoundButton.OnCheckedChangeListener> listeners = new ArrayList<>();


        void addOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
            listeners.add(listener);
        }


        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            for (CompoundButton.OnCheckedChangeListener i : listeners) {
                i.onCheckedChanged(compoundButton, b);
            }
        }
    }
}
