package com.jy.firsttest.shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jy.firsttest.shop.bean.LoginBean;
import com.jy.firsttest.shop.net.ApiServer;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName;
    private EditText userPwd;
    private Button btnLogin;
    private TextView textRegister;
    private TextView textForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        userName = (EditText) findViewById(R.id.userName);
        userPwd = (EditText) findViewById(R.id.userPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        textRegister = (TextView) findViewById(R.id.textRegister);
        textForget = (TextView) findViewById(R.id.textForget);

        btnLogin.setOnClickListener(this);
        textRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                submit();
                String name = userName.getText().toString().trim();
                String pwd = userPwd.getText().toString().trim();
                doLogin(name, pwd);
                break;
            case R.id.textRegister:
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent2, 520);
                break;
        }
    }

    //登录的方法
    @SuppressLint("CheckResult")
    private void doLogin(String name, String pwd) {
        //1.获取retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.url) //访问网络的url地址
                .addConverterFactory(GsonConverterFactory.create()) //对Gson解析的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //对象RxJava的支持
                .build();

        //2.创建的接口对象
        ApiServer apiServer = retrofit.create(ApiServer.class);

        //3.调用登录方法
        Flowable<LoginBean> loginBeanFlowable = apiServer.doLogin(name, pwd);

        //4.执行操作
        loginBeanFlowable.subscribeOn(Schedulers.io()) //被观察者在子线程当中执行；一般用Schedulers.io()有线程池 效率高一些
                .observeOn(AndroidSchedulers.mainThread())//观察者在主线程
                .subscribeWith(new ResourceSubscriber<LoginBean>() {
                    //成功
                    @Override
                    public void onNext(LoginBean loginBean) {
                        int code = loginBean.getData().getCode();
                        if (code == 200) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    //失败
                    @Override
                    public void onError(Throwable t) {
                        Log.d("TAG", "onError: " + t.toString());
                    }

                    //完成
                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void submit() {
        String userNameString = userName.getText().toString().trim();
        if (TextUtils.isEmpty(userNameString)) {
            Toast.makeText(this, "账号", Toast.LENGTH_SHORT).show();
            return;
        }

        String userPwdString = userPwd.getText().toString().trim();
        if (TextUtils.isEmpty(userPwdString)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 520 && resultCode == 250) {
            String name = data.getStringExtra("name");
            Log.d("name", "onActivityResult: " + name);
            String pwd = data.getStringExtra("pwd");
            Log.d("pwd", "onActivityResult: " + pwd);
            userName.setText(name);
            userPwd.setText(pwd);
        }
    }
}
