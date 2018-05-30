package com.example.currentplacedetailsonmap;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivityloading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_activityloading);
        ActionBar action=getSupportActionBar();
        action.hide();
        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally{
                    Intent i=new Intent(MainActivityloading.this,signIn.class);
                    startActivity(i);
                }
            }
        };
        timer.start();
    }
}

