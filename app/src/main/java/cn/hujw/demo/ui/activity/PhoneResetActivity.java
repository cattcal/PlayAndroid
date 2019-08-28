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
 * @date: 2019/8/22
 * @description: 更换手机号
 * @email: hujw_android@163.com
 */
public class PhoneResetActivity extends MyActivity {
    @BindView(R.id.et_phone_reset_phone)
    RegexEditText mPhoneView;
    @BindView(R.id.et_phone_reset_code)
    AppCompatEditText mCodeView;
    @BindView(R.id.cv_phone_reset_countdown)
    CountdownView mCountdownView;
    @BindView(R.id.btn_phone_reset_commit)
    AppCompatButton mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_reset;
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


    @OnClick({R.id.cv_phone_reset_countdown, R.id.btn_phone_reset_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_phone_reset_countdown:
                if (mPhoneView.getText().toString().length() != 11) {
                    // 重置验证码倒计时控件
                    mCountdownView.resetState();
                    toast(R.string.common_phone_input_error);
                } else {
                    // 获取验证码
                    toast(R.string.common_code_send_hint);
                }
                break;
            case R.id.btn_phone_reset_commit:
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                } else {
                    // 更换手机号
                    toast(R.string.phone_reset_commit_succeed);
                    finish();
                }
                break;
        }
    }
}
