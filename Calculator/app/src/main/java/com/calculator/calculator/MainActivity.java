package com.calculator.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Stack;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_0;//数字按钮0
    Button btn_1;//数字按钮1
    Button btn_2;//数字按钮2
    Button btn_3;//数字按钮3
    Button btn_4;//数字按钮4
    Button btn_5;//数字按钮5
    Button btn_6;//数字按钮6
    Button btn_7;//数字按钮7
    Button btn_8;//数字按钮8
    Button btn_9;//数字按钮9
    Button btn_point;//小数点按钮
    Button btn_plus;//加好按钮
    Button btn_sub;//减号按钮
    Button btn_mul;//乘号按钮
    Button btn_divide;//除号按钮
    Button btn_equal;//等号按钮
    Button btn_del;//删除按钮
    Button btn_allclear;//清除按钮
    EditText ex_input;//显示输入内容的显示屏
    boolean clear_flag;//清空标识
    //容错标志
    boolean flag_0 = false;//限制数字开头的0
    boolean flag_point = false;//限制数字小数点个数1
    boolean flag_operator = false;//限制操作符数量1，连续输入运算符标志
    boolean flag_num = false;//数字输入开始

    Stack stk_point = new Stack();//记录小数点的输入状态，del时不重复输入容错
    Stack stk_Length = new Stack();//记录每一个数的长度(包括小数点)
    int numLength = 0;//数字长度，初始0；
    Stack stk_operator = new Stack();//顺序运算符栈
    Stack stk_num = new Stack();//顺序数字栈
    Stack stk_midop = new Stack();//逆序运算符栈
    Stack stk_midnum = new Stack();//逆序数字栈




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化按钮
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1= (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_allclear = (Button) findViewById(R.id.btn_allclear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        //实例化输入框
        ex_input = (EditText) findViewById(R.id.ex_input);
        //设置按钮的点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_allclear.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String str = ex_input.getText().toString();
        switch (view.getId()){
            case R.id.btn_0:
                if(clear_flag){                                     //输入前清空结果
                    clear_flag = false;
                    str="";
                    ex_input.setText("");
                }
                if(flag_0 == true){                                 //0输入受限
                    return;
                }
                if(flag_num == false){                              //数字输入未开始
                    flag_0 = true;                                  //限制0的个数1
                    /*
                    if(ex_input.getText().toString().endsWith("0")){
                        return;
                    }else {}*/
                        numLength++;
                        ex_input.setText(str + ((Button) view).getText());//若先输入0，整数部分会以一个0开头

                }else{                                              //数字输入开始
                    if(flag_0 == true){flag_0 = false;}             //若有限制，则取消
                }
                if(flag_0 == false){                                //0输入不受限
                    ex_input.setText(str+((Button)view).getText());
                    numLength++;
                }
                break;

            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                if(clear_flag){
                    clear_flag = false;
                    str="";
                    ex_input.setText("");
                }
                if(flag_num == false){
                    flag_num = true;                                //数字输入开始
                    flag_0 = false;                                 //不限制0的输入
                    flag_operator = false;                          //置连续输入运算符为false，无运算符输入
                }
                ex_input.setText(str+((Button)view).getText());
                numLength++;
                break;

            case R.id.btn_point:
                if(clear_flag){
                    clear_flag = false;
                    str="";
                    ex_input.setText("");
                }
                if(flag_point == true){                             //已有小数点，限制输入
                    return;
                }else{                                              //无小数点可以输入
                   // stk_point.push(true);                           //记录小数点输入状态
                    flag_point = true;                              //已有一个小数点
                    if(flag_num == false){                          //数字输入可以开始
                        flag_num = true;
                        flag_0 = false;
                        flag_operator = false;
                    }
                    /*
                    if(numLength==0){
                        ex_input.setText(str+"0"+((Button)view).getText());
                        numLength++;
                    }else {

                    }*/
                    ex_input.setText(str + ((Button) view).getText());
                    numLength++;
                }
                break;

            case R.id.btn_plus:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_divide:
                if(clear_flag){
                    clear_flag = false;
                    str="";
                    ex_input.setText("");
                }
                if(flag_operator == false){                                 //无运算符输入
                    flag_operator = true;                                   //已输入运算符
                    flag_num = false;                                       //数字输入结束
                    if(flag_point == false){                                //数字输入无小数点时也记录状态
                        stk_point.push(false);
                    }else {                                                 //
                        stk_point.push(true);
                        flag_point = false;
                    }
                    stk_Length.push(numLength);
                    numLength = 0;
                    ex_input.setText(str+" "+((Button)view).getText()+" ");////
                }else{                                                      //已有运算符输入
                    ex_input.setText(str.substring(0,str.length()-3)+" "+((Button)view).getText()+" ");//取代上次输入的运算符
                }
                break;

            case R.id.btn_del:
                if(clear_flag){
                    clear_flag = false;
                    str="";
                    ex_input.setText("");
                }else if(str!=null&&!str.equals("")){                           //
                    if(flag_num == true) {                                      //数据输入未结束
                        if(numLength>0) {
                            if (str.endsWith(".")) {
                                flag_point = false;
                            }
                            numLength--;
                            ex_input.setText(str.substring(0, str.length() - 1));
                            if(numLength==0){
                                flag_num = false;
                                flag_0 = false;
                                flag_operator = true;//
                            }
                        }
                    }else{                                                      //已输入运算符
                        ex_input.setText(str.substring(0, str.length() - 3));
                        flag_num = true;                                        //返回上一个状态，可以输入数据
                        flag_point = (boolean)stk_point.pop();                  //小数点返回上一个记录
                        flag_operator = false;
                        numLength = (int)stk_Length.pop();
                    }
                }
                break;

            case R.id.btn_allclear:
                flag_num = false;
               //flag_0 = false;
                flag_operator = false;
                flag_point = false;
                while(!stk_Length.isEmpty()){
                    stk_Length.pop();
                }
                numLength = 0;
                while(!stk_point.empty()){                                      //清除小数点所有记录
                    stk_point.pop();
                }
                clear_flag = false;
                str="";
                ex_input.setText("");
                break;

            case R.id.btn_equal:
                if(flag_num == true) {                                          //若以运算符结尾，不予计算
                    flag_num = false;
                    flag_operator = false;
                    flag_point = false;  //
                    while(!stk_Length.isEmpty()){
                        stk_Length.pop();
                    }
                    numLength = 0;
                    while (!stk_point.empty()) {                                      //清除小数点所有记录
                        stk_point.pop();
                    }
                    try {
                        getResult();
                    }catch (Exception e){
                        Toast warning = Toast.makeText(this,"表达式输入非法",Toast.LENGTH_LONG);
                        warning.show();
                        str="";
                        ex_input.setText("");
                    }

                }
                break;
        }
    }
    private void getResult(){
        String exp = ex_input.getText().toString();
        if(exp==null||exp.equals("")){
            return;
        }
        if(!exp.contains(" ")){
            return;
        }
        if(clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag = true;
        double num1, num2, num3;
        double result = 0;
         while(!exp.isEmpty()) {                                //表达式分类进栈
            double d1;
            if (exp.contains(" ")) {                            //表达式包含操作符
                if(exp.startsWith(" ")){                        //表达式以运算符开头
                    stk_midnum.push(result);                    //result=0;push(0);
                    String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);//运算符
                    stk_midop.push(op);
                    exp = exp.substring(exp.indexOf(" ") + 3, exp.length());
                }else {
                    String s1 = exp.substring(0, exp.indexOf(" "));//运算符前面的字符串
                    String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);//运算符
                    //String s2 = exp.substring(exp.indexOf(" ")+3);//运算符后面的字符串
                    //if(!s1.equals("")&&!s2.equals("")){
                    d1 = Double.parseDouble(s1);
                    stk_midnum.push(d1);
                    stk_midop.push(op);
                    exp = exp.substring(exp.indexOf(" ") + 3, exp.length());
                }
            } else {                                            //表达式不含运算符
                d1 = Double.parseDouble(exp);
                stk_midnum.push(d1);
                exp = "";
            }
        }
        //逆序，达到“先进先出”的效果
        while(!stk_midnum.isEmpty()) {
            stk_num.push(stk_midnum.pop());
        }
        stk_operator.push(null);//到 底 标志
        while(!stk_midop.isEmpty()) {
            stk_operator.push(stk_midop.pop());
        }
        String op1, op2;
        while(!stk_operator.isEmpty()) {
            if(stk_operator.peek()!=null){                  //至少还有一个运算符
                op1 = (String) stk_operator.pop();
                op2 = (String) stk_operator.pop();          //可能为null
                if(op2!=null){                              //至少2个运算符!op2.equals(null) null与""
                    if(op2.equals("+")||op2.equals("-")){   //第二个运算符为低优先级，先计算第一个运算符
                        num1 = (double) stk_num.pop();
                        num2 = (double) stk_num.pop();
                        result = operator(num1,num2,op1);
                        stk_num.push(result);
                        stk_operator.push(op2);
                    }else if(op1.equals("×")||op1.equals("÷")){//第二个运算符是高优先级，若第一运算符也为高优先级，先计算
                        num1 = (double) stk_num.pop();
                        num2 = (double) stk_num.pop();
                        result = operator(num1,num2,op1);
                        stk_num.push(result);
                        stk_operator.push(op2);
                    }else{                                      //第二高优先级，第一低优先级
                        num1 = (double) stk_num.pop();
                        num2 = (double) stk_num.pop();
                        num3 = (double) stk_num.pop();
                        result = operator(num2,num3,op2);
                        stk_num.push(result);
                        stk_num.push(num1);
                        stk_operator.push(op1);
                    }
                }else{                                                      //只剩一个运算符
                    num1 = (double) stk_num.pop();
                    num2 = (double) stk_num.pop();
                    result = operator(num1,num2,op1);
                }
            }else{                                                          //无运算符
                stk_operator.pop();
            }

        }
        ex_input.setText(result+"");                                        //显示结果
    }
    private double operator(double n1, double n2, String s) {   //BigDecimal解决精度问题
        if (s.equals("+")){
            BigDecimal param = new BigDecimal(Double.toString(n1));
            BigDecimal result = new BigDecimal(Double.toString(n2));
            return result.add(param).doubleValue();
        }else if(s.equals("-")){
            BigDecimal param = new BigDecimal(Double.toString(n2));
            BigDecimal result = new BigDecimal(Double.toString(n1));
            return result.subtract(param).doubleValue();
        }else if(s.equals("×")){
            BigDecimal param = new BigDecimal(Double.toString(n1));
            BigDecimal result = new BigDecimal(Double.toString(n2));
            return result.multiply(param).doubleValue();
        }else{//if(s.equals("÷"))
            if(n2!=0){
                BigDecimal param = new BigDecimal(Double.toString(n2));
                BigDecimal result = new BigDecimal(Double.toString(n1));
                try {
                    return result.divide(param).doubleValue();
                }catch (ArithmeticException e){
                    return result.divide(param,3,BigDecimal.ROUND_HALF_EVEN).doubleValue();
                }
            }else {
                Toast toast = Toast.makeText(this,"错误：除数为0",Toast.LENGTH_SHORT);
                toast.show();
                return 0;   //除数为0
            }
        }
    }
}
