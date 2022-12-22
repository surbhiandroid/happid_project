package com.happid.app.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.happid.app.R;
import com.happid.app.Utils.GenericTextWatcher;

public class EnterotpActivity extends AppCompatActivity {
    private TextView notxt;
    private CardView bottomcard;
    private String otp="",no="";
    private EditText otp_textbox_one,otp_textbox_two,otp_textbox_three,otp_textbox_four;
    private ImageView back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterotp);
        if(getIntent().getExtras()!=null)
        {
           otp=getIntent().getStringExtra("otp");
           no=getIntent().getStringExtra("no");

        }
        finid();
        setvalue();
        clicklistenre();
    }

    private void setvalue() {
        notxt.setText(no);
        EditText[] edit = {otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four};

        otp_textbox_one.addTextChangedListener(new GenericTextWatcher(otp_textbox_one, edit));
        otp_textbox_two.addTextChangedListener(new GenericTextWatcher(otp_textbox_two, edit));
        otp_textbox_three.addTextChangedListener(new GenericTextWatcher(otp_textbox_three, edit));
        otp_textbox_four.addTextChangedListener(new GenericTextWatcher(otp_textbox_four, edit));
    }

    private void clicklistenre() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bottomcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otp.equalsIgnoreCase((otp_textbox_one.getText().toString()+otp_textbox_two.getText().toString()+otp_textbox_three.getText().toString()
                        +otp_textbox_four.getText().toString())))
                {
                    Intent i = new Intent(EnterotpActivity.this, ProfileScreenActivity.class);
                    i.putExtra("no",no);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(EnterotpActivity.this, "Invalid Otp", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void finid() {
        back=findViewById(R.id.back);
        otp_textbox_one = findViewById(R.id.otp_edit_box1);
        otp_textbox_two = findViewById(R.id.otp_edit_box2);
        otp_textbox_three = findViewById(R.id.otp_edit_box3);
        otp_textbox_four = findViewById(R.id.otp_edit_box4);
        bottomcard=findViewById(R.id.bottomcard);
        notxt=findViewById(R.id.notxt);
    }
}