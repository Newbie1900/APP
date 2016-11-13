package com.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ThemedSpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.register.R.array.classtime;
import java.lang.Thread.UncaughtExceptionHandler;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    DBHelper helper;
    private Spinner spinnerCT = null;
    private Spinner spinnerC = null;
    private ListView lst_sno = null;
    private RadioGroup radioGroup = null;
    private RadioButton rb_zaiqin = null, rb_chidao = null, rb_zaotui = null, rb_kuangke = null,
            rb_bingjia = null;

    public static EditText ET_Sno = null;
    public static EditText ET_Sname = null;
    public static EditText ET_Class = null;

    private Button btn_sure = null;
    private Button btn_add = null;
    private Button btn_del = null;
    private Button btn_update = null;

    private static int SPINNERCT=0;//记录选中的是第几课时
    private static String SPINNERC=null;//记录选中的班级名称
    private static String LISTSNO=null;//记录选中的学号
    private static String RADIOBUTTONTXT=null;//记录选中的考勤情况

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);
        //Thread.setDefaultUncaughtExceptionHandler(this);/////
        helper = new DBHelper(this);
        spinnerCT = (Spinner) findViewById(R.id.spinnerclasstime);
        spinnerC = (Spinner) findViewById(R.id.spinnerclass);
        lst_sno = (ListView) findViewById(R.id.lst_sno);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb_zaiqin = (RadioButton) findViewById(R.id.rb_zaiqin);
        rb_chidao = (RadioButton) findViewById(R.id.rb_chidao);
        rb_zaotui = (RadioButton) findViewById(R.id.rb_zaotui);
        rb_kuangke = (RadioButton) findViewById(R.id.rb_kaungke);
        rb_bingjia = (RadioButton) findViewById(R.id.rb_bingjia);
        ET_Sno = (EditText) findViewById(R.id.ET_sno);
        ET_Sname = (EditText) findViewById(R.id.ET_sname);
        ET_Class = (EditText) findViewById(R.id.ET_class);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        btn_update = (Button) findViewById(R.id.btn_update);
        setSpinnerCT();
        setSpinnerC();
        //setListSno();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rb_zaiqin.getId() == checkedId) {
                    RADIOBUTTONTXT = rb_zaiqin.getText().toString();
                } else if (rb_chidao.getId() == checkedId) {
                    RADIOBUTTONTXT = rb_chidao.getText().toString();
                } else if (rb_zaotui.getId() == checkedId) {
                    RADIOBUTTONTXT = rb_zaotui.getText().toString();
                } else if (rb_kuangke.getId() == checkedId) {
                    RADIOBUTTONTXT = rb_kuangke.getText().toString();
                } else {
                    RADIOBUTTONTXT = rb_bingjia.getText().toString();
                }
            }
        });
        btn_sure.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_update.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                helper.sure(LISTSNO,SPINNERCT,RADIOBUTTONTXT);
                break;
            case R.id.btn_add:
                Intent intenta=new Intent();
                intenta.setClass(MainActivity.this, addActivity.class);
                startActivity(intenta);
                break;
            case R.id.btn_update:
                Student student = helper.queryBySno(ET_Sno.getText().toString());
                Intent intentu=new Intent();
                intentu.setClass(MainActivity.this, updateActivity.class);
                intentu.putExtra("sno",student.getSno());
                intentu.putExtra("sname",student.getSname());
                intentu.putExtra("Class",student.getClass());//读取出错
                startActivity(intentu);
                break;
            case R.id.btn_del:
                new AlertDialog.Builder(this).setTitle("选择删除方式：").setSingleChoiceItems(
                        new String[]{"删除当前学生","指定学号删除","指定姓名删除"},-1,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which) {
                                switch(which){
                                    case 0: new AlertDialog.Builder(MainActivity.this).setTitle("确认").setMessage("确定吗？")
                                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    helper.delete(ET_Sno.getText().toString());
                                                }
                                            }).setNegativeButton("否",null).show();
                                        break;
                                    case 1: final EditText et = new EditText(MainActivity.this);
                                            new AlertDialog.Builder(MainActivity.this).setTitle("请输入学号")
                                            .setView(et)
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    helper.delete(et.getText().toString());
                                                }
                                            })
                                            .setNegativeButton("取消",null).show();
                                        break;
                                    case 2:final EditText et2 = new EditText(MainActivity.this);
                                        new AlertDialog.Builder(MainActivity.this).setTitle("请输入姓名")
                                                .setView(et2)
                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        helper.deleteBySname(et2.getText().toString());
                                                    }
                                                })
                                                .setNegativeButton("取消",null).show();
                                        break;
                                }
                            }
                        }).setPositiveButton("确定",null).setNegativeButton("取消",null).show();
                break;
        }
    }
    public void setSpinnerCT(){
        List<String> listCT = new ArrayList<String>();
        String[] ct = this.getResources().getStringArray(classtime);
//        String[] ct = {"第一课时", "第二课时", "第三课时", "第四课时", "第五课时", "第六课时", "第七课时"
//                , "第八课时", "第九课时", "第十课时", "第十一课时", "第十二课时", "第十三课时", "第十四课时"
//                , "第十五课时", "第十六课时", "第十七课时", "第十八课时", "第十九课时", "第二十课时"};
        int count = helper.countClassTime();
        if(count!=0) {
            for (int i = 0; i < count; i++) {
                listCT.add(ct[i]);
            }

        ArrayAdapter adapterCT = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listCT);
//        ArrayAdapter adapterCT = new ArrayAdapter(this,R.layout.item,R.id.textViewId,list);
        //上下文对象，每个条目样式，指定textViewId,提供数据

//        ArrayAdapter<CharSequence> adapterCT = ArrayAdapter.createFromResource(
//                this,R.array.classtime,android.R.layout.simple_spinner_item);//上下文对象，string数组，指定spinner样式
//        adapterCT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//设置条目样式
        spinnerCT.setAdapter(adapterCT);
        spinnerCT.setPrompt("课时列表");
        spinnerCT.setOnItemSelectedListener(new SpinnerOnSelectedListener());
        SPINNERCT = spinnerCT.getSelectedItemPosition()+1;////
        }else{
            Toast.makeText(this,"课时数读取出错！",Toast.LENGTH_SHORT).show();
        }
    }
    public void setSpinnerC() {
        List<String> listC = new ArrayList<String>();
        String[] c = helper.queryClass();
        if (c != null) {
            for (int i = 0; i < c.length; i++) {
                listC.add(c[i]);
            }
            ArrayAdapter adapterC = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listC);
            spinnerC.setAdapter(adapterC);
            spinnerC.setPrompt("行政班级");
            spinnerC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //SPINNERC = parent.getItemAtPosition(position).toString();
                    setListSno();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else{
            Toast.makeText(this,"班级读取出错！",Toast.LENGTH_SHORT).show();///
        }
    }
    public void setListSno(){
        SPINNERC = spinnerC.getSelectedItem().toString();
        List<Integer> listsno = new ArrayList<Integer>();
        String[] c = helper.querySnoByClass(SPINNERC);/////////////////////////
        if (c != null) {
            for (int i = 0; i < c.length; i++) {
                listsno.add(Integer.getInteger(c[i]));
            }
            ArrayAdapter adapterSno = new ArrayAdapter(this,
                   R.layout.support_simple_spinner_dropdown_item,listsno);
            /////以下内容取消注释就出错，空指针异常
//            lst_sno.setAdapter(adapterSno);
//            lst_sno.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    String s = parent.getItemAtPosition(position).toString();
//                    Student student = helper.queryBySno(s);
//                    if(student !=null){
//                        MainActivity.ET_Sno.setText(student.getSno());
//                        MainActivity.ET_Sname.setText(student.getSname());
//                        MainActivity.ET_Class.setText(student.getclass());
//                    }else{
//                        System.out.println(s);
//                    }
//            }
//            });
        }else{
            Toast.makeText(this,"学号读取出错！",Toast.LENGTH_SHORT).show();
        }
//        lst_sno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

//    @Override
//    public void uncaughtException(Thread t, Throwable e) {
//        Log.i("AAA", "uncaughtException   " + e);
//    }
}
