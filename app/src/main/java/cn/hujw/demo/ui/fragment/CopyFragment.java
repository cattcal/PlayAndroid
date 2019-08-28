package cn.hujw.demo.ui.fragment;

import cn.hujw.demo.R;
import cn.hujw.demo.common.MyLazyFragment;
import cn.hujw.demo.ui.activity.CopyActivity;

/**
 * @author: hujw
 * @date: 2019/8/28
 * @description: 可进行拷贝的副本
 * @email: hujw_android@163.com
 */
public final class CopyFragment extends MyLazyFragment<CopyActivity> {

    public static CopyFragment newInstance() {
        return new CopyFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_copy;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
