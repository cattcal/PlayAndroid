package cn.hujw.demo.mvp.proxy;

/**
 * @author: hujw
 * @date: 2019/8/25
 * @description: 模型层代理接口
 * @email: hujw_android@163.com
 */
public interface IMvpModelProxy {
    /**
     * 绑定 Model
     */
    void bindModel();

    /**
     * 解绑 Model
     */
    void unbindModel();
}
