package com.abc.app.memberapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    EditText et_id,et_pw;
    Button bt_login,bt_join;
    TextView tv_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_join = (Button) findViewById(R.id.bt_join);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_join:break;
            case R.id.bt_login:
                Toast.makeText(MainActivity.this,"ID : "+et_id.getText().toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this,"PW : "+et_pw.getText().toString(),Toast.LENGTH_LONG).show();
                break;

        }
    }
}
