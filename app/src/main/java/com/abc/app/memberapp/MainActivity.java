package com.abc.app.memberapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    EditText et_id,et_pw;
    Button bt_login,bt_join,bt_img;
    TextView tv_msg;
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
        bt_img = (Button) findViewById(R.id.bt_img);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
        bt_login.setOnClickListener(this);
        bt_join.setOnClickListener(this);
        bt_img.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_join:
                Toast.makeText(MainActivity.this,"회원가입화면",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,JoinActivity.class));
                break;
            case R.id.bt_login:
                Toast.makeText(MainActivity.this,"ID : "+et_id.getText().toString()+"PW : "+et_pw.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.bt_img:
                startActivity(new Intent(this,ImageActivity.class));
                break;

        }
    }
}
