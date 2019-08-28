package cn.hujw.demo.ui.activity;

import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hujw.demo.R;
import cn.hujw.demo.common.MyActivity;
import cn.hujw.demo.helper.InputTextHelper;

/**
 * @author: hujw
 * @date: 2019/8/22
 * @description: 重置密码
 * @email: hujw_android@163.com
 */
public class PasswordResetActivity extends MyActivity {
    @BindView(R.id.et_password_reset_password1)
    AppCompatEditText mPassword1View;
    @BindView(R.id.et_password_reset_password2)
    AppCompatEditText mPassword2View;
    @BindView(R.id.btn_password_reset_commit)
    AppCompatButton mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_reset;
    }

    @Override
    protected void initView() {
        InputTextHelper.with(this)
                .setMain(mCommitView)
                .addView(mPassword1View)
                .addView(mPassword2View)
                .build();
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_password_reset_commit)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_password_reset_commit:
                if (!mPassword1View.getText().toString().equals(mPassword2View.getText().toString())) {
                    toast(R.string.password_reset_input_error);
                } else {
                    toast(R.string.password_reset_success);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
