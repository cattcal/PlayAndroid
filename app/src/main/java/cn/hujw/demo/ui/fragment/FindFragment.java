package cn.hujw.demo.ui.fragment;


import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hujw.demo.R;
import cn.hujw.demo.common.MyLazyFragment;
import cn.hujw.demo.ui.activity.HomeActivity;
import cn.hujw.widget.view.CountdownView;
import cn.hujw.widget.view.SwitchButton;

/**
 * @author: hujw
 * @date: 2019/8/11
 * @description: 发现页面
 * @email: hujw_android@163.com
 */
public class FindFragment extends MyLazyFragment<HomeActivity> implements SwitchButton.OnCheckedChangeListener {

    @BindView(R.id.sb_find_switch)
    SwitchButton mSwitchButton;

    @BindView(R.id.cv_find_countdown)
    CountdownView mCountdownView;

    public static FindFragment newInstance() {
        return new FindFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        mSwitchButton.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.cv_find_countdown)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_find_countdown:
                toast(R.string.common_code_send_hint);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(SwitchButton button, boolean isChecked) {
        toast(isChecked);
    }
}
