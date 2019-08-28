package cn.hujw.demo.ui.activity;

import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hujw.demo.R;
import cn.hujw.demo.common.MyActivity;
import cn.hujw.demo.helper.InputTextHelper;
import cn.hujw.widget.view.CountdownView;
import cn.hujw.widget.view.RegexEditText;

/**
 * @author: hujw
 * @date: 2019/8/21
 * @description: 忘记密码
 * @email: hujw_android@163.com
 */
public class PasswordForgetActivity extends MyActivity {
    @BindView(R.id.et_password_forget_phone)
    RegexEditText mPhoneView;
    @BindView(R.id.et_password_forget_code)
    AppCompatEditText mCodeView;
    @BindView(R.id.cv_password_forget_countdown)
    CountdownView mCountdownView;
    @BindView(R.id.btn_password_forget_commit)
    AppCompatButton mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_forget;
    }

    @Override
    protected void initView() {
        InputTextHelper.with(this)
                .setMain(mCommitView)
                .addView(mPhoneView)
                .addView(mCodeView)
                .build();
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.cv_password_forget_countdown, R.id.btn_password_forget_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_password_forget_countdown:
                if (mPhoneView.getText().toString().length() != 11) {
                    // 重置验证码倒计时控件
                    mCountdownView.resetState();
                    toast(R.string.common_phone_input_error);
                } else {
                    // 获取验证码
                    toast(R.string.common_code_send_hint);
                }
                break;
            case R.id.btn_password_forget_commit:
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                } else {
                    // 重置密码
                    startActivityFinish(PasswordResetActivity.class);
                }
                break;
        }
    }
}
