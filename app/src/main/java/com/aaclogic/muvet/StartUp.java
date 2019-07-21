package com.aaclogic.muvet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.login_user);

        /* which layout are we using? */
        setContentView(R.layout.splash_screen);



        //TODO figure out how to set up splash screen, then remove this button
        Button moveAheadButton = (Button) findViewById(R.id.btnTemp);
        moveAheadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StartUp.this, UserType.class);
                startActivity(myIntent);
            }
        });


    }//end onCreate
}
