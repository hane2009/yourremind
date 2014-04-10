package com.tencent.yourremind;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.tencent.tauth.Tencent;
import com.tencent.yourremind.R;
import com.tencent.yourremind.util.Constant;

/**
 * Created by sidyang on 14-4-10.
 */
public class LoginActivity extends Activity {

    private Button qqLogin;
    private Tencent mTencent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        qqLogin = (Button)findViewById(R.id.qqlogin);

        qqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTencent = Tencent.createInstance(Constant.APPID_QQ_SDK, getApplicationContext());
                System.out.println(mTencent);
            }
        });

    }
}