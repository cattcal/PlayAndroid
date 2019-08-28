package cn.hujw.demo.mvp.copy;

import java.util.List;

/**
 * @author: hujw
 * @date: 2019/8/25
 * @description: 可进行拷贝的监听器
 * @email: hujw_android@163.com
 */
public interface CopyOnListener {

    void onSucceed(List<String> data);

    void onFail(String msg);
}
