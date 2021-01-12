/*
 * Copyright (C) 2015 Eeshan Jamal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.user.housechore;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView optionList;
    private int mPhoneHeight;
    private  String mFamily;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.login);
//        Button login=(Button)findViewById(R.id.loginButton);
//
//        login.setOnClickListener(this);
//
//
//    }
public static final String PREFS_NAME = "LoginPrefs";
    private Context activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//    setContentView(R.layout.main);
        setContentView(R.layout.login);
        Intent i=getIntent();
        mPhoneHeight= (int) this.getSharedPreferences("setting",0).getFloat(Constants.ARG_PHONE_HEIGHT,0);
        Log.d("Tablayout", "onCreate: PHONE_Height"+mPhoneHeight);
        mFamily=this.getSharedPreferences("family",0).getString("family",null);
        Log.d("family", "onCreate: "+mFamily);

//        /*
//         * Check if we successfully logged in before.
//         * If we did, redirect to home page
//         */
//        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//        if (settings.getString("logged", "").toString().equals("logged")) {
////        Intent intent = new Intent(LoginActivity.this, Home.class);
//            Intent intent = new Intent(LoginActivity.this, TabLayoutActivity.class);
//            startActivity(intent);
//        }


        Button b = (Button) findViewById(R.id.loginButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditText username = (EditText) findViewById(R.id.emailInput);
                EditText password = (EditText) findViewById(R.id.passwordInput);
                    if (password.getText().toString().equals((""+mPhoneHeight))) {
                        Intent intent = new Intent(LoginActivity.this, TabLayoutActivity.class);
                        startActivity(intent);
                    }


//                if (username.getText().toString().length() > 0 && password.getText().toString().length() > 0) {
//                    if (username.getText().toString().equals("test") && password.getText().toString().equals((""+mPhoneHeight))) {
//                        /*
//						 * So login information is correct,
//						 * we will save the Preference data
//						 * and redirect to next class / home
//						 */
//                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//                        SharedPreferences.Editor editor = settings.edit();
//                        editor.putString("logged", "logged");
//                        editor.commit();
//
//                        Intent intent = new Intent(LoginActivity.this, TabLayoutActivity.class);
//                        startActivity(intent);
//                    }
//                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, TabLayoutActivity.class);
        startActivity(i);
    }



}
