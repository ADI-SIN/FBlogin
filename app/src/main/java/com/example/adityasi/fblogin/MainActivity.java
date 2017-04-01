package com.example.adityasi.fblogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    LoginButton log;
    TextView txt;
    CallbackManager callback;                                                               //as the name suggest

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());                             //this is to initialize fb sdk, it should be done before setContentView

        setContentView(R.layout.activity_main);

        log=(LoginButton)findViewById(R.id.login_button);
        txt=(TextView)findViewById(R.id.textView);
        callback=CallbackManager.Factory.create();                       //initialising callback manager

        log.registerCallback(callback, new FacebookCallback<LoginResult>() {                //operation on callback manager after login button
            @Override
            public void onSuccess(LoginResult loginResult) {                                             //FacebookCallback<LoginResult> this is the class which manages login activity
                txt.setText("Login Successful \n\n"+
                        loginResult.getAccessToken().getApplicationId()+
                        "\n\n"+loginResult.getAccessToken().getToken());                 //to display the id and token by calling loginresult
            }

            @Override
            public void onCancel() {
            txt.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {
            txt.setText("An error occured !");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callback.onActivityResult(requestCode,resultCode,data);                                 //pass the result of new activity to callback manager
    }
}
