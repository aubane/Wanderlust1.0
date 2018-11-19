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

public class Register extends BaseClass {

    Button register_button;
    TextView log_in_link;
    private boolean isBackButtonPressed = false;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_button = findViewById(R.id.register_button);
        log_in_link = findViewById(R.id.log_in_link);

        settings = getSharedPreferences("loginPrefs",0);
    }

    public void registerButtonClicked(View view){
        Log.v("Wanderlust", "Register Button clicked");
        CharSequence email = ((TextView)findViewById(R.id.sign_up_email)).getText();
        CharSequence username = ((TextView)findViewById(R.id.sign_up_username)).getText();
        CharSequence password = ((TextView)findViewById(R.id.sign_up_password)).getText();

        if(email.length() <=0 || username.length() <= 0 || password.length() <=0){
            Toast.makeText(this, "Please enter an e-mail, username and password to register.", Toast.LENGTH_LONG).show();
        } else if(!isBackButtonPressed){
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("loggedIn", true);
            editor.putString("email", email.toString());
            editor.putString("username", username.toString());
            editor.putString("password", password.toString());
            editor.commit();
            startActivity(new Intent(Register.this, MyTrips.class));
            finish();
        }

    }

    public void logInLinkClicked(View view){
        Log.v("Wanderlust", "Log in Link clicked");
        startActivity(new Intent(this, LogIn.class));
        finish();
    }

    @Override
    public void onBackPressed(){
        isBackButtonPressed = true;
    }
}
