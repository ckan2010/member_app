package com.abc.app.memberapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckan on 2016-07-27.
 */
public class MemberDAO extends SQLiteOpenHelper{

    public MemberDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public int insert(MemberBean stu){
        int result = 0;
        String sql = "insert into member(id,pw,name,reg_date,ssn_id,email,profile_img,phone) "
                + "values(?,?,?,?,?,?,?,?)";
        return result;
    }
    public int update(MemberBean stu){
        int result = 0;
        String sql = "update member "
                + "set pw = ?, "
                + "email = NVL(?,email), "
                + "phone = ? "
                + "where id = ? ";
        return result;
    }
    public int delete(String id){
        int result = 0;
        String sql = "delete member "
                + " where id = ?";
        return result;
    }
    // list
    public List<MemberBean> list(){
        String sql = "select * from member";
        List<MemberBean> tempList = new ArrayList<MemberBean>();
        return tempList;
    }
    // findByPK
    public MemberBean findById(String pk) {
        String sql = "select * from member where id = ?";
        MemberBean tempBean = null;
        return tempBean;
    }
    // findByName
    public List<?> findByName(String name) {
        String sql = "select * from member where name = ?";
        List<MemberBean> tempList = new ArrayList<MemberBean>();
        return tempList;
    }
    // count
    public int count() {
        int result = 0;
        String sql = "select count(*) count from member ";

        return result;
    }
    public boolean login(MemberBean param) {
        boolean loginOk= false;
        if(param.getId()!=null
                && param.getPw()!=null
                && this.existId(param.getId())){
            MemberBean member = this.findById(param.getId());
            if(member.getPw().equals(param.getPw())){
                loginOk = true;
            }
        }
        return loginOk;
    }
    public int findByGender(String gender) {
        int result = 0;
        String sql = "select count(*) count from member "
                + "where ((? = '남' and "
                + " substrb(ssn_id,8,1) in ('1','3','5','7')) or"
                + "(? = '여' and "
                + " substrb(ssn_id,8,1) in ('2','4','6','8'))) ";

        return result;
    }
    public int findId(String id) {
        int result = 0;
        String sql = "select count(*) count from member "
                + "where id = ?";

        return result;
    }

    public int findPw(MemberBean mem) {
        int result = 0;
        String sql = "select count(*) count from member "
                + "where id = ? "
                + "and pw = ? ";

        return result;
    }
    public boolean existId(String id){
        boolean existOK = false;
        String sql = "select count(*) as count from member where id = ?";
        int result = 0;

        if(result == 1){
            existOK = true;
        }
        return existOK;
    }
}
