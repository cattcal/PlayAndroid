package cn.hujw.demo.ui.activity;

import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hujw.demo.R;
import cn.hujw.demo.common.MyActivity;
import cn.hujw.demo.helper.InputTextHelper;
import cn.hujw.widget.view.CountdownView;

/**
 * @author: hujw
 * @date: 2019/8/22
 * @description: 校验手机号
 * @email: hujw_android@163.com
 */
public class PhoneVerifyActivity extends MyActivity {
    @BindView(R.id.tv_phone_verify_phone)
    AppCompatTextView mPhoneView;
    @BindView(R.id.et_phone_verify_code)
    AppCompatEditText mCodeView;
    @BindView(R.id.cv_phone_verify_countdown)
    CountdownView mCountdownView;
    @BindView(R.id.btn_phone_verify_commit)
    AppCompatButton mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_verify;
    }

    @Override
    protected void initView() {
        InputTextHelper.with(this)
                .setMain(mCommitView)
                .addView(mCodeView)
                .build();
    }

    @Override
    protected void initData() {
        mPhoneView.setText(String.format(getString(R.string.phone_verify_current_phone), "18888888888"));
    }

    @OnClick({R.id.cv_phone_verify_countdown, R.id.btn_phone_verify_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_phone_verify_countdown:
                // 获取验证码
                toast(R.string.common_code_send_hint);
                break;
            case R.id.btn_phone_verify_commit:
                // 修改手机号
                startActivityFinish(PhoneResetActivity.class);
                break;
        }
    }
}
