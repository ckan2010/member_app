package com.abc.app.memberapp;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckan on 2016-07-27.
 */
public class MemberServiceImpl implements MemberService{
    MemberDAO dao;
    MemberBean session;
    public MemberServiceImpl(Context context) {
        dao = new MemberDAO(context);
    }

    @Override
    public void regist(MemberBean stu) {
        int cnt = 0;
        cnt = dao.insert(stu);
        Log.d("member Insert : ",String.valueOf(cnt));
    }
    @Override
    public void update(MemberBean stu) {
        dao.update(stu);
    }
    @Override
    public String delete(String id) {
        int cnt = 0;
        String msg = "";
        if (dao.delete(id) != 0) {
            session = this.findById(id);
            msg = "삭제 성공 입니다.";
        }else{
            msg = "삭제 실패입니다.";
        }
        return msg;
    }
    public int count() {
        return dao.count();
    }
    public MemberBean findById(String id) {
        return dao.findById(id);
    }
    public ArrayList<MemberBean> list() {

        return dao.list();
    }
    public List<?> findBy(String name) {
        return dao.findByName(name);
    }
    @Override
    public void logout(MemberBean mem) {
        if (session.getId().equals(mem.getId()) &&
                session.getPw().equals(mem.getPw())
                ) {
            session = null;
        }
    }
    public MemberBean show() {
        return session;
    }

    @Override
    public boolean login(MemberBean member) {
        return dao.login(member);
    }
}
