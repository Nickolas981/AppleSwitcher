package com.dongumen.nickolas.appleswitcher;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.dongumen.nickolas.appleswitch.AppleSwitcher;

public class MainActivity extends AppCompatActivity {

    AppleSwitcher appleSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appleSwitcher = findViewById(R.id.apple);
        appleSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(MainActivity.this, "hello from main ", Toast.LENGTH_SHORT).show();
            }
        });
//        appleSwitcher.setColor(Color.parseColor("#888888"));
    }
}
