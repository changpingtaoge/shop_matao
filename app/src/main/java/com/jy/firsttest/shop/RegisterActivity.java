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

import com.jy.firsttest.shop.bean.UserBean;
import com.jy.firsttest.shop.bean.UserRegisterBean;
import com.jy.firsttest.shop.net.ApiServer;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";
    private EditText userName;
    private EditText userPwd;
    private EditText userPwd2;
    private Button doRegister;

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
        doRegister = (Button) findViewById(R.id.doRegister);

        doRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doRegister:
                submit();
                String userNameString = userName.getText().toString().trim();
                String userPwdString = userPwd.getText().toString().trim();
                doRegister(userNameString, userPwdString);
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void doRegister(String userNameString, String userPwdString) {
        //1.创建Rerotfit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2.通过反射获取接口对象
        ApiServer apiServer = retrofit.create(ApiServer.class);
        //3.对用注册的方法
        Flowable<UserRegisterBean> userRegisterBean = apiServer.doRegister(userNameString, userPwdString);
        //4.执行操作
        userRegisterBean
                .subscribeOn(Schedulers.io()) //子线程访问数据
                .observeOn(AndroidSchedulers.mainThread()) //显示在主线程
                .subscribeWith(new ResourceSubscriber<UserRegisterBean>() {
                    @Override
                    public void onNext(UserRegisterBean userRegisterBean) {
                        String errmsg = userRegisterBean.getErrmsg();
                        int errno = userRegisterBean.getErrno();
                        String nickname = userRegisterBean.getData().getUserInfo().getNickname();
                        Log.d(TAG, "errmsg: " + errmsg + "errno:" + errno + "nickname:" + nickname);
                        if (errno == 0 && nickname.equals(userNameString)) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("name", userNameString);
                            intent.putExtra("pwd", userPwdString);
                            setResult(520, intent);
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "用户名", Toast.LENGTH_SHORT).show();
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
