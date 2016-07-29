package com.abc.app.memberapp;

import java.util.ArrayList;

/**
 * Created by ckan on 2016-07-27.
 */
public interface MemberService {
    public void regist(MemberBean stu);
    public void update(MemberBean stu);
    public String delete(String id);
    public MemberBean findById(String id);
    public void logout(MemberBean mem);
    public ArrayList<MemberBean> list();
    public MemberBean show();
    public boolean login(MemberBean member);
}
