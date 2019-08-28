package cn.hujw.demo.ui.dialog;

import android.view.Gravity;

import androidx.fragment.app.FragmentActivity;

import cn.hujw.base.BaseDialog;
import cn.hujw.demo.R;
import cn.hujw.demo.common.MyDialogFragment;

/**
 * @author: hujw
 * @date: 2019/8/25
 * @description: 可进行拷贝的副本
 * @email: hujw_android@163.com
 */
public final class CopyDialog {

    public static final class Builder
            extends MyDialogFragment.Builder<Builder> {

        public Builder(FragmentActivity activity) {
            super(activity);

            setContentView(R.layout.dialog_copy);
            setAnimStyle(BaseDialog.AnimStyle.BOTTOM);
            setGravity(Gravity.BOTTOM);
        }
    }
}
