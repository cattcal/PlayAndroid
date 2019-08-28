package cn.hujw.demo.ui.activity;

import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.gyf.immersionbar.ImmersionBar;

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
 * @description: 注册界面
 * @email: hujw_android@163.com
 */
public class RegisterActivity extends MyActivity {

    @BindView(R.id.et_register_phone)
    RegexEditText mPhoneView;
    @BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.et_register_code)
    AppCompatEditText mCodeView;

    @BindView(R.id.et_register_password1)
    AppCompatEditText mPasswordView1;
    @BindView(R.id.et_register_password2)
    AppCompatEditText mPasswordView2;

    @BindView(R.id.btn_register_commit)
    AppCompatButton mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        InputTextHelper.with(this)
                .setMain(mCommitView)
                .addView(mPhoneView)
                .addView(mCodeView)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .build();
    }

    @Override
    protected void initData() {

    }


    @Override
    protected ImmersionBar statusBarConfig() {
        return super.statusBarConfig().keyboardEnable(true);
    }

    @OnClick({R.id.cv_register_countdown, R.id.btn_register_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_register_countdown:
                // 获取验证码
                if (mPhoneView.getText().toString().length() != 11) {
                    // 重置验证码倒计时控件
                    mCountdownView.resetState();
                    toast(R.string.common_phone_input_error);
                } else {
                    // 获取验证码
                    toast(R.string.common_code_send_hint);
                }
                break;
            case R.id.btn_register_commit:
                // 提交注册
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                } else if (!mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString())) {
                    toast(R.string.register_password_input_error);
                } else {
                    startActivity(LoginActivity.class);
                }
                break;
            default:
                break;
        }
    }
}
