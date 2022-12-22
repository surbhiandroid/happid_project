package com.happid.app.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.happid.app.R;

public class WalkthroughActivity extends AppCompatActivity {
    private CardView bottomcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
        bottomcard=findViewById(R.id.bottomcard);
        bottomcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WalkthroughActivity.this,EntermobilenoActivity.class);
                startActivity(intent);

            }
        });
    }
}