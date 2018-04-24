package com.iuoon.app.reader.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iuoon.app.reader.R;
import com.iuoon.app.reader.net.Header;
import com.iuoon.app.reader.net.HttpApi;
import com.iuoon.app.reader.net.RRetrofit;
import com.iuoon.app.reader.model.XUser;
import com.iuoon.app.reader.net.TransVo;
import com.iuoon.app.reader.net.UserResp;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    private HttpApi httpApi= RRetrofit.create(HttpApi.class);
    private Button loginButton;
    private EditText accountEditText;
    private EditText pwdEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton=(Button) findViewById(R.id.loginButton);
        accountEditText=(EditText) findViewById(R.id.accountEditText);
        pwdEditText=(EditText) findViewById(R.id.pwdEditText);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    /**
     * 登录
     */
    public void login(){
        if ("".equals(accountEditText.getText().toString())){
            new  AlertDialog.Builder(LoginActivity.this)
                    .setMessage("请输入用户名" )
                    .setPositiveButton("确定" ,  null )
                    .show();
            return;
        }
        if ("".equals(pwdEditText.getText().toString())){
            new  AlertDialog.Builder(LoginActivity.this)
                    .setMessage("请输入密码" )
                    .setPositiveButton("确定" ,  null )
                    .show();
            return;
        }
        XUser user=new XUser();
        Header header=new Header();
        header.setStrPlatform("app");
        header.setToken("user");
        header.setUserId("");
        user.setStrMobile(accountEditText.getText().toString());
        user.setStrPwd(pwdEditText.getText().toString());
        user.setHeader(header);
        httpApi.login(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( new Subscriber<UserResp>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("login error----->", e.toString());
                    }
                    @Override
                    public void onNext(UserResp userResp) {
                        Log.d("login success----->",userResp.getCode()+"---"+ userResp.getMsg());
                        if (userResp.getCode().equals("0")){
                            SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = sp.edit();
                            //通过editor对象写入数据
                            edit.putString("strMobile",accountEditText.getText().toString().trim());
                            edit.putString("userId",userResp.getUser().getUserId());
                            edit.putString("token",userResp.getUser().getToken());
                            //提交数据存入到xml文件中
                            edit.commit();
                            back(accountEditText.getText().toString());
                        }else {
                            new  AlertDialog.Builder(LoginActivity.this)
                                    .setMessage(userResp.getMsg() )
                                    .setPositiveButton("确定" ,  null )
                                    .show();
                        }
                    }
                });

    }

    public void back(String userName){
        Intent intent=new Intent(this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("tab", "2");//跳到我的页面
        bundle.putString("userName",userName);
        intent.putExtras(bundle);
        startActivityForResult(intent, 100);
    }

}

