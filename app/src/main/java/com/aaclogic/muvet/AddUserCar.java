package com.aaclogic.muvet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddUserCar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*  which layout are we using?*/
        setContentView(R.layout.new_user_car);

        //TODO change imageView to imageButton

        //TODO handle car field

        //TODO handle phone field

        //TODO 'complete sign up' button

        Button addUserCarBack = findViewById(R.id.btnAddCarBack);
        addUserCarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addUserCarBack = new Intent(AddUserCar.this, AddUserUser.class);
                startActivity(addUserCarBack);
            }
        });
    }
}
