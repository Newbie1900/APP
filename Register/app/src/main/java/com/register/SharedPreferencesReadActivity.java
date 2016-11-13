package com.register;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SharedPreferencesReadActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        SharedPreferences sp = getSharedPreferences("mrsoft", MODE_PRIVATE);
        String username = sp.getString("username", "mr");
        String password = sp.getString("password", "001");
        usernameTV.setText("用户名："+username);
        passwordTV.setText("密  码："+password);
    }
}
