package com.register;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Blob;

import static android.widget.Toast.LENGTH_SHORT;

public class DBHelper {
    private static final String DATABASE_NAME = "datastorage";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "checking_in";//考勤表
    private static final String Sno = "sno";//学号
    private static final String Sname = "sname";//姓名
    private static final String Class = "class";//行政班级
    private static final String Records_1 = "records_1", Records_2 = "records_2",
            Records_3 = "records_3", Records_4 = "records_4", Records_5 = "records_5",
            Records_6 = "records_6", Records_7 = "records_7", Records_8 = "records_8",
            Records_9 = "records_9", Records_10 = "records_10", Records_11 = "records_11",
            Records_12 = "records_12", Records_13 = "records_13", Records_14 = "records_14",
            Records_15 = "records_15", Records_16 = "records_16", Records_17 = "records_17",
            Records_18 = "records_18";//考勤记录，学时
    private static final String Portrait = "portrait";//肖像Blob;


    private DBOpenHelper helper;
    private SQLiteDatabase db;

    private static class DBOpenHelper extends SQLiteOpenHelper{
        private static final String CREATE_TABLE = "create table "+TABLE_NAME+" ( "+Sno+
                " text primary key, "+Sname+" text not null, "+Class+" text not null, "
                +Portrait+" Blob, "+Records_1+" text, "+Records_2+" text, "+Records_3+" text, "
                +Records_4+" text, "+Records_5+" text, "+Records_6+" text, "
                +Records_7+" text, "+Records_8+" text, "+Records_9+" text, "+Records_10+" text, "
                +Records_11+" text, "+Records_12+" text, "+Records_13+" text, "+Records_14+" text, "
                +Records_15+" text, "+Records_16+" text, "+Records_17+" text, "+Records_18+" text );";
        public DBOpenHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists "+ TABLE_NAME);
            onCreate(db);
        }
    }

    public DBHelper(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
        try{
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010102','莫天金','计科一班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010103','吴国平','计科一班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010104','孙文斌','计科一班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010105','潘俊旭','计科一班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010106','石佳磊','计科一班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010201','肖霞','计科二班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010203','郑剑锋','计科二班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010206','程飘','计科二班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010207','王浩','计科二班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010220','邓强','计科二班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010301','李佳佳','计科三班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010303','何友鹏','计科三班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010306','郭耕左','计科三班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010307','杨飘','计科三班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010308','李中清','计科三班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010401','刘翠芳','计科四班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010402','李杰','计科四班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010404','杨林','计科四班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010405','刘佳','计科四班');");
            db.execSQL("insert into "+TABLE_NAME+" (sno,sname,class) values('631406010409','董刚','计科四班');");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean delete(String Sno){                      //按学号删除
        Student student = queryBySno(Sno);
        if(student==null){                                  //删除失败，该学号不存在
            return false;
        }else{
            db.execSQL("delete from "+TABLE_NAME+" where sno = '"+Sno+"';");
            return true;
        }
    }
    public boolean deleteBySname(String Sname){                      //按姓名删除
        Student student = queryBySname(Sname);
        if(student==null){                                  //删除失败，该姓名不存在
            return false;
        }else{
            db.execSQL("delete from "+TABLE_NAME+" where sname = '"+Sname+"';");
            return true;
        }
    }
    public boolean sure(String Sno, int pos, String register){
        String sql = "update "+TABLE_NAME+" set records_"+pos+" = '"+register+"' where "+DBHelper.Sno
                +" = '"+Sno+"';";
        db.execSQL(sql);
        return true;
    }
    public boolean insert(Student student){                 //插入学生信息
        Student student1 = queryBySno(student.getSno());
        if(student1 == null) {
            ContentValues values = new ContentValues();
            values.put(Sno, student.getSno());
            values.put(Sname, student.getSname());
            values.put(Class, student.getclass());
            //values.put(Portrait,student.getPortrait());
            values.put(Records_1, student.getRecords()[0]);
            values.put(Records_2, student.getRecords()[1]);
            values.put(Records_3, student.getRecords()[2]);
            values.put(Records_4, student.getRecords()[3]);
            values.put(Records_5, student.getRecords()[4]);
            values.put(Records_6, student.getRecords()[5]);
            values.put(Records_7, student.getRecords()[6]);
            values.put(Records_8, student.getRecords()[7]);
            values.put(Records_9, student.getRecords()[8]);
            values.put(Records_10, student.getRecords()[9]);
            values.put(Records_11, student.getRecords()[10]);
            values.put(Records_12, student.getRecords()[11]);
            values.put(Records_13, student.getRecords()[12]);
            values.put(Records_14, student.getRecords()[13]);
            values.put(Records_15, student.getRecords()[14]);
            values.put(Records_16, student.getRecords()[15]);
            values.put(Records_17, student.getRecords()[16]);
            values.put(Records_18, student.getRecords()[17]);
            db.insert(TABLE_NAME, null, values);
            return true;
        }else{
            return false;
        }
    }
    public boolean update(Student student) {
        Student student1 = queryBySno(student.getSno());
        if (student1 == null) {
            return false;
        } else {
//            student1.setSno(student.getSno());
//            student1.setSname(student.getSname());
//            student1.setClass(student.getclass());
            db.execSQL("update " + TABLE_NAME + " set " + Sno + " = '" + student.getSno()
                    + "', " + Sname + " = '" + student.getSname() + "', " + Class + " = '" + student.getclass()
                    + "' where " + Sno + " = '" + student.getSno()+"';");
            return true;
        }
    }
    public int countClassTime(){        //课时计数
        Cursor cursor = db.rawQuery("PRAGMA table_info("+TABLE_NAME+")",null);
        int count = 0;
        while(cursor.moveToNext()){
            count++;
        }
        if(count>4){        //Sno,Sname,class,portrait,Records_1....
            count = count-4;
        }else {
            count = 0;
        }
        cursor.close();
        return count;       //课时数
    }
    public String[] queryClass(){//返回行政班级名称
        String[] Class;
        Cursor cursor = db.rawQuery("select distinct "+DBHelper.Class+" from "+TABLE_NAME
                +" order by "+DBHelper.Class+" asc;",null);
        int count = 0;
        while(cursor.moveToNext()){
            count++;
        }
        if(cursor.moveToFirst()){
            Class = new String[count];//班级名无顺序
            for(int i=0;i<count;i++){
                Class[i] = cursor.getString(0);
                cursor.moveToNext();
            }
            cursor.close();
        }else{
            Class = null;
        }
        return Class;
    }

    public String[] querySnoByClass(String Class){//根据行政班级查该班学号
        String[] Sno;
        Cursor cursor = db.rawQuery("select "+DBHelper.Sno+" from "+TABLE_NAME+" where "
                +DBHelper.Class+" = '"+Class+"';",null);
        int count = 0;
        while(cursor.moveToNext()){
            count++;
        }
        if(cursor.moveToFirst()){
            Sno = new String[count];//班级名无顺序
            for(int i=0;i<count;i++){
                Sno[i] = cursor.getString(0);
                cursor.moveToNext();
            }
            cursor.close();
        }else{
            Sno = null;
        }
        return Sno;
    }
    public Student queryBySno(String sno){
        Student student;
        String[] records = new String[18];
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where sno = '"+sno+"' ",null);
        if(cursor.moveToFirst()) {
            student = new Student();
            student.setSno(cursor.getString(0)); //获取第一列的值,第一列的索引从0开始
            student.setSname(cursor.getString(1));//获取第二列的值
            student.setClass(cursor.getString(2));
            for (int i = 0; i < 18; i++) {
                records[i] = cursor.getString(i + 4);
            }
            student.setRecords(records);
            cursor.close();
        }else{
            student = null;
        }
        return student;
    }
    public Student queryBySname(String Sname){
        Student student;
        String[] records = new String[18];
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where sname = "+Sname+" ",null);
        if(cursor.moveToFirst()) {
            student = new Student();
            student.setSno(cursor.getString(0)); //获取第一列的值,第一列的索引从0开始
            student.setSname(cursor.getString(1));//获取第二列的值
            student.setClass(cursor.getString(2));
            for (int i = 0; i < 18; i++) {
                records[i] = cursor.getString(i + 3);
            }
            student.setRecords(records);
            cursor.close();
        }else{
            student = null;
        }
        return student;
    }
}
/*
public class DBHelper {
    private static final String DATABASE_NAME = "datastorage";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String ID = "_id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    private static class DBOpenHelper extends SQLiteOpenHelper{
        private static final String CREATE_TABLE = "create table"+TABLE_NAME+"("+ID+
                "integer p rimary key autoincrement, "+USERNAME+" text not null, "+PASSWORD+
                " text not null);";
        public DBOpenHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists "+ TABLE_NAME);
            onCreate(db);
        }
    }

    public DBHelper(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    public void insert(User user){
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        db.insert(TABLE_NAME, null, values);
    }
    public User query(int id){
        User user = new User();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ USERNAME, PASSWORD },
                "_id = "+ id, null, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            user.setUsername(cursor.getString(0));
            user.setPassword(cursor.getString(1));
            cursor.close();
            return user;
        }
        cursor.close();
        return null;
    }
}
 */