package ie.wit.aubane.wanderlust10.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ie.wit.aubane.wanderlust10.R;

public class LogIn extends BaseClass {

    Button log_in_button;
    TextView register_link;
    private boolean isBackButtonPressed = false;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        settings = getSharedPreferences("loginPrefs", 0);
        if(settings.getBoolean("loggedIn", false)){
            startHomeScreen();
        }

        log_in_button = findViewById(R.id.log_in_button);
        register_link = findViewById(R.id.register_link);

    }

    private void startHomeScreen(){
        startActivity(new Intent(this, MyTrips.class));
        finish();
    }

    public void registerLinkClicked(View view){
        Log.v("Wanderlust", "Register Link clicked");
        startActivity(new Intent(this, Register.class));
    }

    public void logInButtonClicked(View view){
        Log.v("Wanderlust", "Log In Button clicked");
        CharSequence username = ((TextView)findViewById(R.id.sign_in_username)).getText();
        CharSequence password = ((TextView)findViewById(R.id.sign_in_password)).getText();

        if(username.length() <= 0 || password.length() <=0){
            Toast.makeText(this, "Please enter your username and password to log in.", Toast.LENGTH_LONG).show();
        } else if(!isBackButtonPressed){
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("loggedIn", true);
            editor.commit();
            startActivity(new Intent(this, MyTrips.class));
            finish();
        }
    }

    @Override
    public void onBackPressed(){
        isBackButtonPressed = true;
    }
}
