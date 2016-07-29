package com.abc.app.memberapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckan on 2016-07-27.
 */
public class MemberDAO extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "member";
    public static final String ID = "id";
    public static final String PW = "pw";
    public static final String NAME = "name";
    public static final String SSN = "ssn_id";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String PROFILE = "profile";

    public MemberDAO(Context context) {
        super(context, "hanbitdb", null, 1);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists "
                + TABLE_NAME
                + " ( "
                + ID + " text primary key, "
                + PW + " text, "
                + NAME + " text, "
                + SSN + " text, "
                + EMAIL + " text, "
                + PHONE + " text, "
                + PROFILE + " text "
                + " ); ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "drop table if exists "+TABLE_NAME;
        db.execSQL("sql");
        this.onCreate(db);
    }

    public int insert(MemberBean stu) {
        int result = 0;
        String sql = "insert into "+TABLE_NAME+
                     String.format(" (%s,%s,%s,%s,%s,%s,%s) ",ID,PW,NAME,SSN,EMAIL,PHONE,PROFILE)+
                     String.format(" values('%s','%s','%s','%s','%s','%s','%s' ); "
                             ,stu.getId()
                             ,stu.getPw()
                             ,stu.getName()
                             ,stu.getSsn()
                             ,stu.getEmail()
                             ,stu.getPhone()
                             ,stu.getProfile()
                     );
        Log.d("DAO ID : "+stu.getId(),"=== ID insert ====");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        return result;
    }

    public int update(MemberBean stu) {
        int result = 0;
        String sql = "update member "
                + "set pw = ?, "
                + "email = NVL(?,email), "
                + "phone = ? "
                + "where id = ? ";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        return result;
    }

    public int delete(String id) {
        int result = 0;
        String sql = "delete member "
                + " where id = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        return result;
    }

    // list
    public ArrayList<MemberBean> list() {
        String sql = "select "
            + String.format(" %s,%s,%s,%s,%s,%s,%s ",ID,PW,NAME,SSN,EMAIL,PHONE,PROFILE)
            +" from "+TABLE_NAME+";";
        ArrayList<MemberBean> tempList = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor != null){
            Log.d("DAO List","목록조회 성공 !!");
            cursor.moveToFirst();
        }
        do {
            MemberBean temp = new MemberBean();
            temp.setId(cursor.getString(0));
            temp.setPw(cursor.getString(1));
            temp.setName(cursor.getString(2));
            temp.setSsn(cursor.getString(3));
            temp.setEmail(cursor.getString(4));
            temp.setPhone(cursor.getString(5));
            temp.setProfile(cursor.getString(6));
            tempList.add(temp);
        }
        while(cursor.moveToNext());
        return tempList;
    }

    // findByPK
    public MemberBean findById(String pk) {
        String sql = "select "
                     +String.format(" %s,%s,%s,%s,%s,%s,%s ",ID,PW,NAME,SSN,EMAIL,PHONE,PROFILE)
                     +String.format(" from "+TABLE_NAME+" where id = %s ;",pk);
        MemberBean tempBean = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor != null){
            Log.d("DAO FIND_BY_ID ","ID 조회 성공 !!");
            if(cursor.moveToFirst()){
                tempBean = new MemberBean();
                tempBean.setId(cursor.getString(0));
                tempBean.setPw(cursor.getString(1));
                tempBean.setName(cursor.getString(2));
                tempBean.setSsn(cursor.getString(3));
                tempBean.setEmail(cursor.getString(4));
                tempBean.setPhone(cursor.getString(5));
                tempBean.setProfile(cursor.getString(6));
            }

        }

        return tempBean;
    }

    // findByName
    public List<?> findByName(String name) {
        String sql = "select * from member where name = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        List<MemberBean> tempList = new ArrayList<MemberBean>();
        if(cursor.moveToFirst()){

        }
        return tempList;
    }

    // count
    public int count() {
        int result = 0;
        String sql = "select count(*) count from member ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return result;
    }

    public boolean login(MemberBean param) {
        boolean loginOk = false;
        String sql = "select "+PW+" from "+TABLE_NAME+
                String.format(" where id = '%s' ; ",param.getId());
        SQLiteDatabase db = this.getReadableDatabase();
        String pw="";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            pw=cursor.getString(0);
        }
        if(pw.equals("")){
            Log.d("DAO 로그인 결과 : ","일치하는 ID가 없음");
            Log.d("DAO ID : ",ID);
        }else{
            Log.d("DAO ID : ",param.getId());
            Log.d("DAO PW : ",pw);
            loginOk = (pw.equals(param.getPw()))?true:false;
        }
        System.out.println("Login OK "+loginOk);
        return loginOk;
    }

    public boolean existId(String id) {
        boolean existOK = false;
        String sql = "select count(*) as count from member where id = ?";
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (result == 1) {
            existOK = true;
        }
        return existOK;
    }
}
