package cn.hujw.demo.mvp;

import android.content.Context;

/**
 * @author: hujw
 * @date: 2019/8/25
 * @description: MVP 通用性接口
 * @email: hujw_android@163.com
 */
public interface IMvpView {

    /**
     * 获取上下文对象
     */
    Context getContext();

    /**
     * 加载中
     */
    void onLoading();

    /**
     * 加载完成
     */
    void onComplete();

    /**
     * 用于请求的数据为空的状态
     */
    void onEmpty();

    /**
     * 用于请求数据出错
     */
    void onError();
}
