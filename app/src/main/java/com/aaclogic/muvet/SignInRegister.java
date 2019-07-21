package com.aaclogic.muvet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* which layout are we using? */
        setContentView(R.layout.new_user_user);

        //setting up 'Sign up with email' button
        Button signUpWithEmail = (Button) findViewById(R.id.btnSignUpEmail);
        signUpWithEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailSignUpIntent = new Intent(SignInRegister.this,AddUserUser.class);
                startActivity(emailSignUpIntent);
            }
        });

        //setting up 'Already have an account?' button
        Button hasAccount = (Button) findViewById(R.id.btnAlreadyHaveAccount);
        hasAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent haveAccountIntent = new Intent(SignInRegister.this,LoginUser.class);
                startActivity(haveAccountIntent);
            }
        });

        //TODO set up button for Facebook

    }
}
