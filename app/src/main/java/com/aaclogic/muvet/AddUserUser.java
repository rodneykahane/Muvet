package com.aaclogic.muvet;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddUserUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* which layout are we using? */
        setContentView(R.layout.add_user);

        //setting up back button
        Button addUserBack = findViewById(R.id.btnAddUserBack);
        addUserBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addUserBackIntent = new Intent(AddUserUser.this, SignInRegister.class);
                startActivity(addUserBackIntent);
            }
        });

        //TODO add terms & conditions button

        //TODO add code to see if car is on file
        Button signUp = findViewById(R.id.btnUserSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpUserIntent = new Intent(AddUserUser.this, AddUserCar.class);
                startActivity(signUpUserIntent);
            }
        });

        //TODO change imageView to an imageButton and code for adding image from file or camera

        //TODO handle firstname field

        //TODO handle lastname field

        //TODO handle email field

        //TODO handle password field

        //TODO handle confirm field


    }
}
