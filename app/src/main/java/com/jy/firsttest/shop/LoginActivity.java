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

import com.jy.firsttest.shop.bean.UserBean;
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
    private Button doLogin;
    private TextView zhuce;
    private TextView forget;
    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        userName = (EditText) findViewById(R.id.userName);
        userPwd = (EditText) findViewById(R.id.userPwd);
        doLogin = (Button) findViewById(R.id.doLogin);
        zhuce = (TextView) findViewById(R.id.zhuce);
        forget = (TextView) findViewById(R.id.forget);

        doLogin.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doLogin:
                submit();
                String userName1 = userName.getText().toString().trim();
                String userPwd1 = userPwd.getText().toString().trim();
                doLogin(userName1, userPwd1);
                break;
            case R.id.zhuce:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 250);
                break;
            case R.id.forget:
                Intent intent2 = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void doLogin(String userName, String userPwd) {

        //1.获取retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //2.创建接口对象
        ApiServer apiServer = retrofit.create(ApiServer.class);

        //3.调用登录的方法
        Flowable<UserBean> userBeanFlowable = apiServer.doLogin(userName, userPwd);

        //4.执行操作
        userBeanFlowable.subscribeOn(Schedulers.io())//访问在子线程
                .observeOn(AndroidSchedulers.mainThread())//执行在主线程
                .subscribeWith(new ResourceSubscriber<UserBean>() {
                    @Override
                    public void onNext(UserBean userBean) {
                        UserBean.DataBean data = userBean.getData();
                        int code = data.getCode();
                        if (code == 200) {
                            Intent intent3 = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent3);
                        } else {
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "onError: " + t.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void submit() {
        // validate
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
        if (requestCode == 250 && resultCode == 520) {
            String name = data.getStringExtra("name");
            String pwd = data.getStringExtra("pwd");
            userName.setText(name);
            userPwd.setText(pwd);
        }
    }
}
