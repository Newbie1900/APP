package com.register;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SQLiteReadActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        DBHelper helper = new DBHelper(SQLiteReadActivity.this);
        /*
        User user = helper.query(1);
        if(user!=null) {
            usernameTV.setText("用户名：" + user.getUsername());
            passwordTV.setText("密  码：" + user.getPassword());
        }else{
            Toast.makeText(this,"无结果",Toast.LENGTH_SHORT).show();
        }
        */
    }
}
