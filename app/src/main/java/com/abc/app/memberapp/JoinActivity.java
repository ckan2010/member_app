package com.abc.app.memberapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener{
    MemberService service;
    EditText et_id,et_pw,et_name,et_ssn,et_email,et_phone;
    Button bt_join,bt_reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        service = new MemberServiceImpl(this.getApplicationContext());
        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_name = (EditText) findViewById(R.id.et_name);
        et_ssn = (EditText) findViewById(R.id.et_ssn);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phone = (EditText) findViewById(R.id.et_phone);
        bt_join = (Button) findViewById(R.id.bt_join);
        bt_reset = (Button) findViewById(R.id.bt_reset);
        bt_join.setOnClickListener(this);
        bt_reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_join:
                Toast.makeText(JoinActivity.this,"ID:"+et_id.getText().toString()+"PW:"+et_pw.getText().toString()+"NAME:"+et_name.getText().toString()+"SSN:"+et_ssn.getText().toString()+"EMAIL:"+et_email.getText().toString()+"PHONE:"+et_phone.getText().toString(),Toast.LENGTH_SHORT).show();
                MemberBean member = new MemberBean();
                member.setId(et_id.getText().toString());
                member.setPw(et_pw.getText().toString());
                member.setName(et_name.getText().toString());
                member.setSsn(et_ssn.getText().toString());
                member.setEmail(et_email.getText().toString());
                member.setPhone(et_phone.getText().toString());
                member.setProfile(et_id.getText().toString()+".jpg");
                service.regist(member);
                break;
            case R.id.bt_reset:
                startActivity(new Intent(this,MainActivity.class));
                break;

        }

    }
}