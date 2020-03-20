package com.jy.firsttest.shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jy.firsttest.shop.bean.RegisterBean;
import com.jy.firsttest.shop.net.ApiServer;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName;
    private EditText userPwd;
    private EditText userPwd2;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        userName = (EditText) findViewById(R.id.userName);
        userPwd = (EditText) findViewById(R.id.userPwd);
        userPwd2 = (EditText) findViewById(R.id.userPwd2);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                submit();
                String name = userName.getText().toString().trim();
                String password = userPwd.getText().toString().trim();
                doRegister(name, password);
                break;
        }
    }

    //注册的方法
    @SuppressLint("CheckResult")
    private void doRegister(String name, String password) {
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.url) //访问网络的url地址
                .addConverterFactory(GsonConverterFactory.create())//对Gson解析的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //对RxJava的支持
                .build();

        //2.创建接口对象
        ApiServer apiServer = retrofit.create(ApiServer.class);

        //3.调用注册方法
        Flowable<RegisterBean> registerBeanFlowable = apiServer.doRegister(name, password);

        //4.执行操作
        registerBeanFlowable
                .subscribeOn(Schedulers.io()) //访问数据在子线程
                .observeOn(AndroidSchedulers.mainThread()) //执行在主线程 【线程切换】
                .subscribeWith(new ResourceSubscriber<RegisterBean>() {
                    //成功
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        int errno = registerBean.getErrno();
                        if (errno == 0) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("pwd", password);
                            setResult(250, intent);
                            finish();
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

        String userPwd2String = userPwd2.getText().toString().trim();
        if (TextUtils.isEmpty(userPwd2String)) {
            Toast.makeText(this, "确认密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!userPwdString.equals(userPwd2String)) {
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
