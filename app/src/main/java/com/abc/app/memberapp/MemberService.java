package com.abc.app.memberapp;

import java.util.List;

/**
 * Created by ckan on 2016-07-27.
 */
public interface MemberService {
    public String regist(MemberBean stu);
    public void update(MemberBean stu);
    public String delete(String id);
    public MemberBean findById(String id);
    public void logout(MemberBean mem);
    public List<?> list();
}
