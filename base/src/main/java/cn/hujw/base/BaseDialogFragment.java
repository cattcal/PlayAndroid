package cn.hujw.base;

import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author: hujw
 * @date: 2019/8/18
 * @description: DialogFragment 基类
 * @email: hujw_android@163.com
 */
public class BaseDialogFragment extends DialogFragment {

    private BaseDialog mDialog;

    private static String sShowTag;
    private static long sLastTime;

    public BaseDialogFragment() {
        /**
         * 必须预留一个空参构造函数，因为 Activity 横竖屏切换的时候会通过反射创建 Fragment
         * 如果没有预留的话就会抛出：Unable to instantiate fragment com.hjq.base.BaseDialogFragment: could not find Fragment constructor
         */
    }

    public BaseDialogFragment(BaseDialog dialog) {
        mDialog = dialog;
        if (mDialog == null) {
            throw new IllegalArgumentException("The dialog box cannot be empty");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        /**
         * super.onActivityCreated 如果往下执行了，里面的 mDialog 对象在横竖屏切换的时会变成一个空对象，所以就会导致空指针异常
         * 从 super.onActivityCreated 源码中得知 mShowsDialog 为 false 不会向下执行，所以我们要在这里进行预设值，避免空指针
         */
        if (mDialog == null) {
            setShowsDialog(false);
        }
        super.onActivityCreated(savedInstanceState);
        /**
         * 如果这个 Dialog 对象为空，就移除这个 DialogFragment
         */
        if (mDialog == null) {
            dismissAllowingStateLoss();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return mDialog;
    }

    @Override
    public Dialog getDialog() {
        return mDialog;
    }

    /**
     * 父类同名方法简化
     */
    public void show(@NonNull Fragment fragment) {
        FragmentManager manager = fragment.getFragmentManager();
        if (manager != null) {
            show(manager, fragment.getClass().getName());
        }
    }

    /**
     * 父类同名方法简化
     */
    public void show(@NonNull FragmentActivity activity) {
        show(activity.getSupportFragmentManager(), activity.getClass().getName());
    }

    @Override
    public void show(@NonNull FragmentManager manager, String tag) {
        // !FragmentManager.isStateSaved()：如果Activity 已经不可见，就不让这个对话框显示，否则会发生报错
        if (!isRepeatedShow(tag) && !manager.isStateSaved()) {
            super.show(manager, tag);
        }
    }

    @Override
    public int show(@NonNull FragmentTransaction transaction, String tag) {
        if (!isRepeatedShow(tag)) {
            return super.show(transaction, tag);
        }
        return -1;
    }

    /**
     * 根据 tag 判断这个 Dialog 是否重复显示了
     *
     * @param tag           Tag标记
     */
    @SuppressWarnings("all")
    protected boolean isRepeatedShow(String tag) {
        boolean result = tag.equals(sShowTag) && SystemClock.uptimeMillis() - sLastTime < 500;
        sShowTag = tag;
        sLastTime = SystemClock.uptimeMillis();
        return result;
    }

    public static class Builder<B extends BaseDialogFragment.Builder> extends BaseDialog.Builder<B> {

        private final FragmentActivity mActivity;
        private BaseDialogFragment mDialogFragment;

        public Builder(FragmentActivity activity) {
            super(activity);
            mActivity = activity;
        }

        /**
         * 获取当前 Activity 对象（仅供子类调用）
         */
        protected FragmentActivity getActivity() {
            return mActivity;
        }

        /**
         * 获取当前 DialogFragment 对象（仅供子类调用）
         */
        protected BaseDialogFragment getDialogFragment() {
            return mDialogFragment;
        }

        /**
         * 获取 Fragment 的标记
         */
        protected String getFragmentTag() {
            return getClass().getName();
        }

//        // 重写父类的方法（仅供子类调用）
//        @Override
//        protected void dismiss() {
//            try {
//                mDialogFragment.dismiss();
//            } catch (IllegalStateException e) {
//                // java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
//                // 这里不能调用 DialogFragment 的 dismiss 方法，因为在前台 show 之后却在后台 dismiss 会导致崩溃
//                // 使用 Dialog 的 dismiss 方法却不会出现这种情况，除此之外没有更好的解决方案，故此这句 API 被注释
//            }
//        }

        @Override
        public BaseDialog show() {
            final BaseDialog dialog = create();
            mDialogFragment = createDialogFragment(dialog);
            mDialogFragment.show(mActivity.getSupportFragmentManager(), getFragmentTag());
            // 解决 Dialog 设置了而 DialogFragment 没有生效的问题
            mDialogFragment.setCancelable(isCancelable());
            return dialog;
        }

        /**
         * 创建 DialogFragment 对象（子类可以重写此方法来改变 DialogFragment 类型）
         */
        protected BaseDialogFragment createDialogFragment(BaseDialog dialog) {
            return new BaseDialogFragment(dialog);
        }
    }
}
