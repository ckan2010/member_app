package com.abc.app.memberapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    EditText et_id,et_pw;
    Button bt_login,bt_join;
    MemberService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = new MemberServiceImpl(this.getApplicationContext());
        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_join = (Button) findViewById(R.id.bt_join);
        bt_login.setOnClickListener(this);
        bt_join.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_join:
                startActivity(new Intent(this,JoinActivity.class));
                break;
            case R.id.bt_login:
                Toast.makeText(MainActivity.this,
                        "ID:"+et_id.getText().toString()
                                +" PW:"+et_pw.getText().toString(),
                        Toast.LENGTH_LONG).show();
                MemberBean member = new MemberBean();
                member.setId(et_id.getText().toString());
                member.setPw(et_pw.getText().toString());
                if(service.login(member)) {
                    System.out.println("Login sucess");
                    startActivity(new Intent(this,HomeActivity.class));
                }else{
                    System.out.println("Login fails");
                    startActivity(new Intent(this,MainActivity.class));
                }
                break;
        }
    }
}