package com.abc.app.memberapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public MemberDAO(Context context) {

        super(context, "hanbitdb", null, 1);
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
                + PHONE + " text "
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
        String sql = "insert into member(id,pw,name,reg_date,ssn_id,email,profile_img,phone) "
                + "values(?,?,?,?,?,?,?,?)";
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
    public List<MemberBean> list() {
        String sql = "select * from member";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        List<MemberBean> tempList = new ArrayList<MemberBean>();
        return tempList;
    }

    // findByPK
    public MemberBean findById(String pk) {
        String sql = "select * from member where id = ?";
        MemberBean tempBean = null;
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        return tempBean;
    }

    // findByName
    public List<?> findByName(String name) {
        String sql = "select * from member where name = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        List<MemberBean> tempList = new ArrayList<MemberBean>();
        return tempList;
    }

    // count
    public int count() {
        int result = 0;
        String sql = "select count(*) count from member ";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        return result;
    }

    public boolean login(MemberBean param) {
        boolean loginOk = false;
        if (param.getId() != null
                && param.getPw() != null
                && this.existId(param.getId())) {
            MemberBean member = this.findById(param.getId());
            if (member.getPw().equals(param.getPw())) {
                loginOk = true;
            }
        }
        return loginOk;
    }

    public boolean existId(String id) {
        boolean existOK = false;
        String sql = "select count(*) as count from member where id = ?";
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        if (result == 1) {
            existOK = true;
        }
        return existOK;
    }
}
