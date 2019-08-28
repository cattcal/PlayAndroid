package cn.hujw.demo.mvp;

/**
 * @author: hujw
 * @date: 2019/8/25
 * @description: MVP 模型基类
 * @email: hujw_android@163.com
 */
public abstract class MvpModel<L> {

    private L mListener;

    public void setListener(L l) {
        mListener = l;
    }

    public L getListener() {
        return mListener;
    }
}
