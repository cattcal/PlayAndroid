package cn.hujw.demo.mvp.proxy;

/**
 * @author: hujw
 * @date: 2019/8/25
 * @description: 逻辑层代理接口
 * @email: hujw_android@163.com
 */
public interface IMvpPresenterProxy {
    /**
     * 绑定 Presenter
     */
    void bindPresenter();

    /**
     * 解绑 Presenter
     */
    void unbindPresenter();
}
