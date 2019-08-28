package cn.hujw.dialog;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;

import cn.hujw.base.BaseDialog;
import cn.hujw.base.BaseDialogFragment;

/**
 * @author: hujw
 * @date: 2019/8/18
 * @description: Toast 效果对话框
 * @email: hujw_android@163.com
 */
public final class ToastDialog {

    public static final class Builder
            extends BaseDialogFragment.Builder<Builder>
            implements Runnable, BaseDialog.OnShowListener {

        private final TextView mMessageView;
        private final ImageView mIconView;

        private Type mType = Type.WARN;
        private int mDuration = 2000;

        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_toast);
            setAnimStyle(BaseDialog.AnimStyle.TOAST);
            setBackgroundDimEnabled(false);
            setCancelable(false);

            mMessageView = findViewById(R.id.tv_toast_message);
            mIconView = findViewById(R.id.iv_toast_icon);

            addOnShowListener(this);
        }

        public Builder setType(Type type) {
            mType = type;
            switch (type) {
                case FINISH:
                    mIconView.setImageResource(R.drawable.ic_dialog_finish);
                    break;
                case ERROR:
                    mIconView.setImageResource(R.drawable.ic_dialog_error);
                    break;
                case WARN:
                    mIconView.setImageResource(R.drawable.ic_dialog_warning);
                    break;
                default:
                    break;
            }
            return this;
        }

        public Builder setDuration(int duration) {
            mDuration = duration;
            return this;
        }

        public Builder setMessage(@StringRes int id) {
            return setMessage(getString(id));
        }
        public Builder setMessage(CharSequence text) {
            mMessageView.setText(text);
            return this;
        }

        @Override
        public BaseDialog create() {
            // 如果显示的类型为空就抛出异常
            if (mType == null) {
                throw new IllegalArgumentException("The display type must be specified");
            }
            // 如果内容为空就抛出异常
            if ("".equals(mMessageView.getText().toString())) {
                throw new IllegalArgumentException("Dialog message not null");
            }

            return super.create();
        }

        @Override
        public void onShow(BaseDialog dialog) {
            // 延迟自动关闭
            postDelayed(this, mDuration);
        }

        @Override
        public void run() {
            if (getDialogFragment() != null &&
                    getDialogFragment().isAdded() &&
                    getDialog() != null &&
                    getDialog().isShowing()) {
                dismiss();
            }
        }
    }

    /**
     * 显示的类型
     */
    public enum Type {
        // 完成，错误，警告
        FINISH, ERROR, WARN
    }
}
