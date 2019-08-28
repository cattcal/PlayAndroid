package cn.hujw.demo.mvp;

import cn.hujw.demo.common.MyLazyFragment;
import cn.hujw.demo.mvp.IMvpView;
import cn.hujw.demo.mvp.proxy.IMvpPresenterProxy;
import cn.hujw.demo.mvp.proxy.MvpPresenterProxyImpl;

/**
 * @author: hujw
 * @date: 2019/8/25
 * @description: MVP 懒加载 Fragment 基类
 * @email: hujw_android@163.com
 */
public abstract class MvpLazyFragment extends MyLazyFragment implements IMvpView {

    private IMvpPresenterProxy mMvpProxy;

    @Override
    protected void initFragment() {
        mMvpProxy = createPresenterProxy();
        mMvpProxy.bindPresenter();
        super.initFragment();
    }

    protected IMvpPresenterProxy createPresenterProxy() {
        return new MvpPresenterProxyImpl(this);
    }

    @Override
    public void onDestroy() {
        if (mMvpProxy != null) {
            mMvpProxy.unbindPresenter();
        }
        super.onDestroy();
    }
}
