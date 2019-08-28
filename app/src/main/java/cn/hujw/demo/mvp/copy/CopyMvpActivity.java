package cn.hujw.demo.mvp.copy;

import android.view.View;

import java.util.List;

import cn.hujw.demo.mvp.MvpActivity;
import cn.hujw.demo.mvp.MvpInject;

/**
 * @author: hujw
 * @date: 2019/8/25
 * @description: 可进行拷贝的MVP Activity 类
 * @email: hujw_android@163.com
 */
public class CopyMvpActivity extends MvpActivity implements CopyContract.View {

    @MvpInject
    CopyPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void onLogin(View view) {
        // 登录操作
        mPresenter.login("账户", "密码");
    }

    @Override
    public void loginSuccess(List<String> data) {
        toast("登录成功");
    }

    @Override
    public void loginError(String msg) {
        toast(msg);
    }
}
