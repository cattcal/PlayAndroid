package cn.hujw.demo.ui.activity;

import android.view.Gravity;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hujw.base.BaseDialog;
import cn.hujw.demo.R;
import cn.hujw.demo.common.MyActivity;
import cn.hujw.demo.helper.ActivityStackManager;
import cn.hujw.demo.helper.CacheDataManager;
import cn.hujw.demo.ui.dialog.MenuDialog;
import cn.hujw.image.ImageLoader;
import cn.hujw.widget.layout.SettingBar;
import cn.hujw.widget.view.SwitchButton;

/**
 * @author: hujw
 * @date: 2019/8/22
 * @description: 设置界面
 * @email: hujw_android@163.com
 */
public class SettingActivity extends MyActivity implements SwitchButton.OnCheckedChangeListener {

    @BindView(R.id.sb_setting_switch)
    SwitchButton mAutoSwitchView;
    @BindView(R.id.sb_setting_auto)
    SettingBar mAutoLoginView;

    @BindView(R.id.sb_setting_cache)
    SettingBar mCleanCacheView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        // 设置切换按钮的监听
        mAutoSwitchView.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        // 获取应用缓存大小
        mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(this));
    }

    @OnClick({R.id.sb_setting_language, R.id.sb_setting_update, R.id.sb_setting_agreement, R.id.sb_setting_about, R.id.sb_setting_auto, R.id.sb_setting_cache, R.id.sb_setting_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_setting_language:
                // 底部选择框
                new MenuDialog.Builder(this)
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setList(R.string.setting_language_simple, R.string.setting_language_complex)
                        .setListener(new MenuDialog.OnListener<String>() {

                            @Override
                            public void onSelected(BaseDialog dialog, int position, String string) {
                                WebActivity.start(getActivity(), "https://github.com/getActivity/MultiLanguages");
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {}
                        })
                        .setGravity(Gravity.BOTTOM)
                        .setAnimStyle(BaseDialog.AnimStyle.BOTTOM)
                        .show();
                break;
            case R.id.sb_setting_update:
                break;
            case R.id.sb_setting_agreement:
                WebActivity.start(this, "https://github.com");
                break;
            case R.id.sb_setting_about:
                startActivity(AboutActivity.class);
                break;
            case R.id.sb_setting_auto:
                // 自动登录
                mAutoSwitchView.setChecked(!mAutoSwitchView.isChecked());
                break;
            case R.id.sb_setting_cache:
                // 清空缓存
                ImageLoader.clear(this);
                CacheDataManager.clearAllCache(this);
                // 重新获取应用缓存大小
                mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(this));
                break;
            case R.id.sb_setting_exit:
                // 退出登录
                startActivity(LoginActivity.class);
                // 进行内存优化，销毁掉所有的界面
                ActivityStackManager.getInstance().finishAllActivities(LoginActivity.class);
                break;
        }
    }

    @Override
    public void onCheckedChanged(SwitchButton button, boolean isChecked) {
        toast(isChecked);
    }
}
