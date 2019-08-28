package cn.hujw.demo.ui.fragment;

import android.view.View;

import java.util.ArrayList;

import butterknife.OnClick;
import cn.hujw.demo.R;
import cn.hujw.demo.common.MyLazyFragment;
import cn.hujw.demo.ui.activity.AboutActivity;
import cn.hujw.demo.ui.activity.DialogActivity;
import cn.hujw.demo.ui.activity.HomeActivity;
import cn.hujw.demo.ui.activity.ImageActivity;
import cn.hujw.demo.ui.activity.LoginActivity;
import cn.hujw.demo.ui.activity.PasswordForgetActivity;
import cn.hujw.demo.ui.activity.PasswordResetActivity;
import cn.hujw.demo.ui.activity.PersonalDataActivity;
import cn.hujw.demo.ui.activity.PhoneResetActivity;
import cn.hujw.demo.ui.activity.PhoneVerifyActivity;
import cn.hujw.demo.ui.activity.RegisterActivity;
import cn.hujw.demo.ui.activity.SettingActivity;
import cn.hujw.demo.ui.activity.StatusActivity;
import cn.hujw.demo.ui.activity.WebActivity;

/**
 * @author: hujw
 * @date: 2019/8/11
 * @description: 项目界面跳转示例
 * @email: hujw_android@163.com
 */
public class MineFragment extends MyLazyFragment<HomeActivity> {


    public static MineFragment newInstance() {
        return new MineFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_mine_dialog, R.id.btn_mine_hint, R.id.btn_mine_login, R.id.btn_mine_register, R.id.btn_mine_forget, R.id.btn_mine_reset, R.id.btn_mine_verify, R.id.btn_mine_change, R.id.btn_mine_personal, R.id.btn_mine_setting, R.id.btn_mine_about, R.id.btn_mine_browser, R.id.btn_mine_image, R.id.btn_mine_crash})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mine_dialog:
                startActivity(DialogActivity.class);
                break;
            case R.id.btn_mine_hint:
                startActivity(StatusActivity.class);
                break;
            case R.id.btn_mine_login:
                startActivity(LoginActivity.class);
                break;
            case R.id.btn_mine_register:
                startActivity(RegisterActivity.class);
                break;
            case R.id.btn_mine_forget:
                startActivity(PasswordForgetActivity.class);
                break;
            case R.id.btn_mine_reset:
                startActivity(PasswordResetActivity.class);
                break;
            case R.id.btn_mine_verify:
                startActivity(PhoneVerifyActivity.class);
                break;
            case R.id.btn_mine_change:
                startActivity(PhoneResetActivity.class);
                break;
            case R.id.btn_mine_personal:
                startActivity(PersonalDataActivity.class);
                break;
            case R.id.btn_mine_setting:
                startActivity(SettingActivity.class);
                break;
            case R.id.btn_mine_about:
                startActivity(AboutActivity.class);
                break;
            case R.id.btn_mine_browser:
                WebActivity.start(getAttachActivity(), "https://github.com/");
                break;
            case R.id.btn_mine_image:
                ArrayList<String> images = new ArrayList<>();
                images.add("http://img5.imgtn.bdimg.com/it/u=2820110229,1486209150&fm=26&gp=0.jpg");
                images.add("https://img5.duitang.com/uploads/item/201507/06/20150706081750_zuA3P.thumb.700_0.jpeg");
                images.add("https://b-ssl.duitang.com/uploads/item/201808/07/20180807111055_bfmws.thumb.700_0.jpg");
                ImageActivity.start(getAttachActivity(), images, images.size() - 1);
                break;
            case R.id.btn_mine_crash:
                throw new IllegalStateException("are you ok?");

        }
    }
}
