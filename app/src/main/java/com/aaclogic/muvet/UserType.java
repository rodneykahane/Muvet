package com.aaclogic.muvet;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*** which layout are we using? ***/
        setContentView(R.layout.register_user_type);
        Button userButton = (Button) findViewById(R.id.btnRegisterUserTypeUser);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(UserType.this,SignInRegister.class);
                startActivity(myIntent);
            }
        });
    }
}
