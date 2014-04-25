package com.tencent.yourremind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.yourremind.util.Constant;
import com.tencent.yourremind.util.SettingUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sidyang on 14-4-10.
 */
public class LoginActivity extends Activity {

    private Button qqLogin;
    private Tencent mTencent;
    protected final static String SCOPE = "get_user_info";
    private String username;
    private String password;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        //初始化登录UI
        initUi();

        //判断是否直接跳转
        whetherGotoMainActivity();
    }


    public void initUi(){
        //setting login listener
        qqLogin = (Button)findViewById(R.id.qqlogin);

        qqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTencent = Tencent.createInstance(Constant.APPID_QQ_SDK, getBaseContext());
                if(!mTencent.isSessionValid()){
                    mTencent.login(LoginActivity.this,SCOPE,new BaseUiListener());
                }else {
                    username = mTencent.getOpenId();
                    password = mTencent.getOpenId();
                    GoToMainYourrmind();
                }

            }
        });
    }

    public void whetherGotoMainActivity(){
        if(SettingUtil.getIsUserLogin(getBaseContext())){
            GoToMainYourrmind();
        }
    }

    public void GoToMainYourrmind(){
        SettingUtil.setIsUserLogin(getBaseContext(),true);
        Intent intent = new Intent(this, YourremindActivity.class);
        startActivity(intent);
        this.finish();
    }

    /**
     * QQ登录回调函数
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            try{
                JSONObject jsonObject = (JSONObject)response;
                String openId = jsonObject.getString("openid");
                if (openId != null && !openId.equals("")) {
                    username = openId;
                    password = openId;
                    SettingUtil.setUsername(getBaseContext(),username);
                    SettingUtil.getUsername(getBaseContext());
                    SettingUtil.setPassword(getBaseContext(),password);
                    GoToMainYourrmind();
                }
            }catch (JSONException e){

            }
        }

        @Override
        public void onError(UiError e) {

        }

        @Override
        public void onCancel() {
        }

    }
}