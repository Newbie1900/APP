package com.register;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class addActivity extends Activity implements View.OnClickListener{
    private EditText ET_add_sno=null, ET_add_sname=null, ET_add_class=null;
    private Button btn_add_up=null, btn_add_sure=null, btn_add_cancel=null;
    private DBHelper helper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        ET_add_sno = (EditText) findViewById(R.id.ET_add_sno);
        ET_add_sname = (EditText) findViewById(R.id.ET_add_sname);
        ET_add_class = (EditText) findViewById(R.id.ET_add_class);
        btn_add_up = (Button) findViewById(R.id.btn_add_up);
        btn_add_sure = (Button) findViewById(R.id.btn_add_sure);
        btn_add_cancel = (Button) findViewById(R.id.btn_add_cancel);
        btn_add_up.setOnClickListener(this);
        btn_add_sure.setOnClickListener(this);
        btn_add_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_add_up:

                break;
            case R.id.btn_add_sure:
                Student student = new Student();
                if(ET_add_sno.getText()!=null&&ET_add_sname.getText()!=null&&ET_add_class.getText()!=null){
                    student.setSno(ET_add_sno.getText().toString());
                    student.setSname(ET_add_sname.getText().toString());
                    student.setClass(ET_add_class.getText().toString());
                    if(helper.insert(student)) {
                        finish();//hint
                    }else{
                        Toast.makeText(addActivity.this,"添加失败！",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(addActivity.this,"学号，姓名，班级不能为空！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_add_cancel:
                finish();
                break;
        }
    }
}
