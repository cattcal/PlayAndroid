package cn.hujw.demo.ui.fragment;


import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.AppBarLayout;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import cn.hujw.demo.R;
import cn.hujw.demo.common.MyLazyFragment;
import cn.hujw.demo.ui.activity.HomeActivity;
import cn.hujw.demo.widget.XCollapsingToolbarLayout;

/**
 * @author: hujw
 * @date: 2019/8/11
 * @description: 主页面
 * @email: hujw_android@163.com
 */
public class HomeFragment extends MyLazyFragment<HomeActivity> implements XCollapsingToolbarLayout.OnScrimsListener {

    @BindView(R.id.abl_home_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.ctl_home_bar)
    XCollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.t_home_title)
    Toolbar mToolbar;
    @BindView(R.id.tv_home_scan)
    TextView mScanView;
    @BindView(R.id.et_home_search)
    EditText mSearchView;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        // 给这个ToolBar设置顶部内边距，才能和TitleBar进行对齐
        ImmersionBar.setTitleBar(getAttachActivity(), mToolbar);
        //设置渐变监听
        mCollapsingToolbarLayout.setOnScrimsListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean statusBarDarkFont() {
        return mCollapsingToolbarLayout.isScrimsShown();
    }

    /**
     * CollapsingToolbarLayout 渐变回调
     *
     * @param layout
     * @param shown  渐变开关
     */
    @Override
    public void onScrimsStateChange(XCollapsingToolbarLayout layout, boolean shown) {
        if (shown) {
            mScanView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black));
            mSearchView.setBackgroundResource(R.drawable.bg_home_search_bar_gray);
            getStatusBarConfig().statusBarDarkFont(true).init();
        } else {
            mScanView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white));
            mSearchView.setBackgroundResource(R.drawable.bg_home_search_bar_transparent);
            getStatusBarConfig().statusBarDarkFont(false).init();
        }
    }
}
