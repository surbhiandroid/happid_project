package com.happid.app.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.happid.app.R;

public class EntermobilenoActivity extends AppCompatActivity {
    private CardView bottomcard;
    public EditText edittxt;
    private static int SPLASH_TIME_OUT = 2000;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entermobileno);
        finid();
        clicklistener();


    }

    private void clicklistener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bottomcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edittxt.getText().toString().equalsIgnoreCase(""))
                {
                    String substr=edittxt.getText().toString().substring(0,2);
                    String substr1=edittxt.getText().toString().substring(8,10);
                    callPopup(substr+substr1);
                }
                else
                {
                    edittxt.setError("Fill out this field");
                }


            }
        });



    }

    private void finid() {
        back=findViewById(R.id.back);
        bottomcard=findViewById(R.id.bottomcard);
        edittxt=findViewById(R.id.edittxt);
    }


    private void callPopup(String otp) {
        Dialog punchDialog = new Dialog(EntermobilenoActivity.this);
        punchDialog.setContentView(R.layout.cardpopup);
        punchDialog.setCancelable(true);
        punchDialog.setCanceledOnTouchOutside(false);
        CardView card=punchDialog.findViewById(R.id.card);
        EditText otp_textbox_one=punchDialog.findViewById(R.id.otp_edit_box1);
        EditText otp_textbox_two=punchDialog.findViewById(R.id.otp_edit_box2);
        EditText otp_textbox_three=punchDialog.findViewById(R.id.otp_edit_box3);
        EditText otp_textbox_four=punchDialog.findViewById(R.id.otp_edit_box4);
        otp_textbox_one.setText(otp.substring(0));
        otp_textbox_two.setText(otp.substring(1));
        otp_textbox_three.setText(otp.substring(2));
        otp_textbox_four.setText(otp.substring(3));

        punchDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        punchDialog.show();



     /*   card.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                punchDialog.dismiss();
                Intent i = new Intent(EntermobilenoActivity.this, EnterotpActivity.class);
                String otp=otp_textbox_one.getText().toString()+otp_textbox_two.getText().toString()+otp_textbox_three.getText().toString()
                        +otp_textbox_four.getText().toString();
                i.putExtra("no",edittxt.getText().toString());
                i.putExtra("otp",otp);

                startActivity(i);
                return true;
            }
        });*/

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                punchDialog.dismiss();
                Intent i = new Intent(EntermobilenoActivity.this, EnterotpActivity.class);
                String otp=otp_textbox_one.getText().toString()+otp_textbox_two.getText().toString()+otp_textbox_three.getText().toString()
                        +otp_textbox_four.getText().toString();
                i.putExtra("no",edittxt.getText().toString());
                i.putExtra("otp",otp);

                startActivity(i);

            }
        }, SPLASH_TIME_OUT);



    }

}