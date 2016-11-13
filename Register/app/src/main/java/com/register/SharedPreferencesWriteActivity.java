package com.register;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SharedPreferencesWriteActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);
        final EditText usernameET =(EditText)  findViewById(R.id.username);
        final EditText passwordET =(EditText)  findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                /*
                User user = new User(username, password);
                DBHelper helper = new DBHelper(SQLiteWriteActivity.this);
                helper.insert(user);
                 */

                SharedPreferences sp = getSharedPreferences("mrsoft", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.commit();

                Intent intent = new Intent();
                intent.setClass(SharedPreferencesWriteActivity.this, SharedPreferencesReadActivity.class);
                startActivity(intent);
            }
        });

    }
}
