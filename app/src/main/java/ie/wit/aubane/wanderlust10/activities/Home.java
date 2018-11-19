package ie.wit.aubane.wanderlust10.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ie.wit.aubane.wanderlust10.R;

public class Home extends BaseClass {

    private boolean isBackButtonPressed;
    private static final int SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                if(!isBackButtonPressed){
                    Home.this.startActivity(new Intent(Home.this, LogIn.class));
                }
            }
        }, SPLASH_DURATION);*/
        startActivity(new Intent(Home.this, LogIn.class));
    }

    @Override
    public void onBackPressed(){
        isBackButtonPressed = true;
    }
}
