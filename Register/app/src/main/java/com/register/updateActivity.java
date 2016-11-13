package com.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateActivity extends Activity implements View.OnClickListener{
    private EditText ET_update_sno=null, ET_update_sname=null, ET_update_class=null;
    private Button btn_update_etsno=null, btn_update_etsname=null, btn_update_etclass=null,
                    btn_update_sure=null, btn_update_cancel=null;
    DBHelper helper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        Intent intent = getIntent();
        String sno = intent.getStringExtra("sno");
        String sname = intent.getStringExtra("sname");
        String Class = intent.getStringExtra("Class");
        ET_update_sno = (EditText) findViewById(R.id.ET_update_sno);
        ET_update_sname = (EditText) findViewById(R.id.ET_update_sname);
        ET_update_class = (EditText) findViewById(R.id.ET_update_class);
        ET_update_sno.setText(sno);
        ET_update_sname.setText(sname);
        ET_update_class.setText(Class);
        btn_update_etsno = (Button) findViewById(R.id.btn_update_etsno);
        btn_update_etsname = (Button) findViewById(R.id.btn_update_etsname);
        btn_update_etclass = (Button) findViewById(R.id.btn_update_etclass);
        btn_update_sure = (Button) findViewById(R.id.btn_update_sure);
        btn_update_cancel = (Button) findViewById(R.id.btn_update_cancel);
        btn_update_etsno.setOnClickListener(this);
        btn_update_etsname.setOnClickListener(this);
        btn_update_etclass.setOnClickListener(this);
        btn_update_sure.setOnClickListener(this);
        btn_update_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_update_etsno:
                ET_update_sno.setClickable(true);
                ET_update_sno.setEnabled(true);
      //          ET_update_sno.setTextIsSelectable(true);
                ET_update_sno.setFocusable(true);
                ET_update_sno.setSelectAllOnFocus(true);
                //ET_update_sno.selectAll();
                break;
            case R.id.btn_update_etsname:
                ET_update_sname.setClickable(true);
                ET_update_sname.setEnabled(true);
                ET_update_sname.setFocusable(true);
                ET_update_sname.setSelectAllOnFocus(true);
                //ET_update_sname.setTextIsSelectable(true);
                //ET_update_sname.selectAll();
                break;
            case R.id.btn_update_etclass:
                ET_update_class.setClickable(true);
                ET_update_class.setEnabled(true);
                ET_update_class.setFocusable(true);
                ET_update_class.setSelectAllOnFocus(true);
                //ET_update_class.selectAll();
                break;
            case R.id.btn_update_sure:
                Student student = new Student();
                if(ET_update_sno.getText()!=null&&ET_update_sno.getText().toString()!=""&&
                        ET_update_sname.getText()!=null&&ET_update_sname.getText().toString()!=""&&
                ET_update_class.getText()!=null&&ET_update_class.getText().toString()!="") {
                    student.setSno(ET_update_sno.getText().toString());
                    student.setSname(ET_update_sname.getText().toString());
                    student.setClass(ET_update_class.getText().toString());
                    if (helper.update(student)) {
                        finish();
                    } else {
                        //Toast.makeText(updateActivity.this,"更新失败！",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //Toast.makeText(updateActivity.this,"输入框不能为空！",Toast.LENGTH_SHORT).show();
                }
                //finish();
                break;
            case R.id.btn_update_cancel:
                finish();
                break;
        }
    }
}
