package com.abc.app.memberapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends Activity implements View.OnClickListener{
    EditText et_id,et_pw,et_name,et_ssn,et_email,et_phone;
    Button bt_join,bt_rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_name = (EditText) findViewById(R.id.et_name);
        et_ssn = (EditText) findViewById(R.id.et_ssn);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phone = (EditText) findViewById(R.id.et_phone);
        bt_join = (Button) findViewById(R.id.bt_join);
        bt_rest = (Button) findViewById(R.id.bt_rest);
        bt_join.setOnClickListener(this);
        bt_rest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_join:
                Toast.makeText(JoinActivity.this,"회원가입단추",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.bt_rest:break;
        }
    }
}
