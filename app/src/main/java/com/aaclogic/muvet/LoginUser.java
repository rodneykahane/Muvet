package com.aaclogic.muvet;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /* which layout are we using?*/
        setContentView(R.layout.login_user);

        //TODO add code for forgot password button

        //setting up 'log in' button
        Button loginButton = findViewById(R.id.login_register_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginUser = new Intent(LoginUser.this, MainMap.class);
                startActivity(loginUser);
            }
        });

        //TODO add code for email field

        //TODO add code for password field
    }
}
