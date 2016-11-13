package com.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SQLiteWriteActivity extends Activity {
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
                User user = new User(username, password);
                DBHelper helper = new DBHelper(SQLiteWriteActivity.this);
                //helper.insert(user);

                Intent intent = new Intent();
                intent.setClass(SQLiteWriteActivity.this, SQLiteReadActivity.class);
                startActivity(intent);
            }
        });

    }
}
