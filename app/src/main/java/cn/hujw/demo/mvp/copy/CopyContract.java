package cn.hujw.demo.mvp.copy;

import java.util.List;

import cn.hujw.demo.mvp.IMvpView;

/**
 * @author: hujw
 * @date: 2019/8/25
 * @description: 可进行拷贝的契约类
 * @email: hujw_android@163.com
 */
public final class CopyContract {

    public interface View extends IMvpView {

        void loginSuccess(List<String> data);

        void loginError(String msg);
    }

    public interface Presenter {

        void login(String account, String password);
    }
}
